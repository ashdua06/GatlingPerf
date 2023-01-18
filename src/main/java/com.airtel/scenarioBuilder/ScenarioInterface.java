package com.airtel.scenarioBuilder;

import io.gatling.javaapi.core.PopulationBuilder;

public interface ScenarioInterface {
    PopulationBuilder getPopulationBuilder();
    void executeApi();
    void executeScenario();

}
