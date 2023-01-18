package com.airtel.global;

public class APIEndpoints {
    public static class OrderHive {
        public static final String PROCEED_TO_CHECKOUT = "/orderhive/s2s/auth/api/order/proceedToCheckout";
        public static final String COMPROHENSIVE_ORDER_DETAILS = "/orderhive/s2s/api/getComprehensiveOrderDetails";

    }

    public static class Template{
        public static final String GET_TEMPLATE="/template/auth/v1/getTemplate";
    }
}
