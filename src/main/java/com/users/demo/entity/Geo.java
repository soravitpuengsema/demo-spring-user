package com.users.demo.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geo {

    @NotNull(message = "Latitude is required")
    private String lat;

    @NotNull(message = "Longitude is required")
    private String lng;
}