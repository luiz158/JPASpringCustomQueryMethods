package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    long countByState(String state);
    Iterable<Customer> findAllByLastNameContainingIgnoreCase(String lastName);
    Iterable<Customer> findAllByCityAndState(String city, String state);

    //Customer findById(Long id);
    //ArrayList<Customer> findByName(String name);
}
