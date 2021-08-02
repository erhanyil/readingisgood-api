package com.example.readingisgood.dto.customer;

import com.example.readingisgood.constant.Role;
import com.example.readingisgood.model.Customer;

import javax.validation.constraints.NotNull;

public class CustomerDto {

    private String id;

    @NotNull(message = "FirstName required")
    private String firstName;

    @NotNull(message = "FirstName required")
    private String lastName;

    @NotNull(message = "FirstName required")
    private String fullName;

    @NotNull(message = "FirstName required")
    private String email;

    @NotNull(message = "Password required")
    private String password;

    private String role = Role.USER;

    public CustomerDto() {
    }

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.fullName = this.firstName + ' ' + this.lastName;
        this.email = customer.getEmail();
        this.password = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
