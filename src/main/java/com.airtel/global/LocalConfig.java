package com.airtel.global;

import com.airtel.utils.PropertyUtils;

public class LocalConfig {
    public static final String SHOP_GET_COMPREHENSIVE_IP_PORT;

    static {
        try {
            PropertyUtils.getInstance().load("config.properties");
            SHOP_GET_COMPREHENSIVE_IP_PORT = System.getProperty("SHOP_GET_COMPREHENSIVE_IP_PORT", PropertyUtils.getInstance().getValue("SHOP_GET_COMPREHENSIVE_IP_PORT"));
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("Something wrong !!! Check configurations.", t);
        }
    }
}
