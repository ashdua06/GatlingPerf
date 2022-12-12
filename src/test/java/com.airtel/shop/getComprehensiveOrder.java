package com.airtel.shop;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class getComprehensiveOrder extends Simulation{

    private HttpProtocolBuilder httpProtocol = http.baseUrl("http://10.241.129.179:8080")
            .header("Accept","application/json")
            .header("content-type","application/json");
    private ScenarioBuilder scn = scenario("Checking Bundle Product Details").exec(
            http("Get Bundle Details")
                    .get("/digital-catalog/s2s/nonAB/api/bundleDetails").queryParam("bundleIds","hard_bundles_getEligibility_FE_02").check(
                            status().is(200))).pause(1);

    {
        setUp(
                scn.injectOpen(atOnceUsers(10))).protocols(httpProtocol);
    }

}