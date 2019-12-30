package com.artyom.dvdrentals.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponse {
    private int customerId;
    private String customerLastName;
    private String customerFirstName;
    private List<FilmResponse> listOfFilmsRented;
}
