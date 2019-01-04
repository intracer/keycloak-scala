package illko

import org.keycloak.admin.client.Keycloak
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
  }
}