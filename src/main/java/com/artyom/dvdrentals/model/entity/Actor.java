package com.artyom.dvdrentals.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Field("actorId")
    private String actorId;
    @Field("First name")
    private String firstName;
    @Field("Last name")
    private String lastName;
}
