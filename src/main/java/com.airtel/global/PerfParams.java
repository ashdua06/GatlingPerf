package com.airtel.global;

public class PerfParams {
    public static final int noOfUsers=Integer.parseInt(System.getProperty("NumberOfUsers","10"));
    public static final int rampUpPeriod=Integer.parseInt(System.getProperty("RampUpPeriod","10"));
    public static final int duration=Integer.parseInt(System.getProperty("Duration","10"));

}
