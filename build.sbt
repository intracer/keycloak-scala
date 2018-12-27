name := "keycloak-scala"

version := "0.1"

scalaVersion := "2.12.8"

//resolvers += "JBoss" at "https://repository.jboss.org"
resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "org.keycloak" % "keycloak-admin-client" % "4.8.1.Final",
  "org.jboss.resteasy" % "resteasy-client" % "3.6.1.Final",
  "org.jboss.resteasy" % "resteasy-jackson2-provider" % "3.6.1.Final"
)

dependencyOverrides ++= Seq(
  "org.jboss.spec.javax.ws.rs" % "jboss-jaxrs-api_2.1_spec" % "1.0.1.Final",
  "org.jboss.spec.javax.xml.bind" % "jboss-jaxb-api_2.3_spec" % "1.0.1.Final",
  "org.jboss.spec.javax.annotation" % "jboss-annotations-api_1.2_spec" % "1.0.2.Final",
  "org.jboss.spec.javax.servlet" % "jboss-servlet-api_3.1_spec" % "1.0.2.Final",
  "org.reactivestreams" % "reactive-streams" % "1.0.2",
  "javax.validation" % "validation-api" % "2.0.1.Final",
  "javax.activation" % "activation" % "1.1.1",
  "org.apache.httpcomponents" % "httpclient" % "4.5.3",
  "commons-io" % "commons-io" % "2.6",
  "net.jcip" % "jcip-annotations" % "1.0",
  "javax.json.bind" % "javax.json.bind-api" % "1.0",
  "org.jboss.logging" % "jboss-logging" % "3.3.2.Final",
  "org.jboss.logging" % "jboss-logging-annotations" % "2.1.0.Final",
  "org.jboss.logging" % "jboss-logging-processor" % "2.1.0.Final",

  "com.fasterxml.jackson.jaxrs" % "jackson-jaxrs-json-provider" % "2.9.5",
  "com.github.fge" % "json-patch" % "1.3",   
  "junit" % "junit" % "4.12"
)