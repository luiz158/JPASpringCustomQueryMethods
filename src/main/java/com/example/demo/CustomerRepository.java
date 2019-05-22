package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    long countByState(String state);
    Iterable<Customer> findAllByLastNameContainingIgnoreCase(String lastName);
    Iterable<Customer> findAllByCityAndState(String city, String state);
    Iterable<Customer> findAllByLastnameOrderByState(String lastname, String state);


    //Customer findById(Long id);
    //ArrayList<Customer> findByName(String name);

    //List<Customer> findByEmailAddressAndLastName(String emailaddress, String lastname);
    List<Customer> findByLastname(String lastname);

}
