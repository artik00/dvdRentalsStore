package com.artyom.dvdrentals.service;

import com.artyom.dvdrentals.model.projections.CustomerInfoProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProjectionClass implements CustomerInfoProjection {

    private int id;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    private String city;

    private String country;

    private String district;

}
