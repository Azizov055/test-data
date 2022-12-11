package com.example.data.controller;

import com.example.data.entity.Address;
import com.example.data.entity.User;
import com.example.data.repository.AddressRepository;
import com.example.data.repository.UserRepository;
import com.example.data.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .gender(userRequest.getGender())
                .build();
        Address address = Address.builder()
                .city(userRequest.getCity())
                .street(userRequest.getStreet())
                .postalCode(userRequest.getPostalCode())
                .country(userRequest.getCountry())
                .build();

//        addressRepository.save(address);
        user.setUserAddress(address);
        userRepository.save(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

}
