package com.pichincha.crd.automotriz.domain;

public class Routes {

    public static final String BASE = "/api";
    public static final String VEHICLES = "/vehicles";
    public static final String BASE_VEHICLES = BASE + VEHICLES;
    public static final String ID = "/{id}";
    public static final String BRAND = "/brands";
    public static final String MODEL = "/models";
    public static final String YEAR = "years";
    public static final String CLIENT = "/clients";
    public static final String YARD = "/yards";

    public static final String CLIENT_YARD = "/client-yards";
    public static final String EXECUTIVE = "/executives";
    public static final String CREDIT_REQUEST = "/credit-requests";

    public static final String BASE_CREDIT_REQUEST = BASE + CREDIT_REQUEST;
    public static final String BASE_BRAND = BASE + BRAND;
    public static final String BASE_CLIENT = BASE + CLIENT;
    public static final String BASE_YARD = BASE + YARD;
    public static final String BASE_EXECUTIVES = BASE + EXECUTIVE;

    public static final String BASE_CLIENT_YARD = BASE + CLIENT_YARD;
}
