package com.users.demo.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Suite is required")
    private String suite;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Zipcode is required")
    private String zipcode;

    @Valid
    @NotNull(message = "Geo coordinates are required")
    private Geo geo;
}