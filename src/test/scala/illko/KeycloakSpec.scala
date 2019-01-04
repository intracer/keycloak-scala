package illko

import javax.ws.rs.core.Response.Status
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.UserRepresentation
import org.specs2.mutable.Specification

import scala.collection.JavaConverters._

class KeycloakSpec extends Specification {
  val authServer = "http://localhost:8080/auth"

  val keycloak: Keycloak = Keycloak.getInstance(authServer, "example", "examples-admin-client", "password",
    "examples-admin-client", "password")
  val realm = keycloak.realm("example")

  "realm" should {
    "contain example client" in {
      val clients = realm.clients().findAll().asScala
      clients.size === 6
      val clientMap = clients.groupBy(_.getClientId).mapValues(_.head)
      clientMap.keySet === Set("account", "admin-cli", "broker", "examples-admin-client", "realm-management", "security-admin-console")
      val client = clientMap("examples-admin-client")
      client.getBaseUrl === "/examples-admin-client"
      client.getClientId === "examples-admin-client"
    }

    "contain example user" in {
      val usersResource = realm.users()
      val users = usersResource.list().asScala
      users.size === 1
      val user = users.head
      user.getUsername === "examples-admin-client"
      user.isEnabled === true
    }

    "create and delete user" in {
      val usersResource = realm.users()
      usersResource.list().asScala.size === 1

      val user = new UserRepresentation()
      user.setUsername("testUser1")
      user.setFirstName("First Name")
      user.setLastName("Last Name")
      user.setEmail("1@b.com")
      user.setEnabled(true)
      val createResponse = usersResource.create(user)
      createResponse.getStatusInfo.toEnum === Status.CREATED

      val all = usersResource.list().asScala
      all.size === 2

      val found = usersResource.search("testUser1").asScala
      found.size === 1

      val deleteResponse = usersResource.delete(found.head.getId)
      deleteResponse.getStatusInfo.toEnum === Status.NO_CONTENT

      usersResource.search("testUser1").asScala.isEmpty === true
    }
  }
}