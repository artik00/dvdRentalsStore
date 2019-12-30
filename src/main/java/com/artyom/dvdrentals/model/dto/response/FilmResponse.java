package com.artyom.dvdrentals.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmResponse extends ResourceSupport {
    private String rentalDate;
    private double amountPaid;
    private String filmDuration;
}
