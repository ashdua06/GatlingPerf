package com.airtel.scenarioBuilder;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public interface ScenarioInterface {
    HttpProtocolBuilder getHttpProtocolBuilder();
    ScenarioBuilder getScenarioBuilder();
    PopulationBuilder getPopulationBuilder();

    void execute();

}
