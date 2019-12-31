package com.artyom.dvdrentals.model.projections;

public interface AvailableFilm {
    int getId();
    String getTitle();
    String getCategory();
    String getDescription();
    String getRating();
    String getRentalDuration();
}
