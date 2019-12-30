package com.artyom.dvdrentals.model.repo;

import com.artyom.dvdrentals.model.entity.Customer;

import com.artyom.dvdrentals.model.projections.CustomerInfoProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> , PagingAndSortingRepository<Customer, Integer> {
    Optional<CustomerInfoProjection> findAllById(int id);

    Page<CustomerInfoProjection> findAllProjectedBy(Pageable pageable);

    @Query(value="{'Rentals.filmId' : ?0 }")
    List<CustomerInfoProjection> findCustomerByFilmId(int filmId);
}
