package org.znsio.perfiz

import scala.language.postfixOps
import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import io.gatling.core.feeder.FeederBuilder

import scala.concurrent.duration._

class KarateSimulation extends Simulation {
  val petIdFeeder: FeederBuilder = Array(
    Map("petId" -> "103"),
    Map("petId" -> "306")
  ).random

  val pets = scenario("Pets")
    .feed(petIdFeeder)
    .exec(karateFeature("classpath:petstoreFeeder.feature"))

  setUp(
    pets.inject(
      constantUsersPerSec(3) during (15 seconds)
    ).protocols(karateProtocol(
      "/pets/{petId}" -> Nil,
    ))
  )

}