package illko

import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.resource.ClientsResource
import scala.collection.JavaConverters._

object KeycloakQuery {

  def main(args: Array[String]): Unit = {
    val authServer = "http://localhost:8080/auth"

    val keycloak: Keycloak = Keycloak.getInstance(authServer, "example", "examples-admin-client", "password",
      "examples-admin-client", "password")
    val clients: ClientsResource = keycloak.realm("example").clients()

    for (client <- clients.findAll().asScala) {

      if (client.getBaseUrl != null) {
        println("client url: " + client.getBaseUrl + ", client id: " + client.getClientId)
      } else {
        println("client id: " + client.getClientId)
      }
    }
  }
}
