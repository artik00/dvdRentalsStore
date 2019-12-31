package com.artyom.dvdrentals.controller;


import com.artyom.dvdrentals.model.dto.response.FullFilmDetails;
import com.artyom.dvdrentals.model.dto.response.RentalResponse;
import com.artyom.dvdrentals.model.projections.AvailableFilm;
import com.artyom.dvdrentals.model.projections.CustomerInfoProjection;
import com.artyom.dvdrentals.service.CustomerService;

import com.artyom.dvdrentals.utils.ApiPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/dvdrentals")
public class DvdRentalsController {

    private CustomerService customerService;

    public DvdRentalsController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{id}")
    public CustomerInfoProjection getCustomerById(@PathVariable(value="id") int id) {
        return customerService.findAllById(id);
    }

    @GetMapping("/customers")
    @ApiPageable
    public Page<CustomerInfoProjection> getAllCustomers(@ApiIgnore @NonNull Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @GetMapping("/customers/{id}/rentals")

    public RentalResponse getRentalsForCustomer(@PathVariable(value="id") int id) {
        return customerService.findRentalsByCustomerId(id);
    }

    @GetMapping("/films/{id}")
    public FullFilmDetails getFilmById(@PathVariable(value="id") int id) {
        return customerService.findFilmById(id);
    }

    @GetMapping("/films")
    @ApiPageable
    public Page<AvailableFilm> getAllFilms(@ApiIgnore@NotNull final Pageable pageable) {
        return customerService.findAllFilms(pageable);
    }
}
