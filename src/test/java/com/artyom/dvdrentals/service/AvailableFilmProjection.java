package com.artyom.dvdrentals.service;

import com.artyom.dvdrentals.model.projections.AvailableFilm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableFilmProjection implements AvailableFilm {
    private int id;

    private String title;

    private String category;

    private String description;

    private String length;

    private String rating;

    private String rentalDuration;
}
