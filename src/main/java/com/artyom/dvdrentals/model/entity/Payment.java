package com.artyom.dvdrentals.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Field("Payment Id")
    private String paymentId;

    @Field("Amount")
    private double amount;

    @Field("Payment Date")
    private String paymentDate;
}
