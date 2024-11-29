package com.spartak.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email is valid ->
        // todo: check if email not taken ->
        customerRepository.saveAndFlush(customer);


        // todo: check if fraudster ->
        FraudCheckResponce fraudCheckResponce = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponce.class,
                customer.getId()

        );

        if (fraudCheckResponce.isFraudster()) {
            throw new IllegalStateException("He is a fraudster");
        }


        // store customer in db ->

        // todo: send notification
    }
}
