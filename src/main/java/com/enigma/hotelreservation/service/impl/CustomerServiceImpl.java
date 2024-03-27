package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.Customer;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;
import com.enigma.hotelreservation.model.request.auth.RegisterCustomerRequest;
import com.enigma.hotelreservation.model.response.customer.CustomerResponse;
import com.enigma.hotelreservation.repository.CustomerRepository;
import com.enigma.hotelreservation.service.CustomerService;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.exception.QueryException;
import com.enigma.hotelreservation.util.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.getById(id);
        if (customer == null) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return customer;
    }

    @Override
    public CustomerResponse getById(Integer id) {
        Customer customer = this.getCustomerById(id);
        return CustomerMapper.mapToRes(customer);
    }

    @Override
    public Customer getLastCustomer() {
        return customerRepository.getLastCustomer();
    }

    @Override
    public Customer create(RegisterCustomerRequest request, UserCredential userCredential) {
        int rowsChange = customerRepository.insertCustomer(
                request.getAddress(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getName(),
                request.getIdentityNumber(),
                userCredential.getId()
        );
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);

        return this.getLastCustomer();
    }
}
