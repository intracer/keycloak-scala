package illko

import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.resource.{ClientsResource, RealmResource, UsersResource}
import org.keycloak.representations.idm.UserRepresentation

import scala.collection.JavaConverters._

object KeycloakQuery {

  def main(args: Array[String]): Unit = {
    val authServer = "http://localhost:8080/auth"

    val keycloak: Keycloak = Keycloak.getInstance(authServer, "example", "examples-admin-client", "password",
      "examples-admin-client", "password")
    val realm = keycloak.realm("example")

    printClients(realm)

    val usersResource = realm.users()
    userOperations(usersResource)
  }

  private def userOperations(usersResource: UsersResource) = {
    printUsers(usersResource.list().asScala)
    createUser(usersResource)

    printUsers(usersResource.list().asScala)

    val found = usersResource.search("testUser1").asScala
    printUsers(found)

    found.headOption.foreach { user =>
      usersResource.delete(user.getId)
    }

    printUsers(usersResource.list().asScala)
  }

  private def createUser(usersResource: UsersResource) = {
    val user = new UserRepresentation()
    user.setUsername("testUser1")
    user.setFirstName("First Name")
    user.setLastName("Last Name")
    user.setEmail("1@b.com")
    user.setEnabled(true)
    val response = usersResource.create(user)
    println("status: " + response.getStatusInfo.toEnum)
  }

  private def printUsers(users: Seq[UserRepresentation]) = {
    for ((user, i) <- users.zipWithIndex) {
      println(s"${i + 1}. id: ${user.getId}, username: ${user.getUsername}, Name: ${user.getFirstName} ${user.getLastName}, email: ${user.getEmail}, enabled: ${user.isEnabled}")
    }
  }

  def printClients(realm: RealmResource) = {
    val clients = realm.clients()
    for (client <- clients.findAll().asScala) {
      if (client.getBaseUrl != null) {
        println("client url: " + client.getBaseUrl + ", client id: " + client.getClientId)
      } else {
        println("client id: " + client.getClientId)
      }
    }
  }
}
