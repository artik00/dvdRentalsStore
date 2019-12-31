package com.artyom.dvdrentals.model.entity;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private int id;

    @Field("First Name")
    private String firstName;

    @Field("Last Name")
    private String lastName;

    @Field("Phone")
    private String phone;

    @Field("Address")
    private String address;

    @Field("City")
    private String city;

    @Field("Country")
    private String country;

    @Field("District")
    private String district;

    @Field("Rentals")
    private List<Rental> rentals;
}
