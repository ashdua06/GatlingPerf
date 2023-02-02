package com.airtel.global;

public class PerfParams {
    public static final int atOnceUsers=Integer.parseInt(System.getProperty("AtOnceUsers","5"));
    public static final int rampUpUsers=Integer.parseInt(System.getProperty("RampUpUsers","10"));
    public static final int duration=Integer.parseInt(System.getProperty("Duration","10"));

}
