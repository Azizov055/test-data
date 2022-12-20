package com.example.data.request;

import com.example.data.validator.Currency;
import com.example.data.validator.Published;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "Name field is mandatory")
    @Size(max = 10, message = "Length of name field must be less than 10")
    private String name;

    @Published
    private LocalDate publishedAt;

    @Min(value = 1, message = "Page count must be min 1")
    @Max(value = 500, message = "Page counts must be max 500")
    private int pageCount;

    @PositiveOrZero
    private double price;

    @Currency
    private String currency;

    @Email(message = "Invalid author email")
    private String authorEmail;

    @Pattern(regexp = "^(AZ|US|RU)[0-9]{4}?$")
    private String postalCode;

    @AssertTrue(message = "Published field must be true")
    private Boolean published;

}
