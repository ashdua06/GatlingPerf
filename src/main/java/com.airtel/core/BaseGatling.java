package com.airtel.core;


import com.airtel.global.ContentType;
import com.airtel.global.MethodType;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.Http;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

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

//    public  HttpProtocolBuilder getHttpProtocol() {
//        return httpProtocol;
//    }
//
//    public  ScenarioBuilder getScenBuilder() {
//        return scenarioBuilder;
//    }

    public void setScenarioDescription(String scenario) {
        scenarioBuilder= scenario(scenario);
    }

    public ScenarioBuilder executeScenario(){
        return scenarioBuilder.exec(httpRequestActionBuilder);
    }
    public HttpProtocolBuilder executeHttpProtocol(){
        return httpProtocol;
    }

    public PopulationBuilder executePopulationBuilder(ScenarioBuilder scn, int rampUsers, int duration){
        return scn.injectOpen(rampUsers(rampUsers).during(duration));
    }

    public void setBaseUrl(String url){
        httpProtocol=httpProtocol.baseUrl(url);
    }

    public void setHeader(String headerKey,String headerValue){
        httpProtocol=httpProtocol.header(headerKey,headerValue);
    }

    public void setContentType(ContentType contentType){
        httpProtocol=httpProtocol.header("content-type",contentType.getContentType());
    }

    public void setHeader(Map<String,String> headers){
        httpProtocol.headers(headers);
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
        httpRequestActionBuilder.queryParamMap(params);
    }

    public void checkStatus(int statusCode){
        httpRequestActionBuilder=httpRequestActionBuilder.check(status().is(statusCode));
    }

    public void pause(long l){
        scenarioBuilder=scenarioBuilder.pause(l);
    }

}
