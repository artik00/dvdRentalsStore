package com.artyom.dvdrentals.service;


import com.artyom.dvdrentals.controller.DvdRentalsController;
import com.artyom.dvdrentals.model.dto.response.FilmResponse;
import com.artyom.dvdrentals.model.dto.response.FullFilmDetails;
import com.artyom.dvdrentals.model.dto.response.RentalResponse;
import com.artyom.dvdrentals.model.entity.Customer;
import com.artyom.dvdrentals.model.entity.Film;
import com.artyom.dvdrentals.model.entity.Rental;
import com.artyom.dvdrentals.model.exception.CustomerNotFoundException;
import com.artyom.dvdrentals.model.exception.FilmNotFoundException;
import com.artyom.dvdrentals.model.projections.AvailableFilm;
import com.artyom.dvdrentals.model.projections.CustomerInfoProjection;
import com.artyom.dvdrentals.model.repo.CustomerRepository;
import com.artyom.dvdrentals.model.repo.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.summingDouble;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private FilmRepository filmRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, FilmRepository filmRepository) {
        this.customerRepository = customerRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public Page<CustomerInfoProjection> findAll(Pageable pageable) {
        return customerRepository.findAllProjectedBy(pageable);
    }

    @Override
    public CustomerInfoProjection findAllById(int id) {
        Optional<CustomerInfoProjection> customer = customerRepository.findAllById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public RentalResponse findRentalsByCustomerId(int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        List<FilmResponse> rentedFilms = new ArrayList<>();
        RentalResponse rentalResponse = new RentalResponse(customer.get().getId(), customer.get().getLastName(),
                customer.get().getFirstName(), rentedFilms);

        for (Rental rental: customer.get().getRentals()) {
            Optional<Film> film = filmRepository.findById(rental.getFilmId());
            if (film.isPresent()) {
                double sumPaid = rental.getPayments().stream().collect(summingDouble(f -> f.getAmount()));
                FilmResponse filmResponse = new FilmResponse(rental.getRentalDate(), sumPaid,
                        film.get().getLength());
                Link selfLink = linkTo(methodOn(DvdRentalsController.class).getFilmById(film.get().getId())).withSelfRel();
                filmResponse.add(selfLink);
                rentedFilms.add(filmResponse);
            }
        }
        return rentalResponse;
    }

    @Override
    public FullFilmDetails findFilmById(int id) {
        Optional<Film> film = filmRepository.findById(id);

        if (film.isPresent()) {
            List<CustomerInfoProjection> allCustomers = customerRepository.findCustomerByFilmId(film.get().getId());
            return new FullFilmDetails(film.get(), allCustomers);
        } else {
            throw new FilmNotFoundException();
        }
    }

    @Override
    public Page<AvailableFilm> findAllFilms(Pageable pageable) {
        return filmRepository.findAllProjectedBy(pageable);
    }

}
