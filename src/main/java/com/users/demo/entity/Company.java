package com.users.demo.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @NotBlank(message = "Company name is required")
    private String name;

    @NotBlank(message = "Catchphrase is required")
    private String catchPhrase;

    @NotBlank(message = "Business sector (bs) is required")
    private String bs;
}