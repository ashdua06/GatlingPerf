package com.airtel.scenarioBuilder.template;

import com.airtel.chainBuilder.template.GetTemplate;
import com.airtel.core.BaseGatling;
import com.airtel.scenarioBuilder.ScenarioInterface;
import io.gatling.javaapi.core.PopulationBuilder;
import com.airtel.chainBuilder.orderhive.GetComprohensiveOrderDetails;
import com.airtel.chainBuilder.orderhive.ProceedToCheckout;
import com.airtel.core.BaseGatling;
import com.airtel.global.*;
import com.airtel.scenarioBuilder.ScenarioInterface;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;
public class GetJourneyTemplate extends BaseGatling implements ScenarioInterface {
    private ScenarioBuilder scenarioBuilder;
    private PopulationBuilder populationBuilder;
    private GetTemplate getTemplate;

    public GetJourneyTemplate(){
        setScenarioDescription("Get Journey Template");
        setFeederUsingCsvRandom("templateJourneyName.csv");
        pause(1);
    }
    @Override
    public PopulationBuilder getPopulationBuilder() {
         return this.populationBuilder;
    }

    @Override
    public void executeApi() {
        //Get Template API
        getTemplate=new GetTemplate();
        getTemplate.setQueryParam("journeyName","#{journeyName}");
        getTemplate.saveElementInResponseBody("$.body.journeyName","actualJourneyName");
        getTemplate.validateElementInResponse("$.body.journeyName","journeyName");
        getTemplate.execute();
    }

    @Override
    public void executeScenario() {
        //Create Scenario for get template
        this.scenarioBuilder=executeScenario(getTemplate.getHttpRequestActionBuilder());
        //Set rampup and duration for scenario
        this.populationBuilder=executePopulationBuilder(this.scenarioBuilder,PerfParams.atOnceUsers,PerfParams.rampUpUsers,PerfParams.duration);
    }
}
