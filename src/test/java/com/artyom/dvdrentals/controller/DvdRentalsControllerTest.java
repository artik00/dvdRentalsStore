package com.artyom.dvdrentals.controller;

import com.artyom.dvdrentals.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DvdRentalsController.class)
public class DvdRentalsControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void evaluatesPageableParametersForCustomers() throws Exception {
        mockMvc.perform(get("/dvdrentals/customers")
                .param("page", "5")
                .param("size", "10")
                .param("sort", "_id")
                .param("sort", "lastName,desc"))
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor =
                ArgumentCaptor.forClass(Pageable.class);
        verify(customerService).findAll(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertEquals(5, pageable.getPageNumber());
        assertEquals(10, pageable.getPageSize());
        assertEquals(Sort.Order.asc("_id"), pageable.getSort().getOrderFor("_id"));
        assertEquals(Sort.Order.desc("lastName"), pageable.getSort().getOrderFor("lastName"));
    }

    @Test
    void evaluatesPageableParameterForFilms() throws Exception {
        mockMvc.perform(get("/dvdrentals/films")
                .param("page", "0")
                .param("size", "100")
                .param("sort", "_id")
                .param("sort", "title,desc"))
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor =
                ArgumentCaptor.forClass(Pageable.class);
        verify(customerService).findAllFilms(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertEquals(0, pageable.getPageNumber());
        assertEquals(100, pageable.getPageSize());
        assertEquals(Sort.Order.asc("_id"), pageable.getSort().getOrderFor("_id"));
        assertEquals(Sort.Order.desc("title"), pageable.getSort().getOrderFor("title"));
    }

}
