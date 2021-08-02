package com.example.readingisgood.dto.customer;

import javax.validation.constraints.NotNull;

public class CustomerRequestDto {

    @NotNull(message = "FirstName required")
    private String firstName;

    @NotNull(message = "LastName required")
    private String lastName;

    @NotNull(message = "Email required")
    private String email;

    @NotNull(message = "Password required")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
