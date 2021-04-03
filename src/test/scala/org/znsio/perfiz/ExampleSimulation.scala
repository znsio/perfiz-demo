package org.znsio.perfiz

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ExampleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://host.docker.internal:9999")

  val scn = scenario("ExampleSimulation")
    .exec(http("request_pet")
      .get("/pets/1"))
    .pause(5)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
}