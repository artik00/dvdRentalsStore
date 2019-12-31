package com.artyom.dvdrentals.model.dto.response;

import com.artyom.dvdrentals.model.entity.Film;
import com.artyom.dvdrentals.model.projections.CustomerInfoProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullFilmDetails {
    private Film film;
    private List<CustomerInfoProjection> listOfCustomerWhoRented;
}
