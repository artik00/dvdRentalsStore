package com.artyom.dvdrentals.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Field("rentalId")
    private String rentalId;

    @Field("Film Title")
    private String filmTitle;

    private int filmId;

    private String staffId;

    @Field("Payments")
    private List<Payment> payments;

    @Field("Rental Date")
    private String rentalDate;

    @Field("Return Date")
    private String returnDate;
}
