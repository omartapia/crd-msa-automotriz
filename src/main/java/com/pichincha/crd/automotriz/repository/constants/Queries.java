package com.pichincha.crd.automotriz.repository.constants;

public class Queries {
    public static final String EXIST_BY_VEHICLE = "SELECT COUNT(*) > 0 FROM CreditRequest cr WHERE cr.vehicle.id = :vehicleId and cr.state = 'Registered' ";
    public static final String EXIST_BY_CLIENT_YARD = "SELECT COUNT(*) > 0 FROM CreditRequest cr WHERE cr.client.id = :clientId and cr.yard.id = :yardId  and cr.state = 'Registered' ";
    public static final String EXIST_ACTIVE_REQUEST_BY_CLIENT_AND_DATE = "SELECT COUNT(*) > 0 FROM CreditRequest cr WHERE cr.client.id = :clientId and cr.date like CONCAT('%',:date,'%') and cr.state =:state";
    public static final String FIND_VEHICLE_BY_BRAND = "SELECT v FROM Vehicle v WHERE v.brand.id = :brandId";

    public static final String FIND_YARD_BY_NAME = "select y from Yard y where y.name =:name";
}
