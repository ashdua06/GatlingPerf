package com.airtel.scenarioBuilder.orderhive;

import com.airtel.chainBuilder.orderhive.GetComprohensiveOrderDetails;
import com.airtel.chainBuilder.orderhive.ProceedToCheckout;
import com.airtel.core.BaseGatling;
import com.airtel.global.*;
import com.airtel.scenarioBuilder.ScenarioInterface;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;
public class CreateAndGetOrder extends BaseGatling implements ScenarioInterface {
    private ScenarioBuilder scenarioBuilder;
    private PopulationBuilder populationBuilder;
    private ProceedToCheckout proceedToCheckout;
    private GetComprohensiveOrderDetails getComprohensiveOrderDetails;


    public CreateAndGetOrder(){
        setScenarioDescription("Create and get order details");
        pause(1);
    }


    public PopulationBuilder getPopulationBuilder() {
        return this.populationBuilder;
    }


    public void executeApi() {
        //Create order Api
        proceedToCheckout=new ProceedToCheckout();
        proceedToCheckout.execute();

        //Get Order Api
        getComprohensiveOrderDetails=new GetComprohensiveOrderDetails();
        getComprohensiveOrderDetails.execute();

    }


    public void executeScenario() {
        //Setting up scenario builder for chain apis in sequence
        this.scenarioBuilder=executeScenario(proceedToCheckout.getHttpRequestActionBuilder(),getComprohensiveOrderDetails.getHttpRequestActionBuilder());
        //Setting ramp up and duration for scenario
        this.populationBuilder=executePopulationBuilder(this.scenarioBuilder, PerfParams.atOnceUsers,PerfParams.rampUpUsers,PerfParams.duration);

    }


}
