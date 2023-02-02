package com.airtel.chainBuilder.orderhive;

import com.airtel.chainBuilder.ChainInterface;
import com.airtel.core.BaseGatling;
import com.airtel.global.APIEndpoints;
import com.airtel.global.ContentType;
import com.airtel.global.LocalConfig;
import com.airtel.global.MethodType;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

public class GetComprohensiveOrderDetails extends BaseGatling implements ChainInterface {

    private HttpRequestActionBuilder httpRequestActionBuilder;
    public GetComprohensiveOrderDetails(){
        setHttpReqDescription("Get Comprehensive order details");
        setMethod(MethodType.GET,  LocalConfig.ORDERHIVE_IP_PORT+APIEndpoints.OrderHive.COMPROHENSIVE_ORDER_DETAILS);
        setContentType(ContentType.JSON);
        checkStatus(200);

    }

    public HttpRequestActionBuilder getHttpRequestActionBuilder(){
        return this.httpRequestActionBuilder;
    }

    public void execute(){
        this.httpRequestActionBuilder=executeHttpActionBuilder();
    }


}
