package com.airtel.chainBuilder;


import com.airtel.core.BaseGatling;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

public interface ChainInterface{
    HttpRequestActionBuilder getHttpRequestActionBuilder();
    void execute();

    void saveResponseField(String jsonPath,String variableName);

    void saveResponse(String variableName);

    void validateResponseField(String actualFieldJsonPath,String expectedVariableName);
    void validateResponseFieldValue(String actualFieldJsonPath,String expectedVariableValue);

}
