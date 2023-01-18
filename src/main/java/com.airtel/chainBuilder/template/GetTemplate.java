package com.airtel.chainBuilder.template;

import com.airtel.chainBuilder.ChainInterface;
import com.airtel.core.BaseGatling;
import com.airtel.global.APIEndpoints;
import com.airtel.global.ContentType;
import com.airtel.global.LocalConfig;
import com.airtel.global.MethodType;
import io.gatling.core.action.builder.FeedBuilder;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.javaapi.http.HttpRequestActionBuilder;



public class GetTemplate extends BaseGatling implements ChainInterface {
    private HttpRequestActionBuilder httpRequestActionBuilder;
    //"{ \"username\": \"${username}\" }"))


    public GetTemplate(){
        setHttpReqDescription("Get Template");
        setMethod(MethodType.GET,  LocalConfig.TEMPLATE_IP_PORT+ APIEndpoints.Template.GET_TEMPLATE);
        setContentType(ContentType.JSON);
        setHeader("Authorization","Basic c2hvcC11c2VyOnNob3BAdXNlcg==");
        setQueryParam("journeyName","#{journeyName}");
        checkStatus(200);
        saveElementInResponseBody("$.body.journeyName","actualJourneyName");
        validateElementInResponse("$.body.journeyName","journeyName");
       // validateElementValueInResponse("$.body.journeyName","SIM_ACQUISITION_AUTO_TC6");
    }
    @Override
    public HttpRequestActionBuilder getHttpRequestActionBuilder() {
        return this.httpRequestActionBuilder;
    }

    @Override
    public void execute() {
        this.httpRequestActionBuilder=executeHttpActionBuilder();
    }
}
