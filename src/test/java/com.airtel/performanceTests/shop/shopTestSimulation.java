package com.airtel.performanceTests.shop;

import com.airtel.scenarioBuilder.orderhive.CreateAndGetOrder;
import com.airtel.scenarioBuilder.template.GetJourneyTemplate;
import io.gatling.javaapi.core.Simulation;
public class shopTestSimulation extends Simulation {
    {

        //Orderhive Service: Create and get orderhive order scenario (multiple apis)
        CreateAndGetOrder createAndGetOrderScenario =new CreateAndGetOrder();
        createAndGetOrderScenario.executeApi();
        createAndGetOrderScenario.executeScenario();

        //Template Service: Get journey template scenario (single api call)
        GetJourneyTemplate getJourneyTemplateScenario=new GetJourneyTemplate();
        getJourneyTemplateScenario.executeApi();
        getJourneyTemplateScenario.executeScenario();

        setUp(
                createAndGetOrderScenario.getPopulationBuilder(),
                getJourneyTemplateScenario.getPopulationBuilder()
        );


    }
}

