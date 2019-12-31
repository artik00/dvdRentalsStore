package com.artyom.dvdrentals.model.repo;


import com.artyom.dvdrentals.model.entity.Film;
import com.artyom.dvdrentals.model.projections.AvailableFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FilmRepository extends MongoRepository<Film, Integer> {
    Page<AvailableFilm> findAllProjectedBy(Pageable pageable);
}
