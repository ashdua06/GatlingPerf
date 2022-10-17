package simulations.shop
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class getComprehensiveOrder extends Simulation{
  val httpConf = http.baseUrl("http://10.241.129.179:8080")
    .header("Accept",value="application/json")
    .header("content-type","application/json")

  //scenario

  val scn = scenario("Checking Bundle Product Details")
    .exec(http("Get Bundle Details")
      .get("/digital-catalog/s2s/nonAB/api/bundleDetails")
      .queryParam("bundleIds","hard_bundles_getEligibility_FE_02")
      .check(status is 200))

  //setup

  setUp(scn.inject(atOnceUsers(10))).protocols(httpConf)

}
