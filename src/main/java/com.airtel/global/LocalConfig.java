package com.airtel.global;


import com.airtel.readWriteUtils.PropertyUtils;

public class LocalConfig {
    public static final String ORDERHIVE_IP_PORT;
    public static final String TEMPLATE_IP_PORT;

    static {
        try {
            PropertyUtils.getInstance().loadProperties("config.properties");
            ORDERHIVE_IP_PORT=System.getProperty("ORDERHIVE_IP_PORT", PropertyUtils.getInstance().getValue("ORDERHIVE_IP_PORT"));
            TEMPLATE_IP_PORT=System.getProperty("TEMPLATE_IP_PORT", PropertyUtils.getInstance().getValue("TEMPLATE_IP_PORT"));
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("Something wrong !!! Check configurations.", t);
        }
    }
}
