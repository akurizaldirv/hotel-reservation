package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Customer;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;
import com.enigma.hotelreservation.model.request.auth.RegisterCustomerRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer getCustomerById(Integer id);
    Customer getLastCustomer();
    Customer create(RegisterCustomerRequest request, UserCredential userCredential);
}
