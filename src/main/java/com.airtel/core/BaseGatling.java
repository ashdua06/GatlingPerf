package com.airtel.core;


import com.airtel.global.ContentType;
import com.airtel.global.MethodType;
import io.gatling.core.action.builder.FeedBuilder;
import io.gatling.core.feeder.Batch;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.Http;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import java.util.Arrays;
import java.util.Map;

import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import static io.gatling.javaapi.core.CoreDsl.*;
import  io.gatling.javaapi.core.internal.*;

public class BaseGatling  extends Simulation {

    private HttpProtocolBuilder httpProtocol= http;
    private MethodType method;
    private ScenarioBuilder scenarioBuilder;

    private Http httpDsl;

    private HttpRequestActionBuilder httpRequestActionBuilder;

    public void setScenarioDescription(String scenario) {
        scenarioBuilder= scenario(scenario);
    }

    public ScenarioBuilder executeScenario(HttpRequestActionBuilder... httpRequestActionBuilders){
        System.out.println("number of action builder is "+httpRequestActionBuilders.length);
        Arrays.stream(httpRequestActionBuilders).forEach(httpRequestActionBuilder->{
            scenarioBuilder=scenarioBuilder.exec(httpRequestActionBuilder.check(bodyString().saveAs("CORE_BODY"))).
                    exec(session -> {
                        System.out.println("Response is "+session.getString("CORE_BODY"));
                        return session;
                    });
        });
        return scenarioBuilder;
    }

    public void setFeederUsingCsvRandom(String fileName){
        FeederBuilder.Batchable<String> feeder=csv(fileName).random();
        scenarioBuilder=scenarioBuilder.feed(feeder);
    }
    public void setFeederUsingCsvCircular(String fileName){
        FeederBuilder.Batchable<String> feeder=csv(fileName).circular();
        scenarioBuilder=scenarioBuilder.feed(feeder);
    }

    public void setFeederUsingCsvQueue(String fileName){
        FeederBuilder.Batchable<String> feeder=csv(fileName).queue();
        scenarioBuilder=scenarioBuilder.feed(feeder);
    }
    public void setFeederUsingCsvShuffle(String fileName){
        FeederBuilder.Batchable<String> feeder=csv(fileName).shuffle();
        scenarioBuilder=scenarioBuilder.feed(feeder);
    }

    public void setFeederUsingJsonRandom(String fileName){
        FeederBuilder.FileBased<Object> feeder=jsonFile(fileName).random();
        scenarioBuilder=scenarioBuilder.feed(feeder);
    }

    public HttpProtocolBuilder executeHttpProtocol(){
        return httpProtocol;
    }

    public HttpRequestActionBuilder executeHttpActionBuilder(){
        return httpRequestActionBuilder;
    }

    public PopulationBuilder executePopulationBuilder(ScenarioBuilder scn, int atOnceUsers,int rampUsers, int duration){
        return scn.injectOpen(atOnceUsers(atOnceUsers),rampUsers(rampUsers).during(duration));
    }

    public void setBaseUrl(String url){
        httpProtocol=httpProtocol.baseUrl(url);
    }

    public void setHeader(String headerKey,String headerValue){
        httpRequestActionBuilder=httpRequestActionBuilder.header(headerKey,headerValue);
    }

    public void setContentType(ContentType contentType){
        httpRequestActionBuilder=httpRequestActionBuilder.header("content-type",contentType.getContentType());
    }

    public void setHeader(Map<String,String> headers){
        httpRequestActionBuilder= httpRequestActionBuilder.headers(headers);
    }
    public void setBody(String body){
        httpRequestActionBuilder= httpRequestActionBuilder.body(StringBody(body)).asJson();
    }

    public void setBodyUsingRawFile(String rawFile){
       httpRequestActionBuilder= httpRequestActionBuilder.body(RawFileBody(rawFile));
    }

    public void setHttpReqDescription(String httpRequest){
        httpDsl= http(httpRequest);
    }




    public void setMethod(MethodType method,String basePath) {
        this.method = method;
        switch (method) {
            case GET:
                httpRequestActionBuilder = httpDsl.get(basePath);
                break;
            case POST:
                httpRequestActionBuilder = httpDsl.post(basePath);
                break;
            case PUT:
                httpRequestActionBuilder = httpDsl.put(basePath);
                break;
            case DELETE:
                httpRequestActionBuilder = httpDsl.delete(basePath);
                break;
            case PATCH:
                httpRequestActionBuilder = httpDsl.patch(basePath);
                break;
            default:
                throw new RuntimeException("API method not specified");
        }
    }
    public void setQueryParam(String key, String value){
        httpRequestActionBuilder=httpRequestActionBuilder.queryParam(key,value);
    }
    public void setQueryParams(Map<String,Object> params){
        httpRequestActionBuilder=httpRequestActionBuilder.queryParamMap(params);
    }

    public void checkStatus(int statusCode){
        httpRequestActionBuilder=httpRequestActionBuilder.check(status().is(statusCode));
    }


    public void saveResponseBody(String variableName){
        httpRequestActionBuilder=httpRequestActionBuilder.check(bodyString().saveAs(variableName));
    }

    public void saveElementInResponseBody(String jsonPath,String variableName){
        httpRequestActionBuilder=httpRequestActionBuilder.check(jsonPath(jsonPath).saveAs(variableName));
    }

    public void validateElementInResponse(String actualElementJsonPath,String expectedElementName){
        httpRequestActionBuilder=httpRequestActionBuilder.check(jsonPath(actualElementJsonPath).is(session -> session.getString(expectedElementName)));
    }


    public void validateElementValueInResponse(String actualElementJsonPath,String expectedElementValue){
        httpRequestActionBuilder=httpRequestActionBuilder.check(jsonPath(actualElementJsonPath).is(expectedElementValue));
    }



    public void pause(long l){
        scenarioBuilder=scenarioBuilder.pause(l);
    }

}
