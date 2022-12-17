package com.example.data.controller;

import com.example.data.dto.UserDto;
import com.example.data.entity.Address;
import com.example.data.entity.Gender;
import com.example.data.entity.User;
import com.example.data.repository.AddressRepository;
import com.example.data.repository.UserRepository;
import com.example.data.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(
              @RequestParam String address,
              @RequestParam Gender gender,
              @RequestParam String name) {
        return ResponseEntity.ok(userRepository.search(address, gender, name).stream()
                .map(u -> UserDto.builder()
                        .id(u.getId())
                        .gender(u.getGender())
                        .name(u.getName())
                        .build())
                .collect(Collectors.toList()));
    }

    @GetMapping("/nativeSearch")
    public ResponseEntity<List<UserDto>> nativeSearchUsers(
            @RequestParam Gender gender,
            @RequestParam String name) {
        return ResponseEntity.ok(userRepository.nativeSearch(gender.name(), name).stream()
                .map(u -> UserDto.builder()
                        .id(u.getId())
                        .gender(u.getGender())
                        .name(u.getName())
                        .build())
                .collect(Collectors.toList()));
    }

    @GetMapping("/search/address")
    public ResponseEntity<List<UserDto>> searchUsersByAddress(
            @RequestParam String postalCode) {
        return ResponseEntity.ok(userRepository.searchByAddress(addressRepository.findByPostalCode(postalCode)).stream()
                .map(u -> UserDto.builder()
                        .id(u.getId())
                        .gender(u.getGender())
                        .name(u.getName())
                        .build())
                .collect(Collectors.toList()));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> searchUsersByAddress() {
        List<UserDto> userList = new ArrayList<>();
        for (User user : userRepository.findAll()) {

            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .gender(user.getGender())
                    .name(user.getName())
                    .build();

            userList.add(userDto);

            userDto.setCity(user.getAddress().getCity());
            userDto.setCountry(user.getAddress().getCountry());
        }

        return ResponseEntity.ok(userList);
    }

}
