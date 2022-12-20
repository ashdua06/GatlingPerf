package com.airtel.performanceTests.shop;
import com.airtel.scenarioBuilder.shop.GetComprehensiveOrder;
import io.gatling.javaapi.core.Simulation;
public class shopTestSimulation extends Simulation {

    {
        GetComprehensiveOrder getComprehensiveOrder=new GetComprehensiveOrder();
        getComprehensiveOrder.execute();
        setUp(getComprehensiveOrder.getPopulationBuilder()).protocols(getComprehensiveOrder.getHttpProtocolBuilder());
    }
}

