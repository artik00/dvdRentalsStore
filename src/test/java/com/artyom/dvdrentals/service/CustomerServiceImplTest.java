package com.artyom.dvdrentals.service;

import com.artyom.dvdrentals.model.dto.response.FullFilmDetails;
import com.artyom.dvdrentals.model.entity.Film;
import com.artyom.dvdrentals.model.exception.CustomerNotFoundException;
import com.artyom.dvdrentals.model.exception.FilmNotFoundException;
import com.artyom.dvdrentals.model.projections.AvailableFilm;
import com.artyom.dvdrentals.model.projections.CustomerInfoProjection;
import com.artyom.dvdrentals.model.repo.CustomerRepository;
import com.artyom.dvdrentals.model.repo.FilmRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    FilmRepository filmRepository;

    @Mock
    Pageable pageable;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    public void testFindAllCustomers() {
        CustomerInfoProjection customer1 = new CustomerProjectionClass(1, "Art", "h",
                "1231", "!231" , "van", "asdasd", "east");
        CustomerInfoProjection customer2 = new CustomerProjectionClass(2, "Art2", "hve",
                "1231", "!231" , "van", "asdasd", "east");
        List<CustomerInfoProjection> list = List.of(customer1, customer2);
        when(customerRepository.findAllProjectedBy(any())).thenReturn(new PageImpl<>(list));

        Page<CustomerInfoProjection> result = customerService.findAll(pageable);
        assertNotNull(result);
        assertEquals(list.size(), result.getTotalElements());
    }


    @Test
    public void testFindAllById() {
        CustomerInfoProjection customer1 = new CustomerProjectionClass(1, "Art", "h",
                "1231", "!231" , "van", "asdasd", "east");
        when(customerRepository.findAllById(1)).thenReturn(Optional.of(customer1));

        CustomerInfoProjection result = customerService.findAllById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test()
    public void testFindAllByIdNotFound() {
        when(customerRepository.findAllById(1)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findAllById(1));
    }

    @Test
    public void testFindAllFilms() {
        AvailableFilm availableFilm1 = new AvailableFilmProjection(1, "title", "r",
                "desc", "100", "31231", "3");
        AvailableFilm availableFilm2 = new AvailableFilmProjection(2, "title2", "r",
                "desc", "100", "31231", "3");
        List<AvailableFilm> availableFilmList = List.of(availableFilm1, availableFilm2);
        when(filmRepository.findAllProjectedBy(pageable)).thenReturn(new PageImpl<>(availableFilmList));
        Page<AvailableFilm> result = customerService.findAllFilms(pageable);
        assertNotNull(result);
        assertEquals(availableFilmList.size(), result.getTotalElements());
        assertEquals(availableFilmList, result.getContent());
    }

    @Test
    public void testFindFilmById() {
        CustomerInfoProjection customer1 = new CustomerProjectionClass(1, "Art", "h",
                "1231", "!231" , "van", "asdasd", "east");
        List<CustomerInfoProjection> customerInfoProjections = List.of(customer1);
        Film film = new Film();
        //FullFilmDetails fullFilmDetail = new FullFilmDetails(film, customerInfoProjections);
        when(filmRepository.findById(0)).thenReturn(Optional.of(film));
        when(customerRepository.findCustomerByFilmId(0)).thenReturn(customerInfoProjections);
        FullFilmDetails fullFilmDetail = customerService.findFilmById(0);
        assertNotNull(fullFilmDetail);
        assertEquals(0, fullFilmDetail.getFilm().getId());
    }

    @Test
    public void testFindFilmByIdNotFoundExceptionThrown() {
        when(filmRepository.findById(0)).thenReturn(Optional.empty());
        assertThrows(FilmNotFoundException.class, () -> customerService.findFilmById(0));
    }

    @Test
    public void testFindFilmByIdNoCustomersFound() {
        Film film = new Film();
        when(filmRepository.findById(0)).thenReturn(Optional.of(film));
        when(customerRepository.findCustomerByFilmId(0)).thenReturn(null);
        FullFilmDetails fullFilmDetail = customerService.findFilmById(0);
        assertNotNull(fullFilmDetail);
        assertEquals(0, fullFilmDetail.getFilm().getId());
        assertNull(fullFilmDetail.getListOfCustomerWhoRented());
    }

}
