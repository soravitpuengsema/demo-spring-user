package com.users.demo.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Valid
    @NotNull(message = "Address is required")
    private Address address;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Website is required")
    private String website;

    @Valid
    @NotNull(message = "Company is required")
    private Company company;
}
