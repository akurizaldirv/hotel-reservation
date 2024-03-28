package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Customer;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.request.auth.RegisterCustomerRequest;
import com.enigma.hotelreservation.model.request.user.CustomerUpdateRequest;
import com.enigma.hotelreservation.model.response.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAll();
    CustomerResponse update(CustomerUpdateRequest request);
    Customer getCustomerById(Integer id);
    CustomerResponse getById(Integer id);
    Customer getLastCustomer();
    Customer create(RegisterCustomerRequest request, UserCredential userCredential);
}
