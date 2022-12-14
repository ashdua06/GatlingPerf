package com.airtel.scenarioBuilder.shop;

import com.airtel.core.BaseGatling;
import com.airtel.global.APIEndpoints;
import com.airtel.global.ContentType;
import com.airtel.global.LocalConfig;
import com.airtel.global.MethodType;
import com.airtel.scenarioBuilder.ScenarioInterface;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;
public class GetComprehensiveOrder extends BaseGatling implements ScenarioInterface {
    private HttpProtocolBuilder httpProtocolBuilder;
    private ScenarioBuilder scenarioBuilder;
    private PopulationBuilder populationBuilder;

    public GetComprehensiveOrder(){
        setScenarioDescription("Checking Bundle Product Details");
        setHttpReqDescription("Get Bundle Details");
        setBaseUrl(LocalConfig.SHOP_GET_COMPREHENSIVE_IP_PORT);
        setMethod(MethodType.GET, APIEndpoints.Shop.GET_COMPREHENSIVE_ORDER);
        setContentType(ContentType.JSON);
        setQueryParam("bundleIds","hard_bundles_getEligibility_FE_02");
        checkStatus(200);
        pause(1);
    }

    public HttpProtocolBuilder getHttpProtocolBuilder(){
        return this.httpProtocolBuilder;
    }

    public ScenarioBuilder getScenarioBuilder(){
        return this.scenarioBuilder;
    }


    public PopulationBuilder getPopulationBuilder() {
        return this.populationBuilder;
    }

    public void execute() {
        this.scenarioBuilder=executeScenario();
        this.httpProtocolBuilder=executeHttpProtocol();
        this.populationBuilder=executePopulationBuilder(this.scenarioBuilder,10,20);
    }


}
