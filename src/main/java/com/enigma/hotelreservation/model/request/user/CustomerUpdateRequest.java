package com.enigma.hotelreservation.model.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerUpdateRequest {
    @NotNull(message = "Customer ID cannot be blank")
    private Integer id;

    @NotBlank(message = "Identity Number cannot be blank")
    private String identityNumber;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Phone Number cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format not valid")
    private String email;
}
