package com.airtel.chainBuilder;


import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

public interface ChainInterface {
    HttpRequestActionBuilder getHttpRequestActionBuilder();
    void execute();

}
