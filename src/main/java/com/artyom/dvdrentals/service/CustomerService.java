package com.artyom.dvdrentals.service;


import com.artyom.dvdrentals.model.dto.response.FullFilmDetails;
import com.artyom.dvdrentals.model.dto.response.RentalResponse;
import com.artyom.dvdrentals.model.projections.AvailableFilm;
import com.artyom.dvdrentals.model.projections.CustomerInfoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<CustomerInfoProjection> findAll(Pageable pageable);
    CustomerInfoProjection findAllById(int id);
    RentalResponse findRentalsByCustomerId(int id);
    FullFilmDetails findFilmById(int id);
    Page<AvailableFilm> findAllFilms(Pageable pageable);
}
