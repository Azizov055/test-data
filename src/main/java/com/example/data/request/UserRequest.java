package com.example.data.request;

import com.example.data.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;
    private String surname;
    private Gender gender;
    private String city;
    private String street;
    private String country;
    private String postalCode;

}
