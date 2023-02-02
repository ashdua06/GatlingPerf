package com.airtel.chainBuilder.orderhive;

import com.airtel.chainBuilder.ChainInterface;
import com.airtel.core.BaseGatling;
import com.airtel.global.APIEndpoints;
import com.airtel.global.ContentType;
import com.airtel.global.LocalConfig;
import com.airtel.global.MethodType;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

public class ProceedToCheckout extends BaseGatling implements ChainInterface {

    private HttpRequestActionBuilder httpRequestActionBuilder;
    public static final String DEFAULT_PROCEEDTOCHECKOUT_REQUEST="{\"channel\":\"DIGITAL_STORE\",\"loggedInSi\":\"#{loggedInSi}\",\"orderMeta\":{},\"orderPaymentDetails\":{\"eligiblePaymentModes\":[{\"modeId\":\"PAYTM\"}],\"orderPaymentAmount\":1,\"skipPayment\":false,\"addToBill\":false},\"serviceOrderItems\":[{\"skuGroupId\":\"APOLLOCIRCLE_2M\",\"serviceId\":\"APOLLOCIRCLE_2M\",\"provisionSi\":\"9654335872\",\"paymentDetails\":{\"paymentAmount\":99},\"serviceOrderMeta\":{\"isBundle\":false,\"bundle\":false}}]}";
    public ProceedToCheckout(){
        setHttpReqDescription("Proceed to checkout");
        setMethod(MethodType.POST, LocalConfig.ORDERHIVE_IP_PORT+APIEndpoints.OrderHive.PROCEED_TO_CHECKOUT);
        setHeader("Authorization","Basic b3JkZXJoaXZlLW9yZGVyaGl2ZTpjb25zdW1lckBvcmRlcmhpdmU=");
        setContentType(ContentType.JSON);
        setBody(DEFAULT_PROCEEDTOCHECKOUT_REQUEST);
        checkStatus(200);

    }


    public HttpRequestActionBuilder getHttpRequestActionBuilder(){
        return this.httpRequestActionBuilder;
    }

    public void execute(){
        this.httpRequestActionBuilder=executeHttpActionBuilder();
    }
}
