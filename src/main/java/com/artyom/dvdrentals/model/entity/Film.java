package com.artyom.dvdrentals.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "film")
public class Film {
    @Id
    private int id;

    @Field("Title")
    private String title;

    @Field("Category")
    private String category;

    @Field("Description")
    private String description;

    @Field("Length")
    private String length;

    @Field("Rating")
    private String rating;

    @Field("Rental Duration")
    private String rentalDuration;

    @Field("Replacement Cost")
    private String replacementCost;

    @Field("Special Features")
    private String specialFeatures;

    @Field("Actors")
    private List<Actor> actors;
}
