package com.spartak.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
