package com.artyom.dvdrentals.model.projections;

public interface CustomerInfoProjection {
    int getId();
    String getFirstName();
    String getLastName();
    String getAddress();
    String getPhone();
    String getCity();
    String getCountry();
    String getDistrict();
}
