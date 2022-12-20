package com.example.data.controller;

import com.example.data.dto.ValidationErrorDto;
import com.example.data.request.BookRequest;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  private final Validator validator;

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookRequest) {
      var validatorErrors = validator.validate(bookRequest);
      if (!validatorErrors.isEmpty()) {

        return ResponseEntity.badRequest().body(
            validatorErrors.stream().map(e -> ValidationErrorDto.builder()
                    .errorMessage(e.getMessage())
                    .value(String.valueOf(e.getInvalidValue()))
                    .build())
                .collect(Collectors.toList())
        );
      }

      return ResponseEntity.ok(bookRequest);
    }

}
