package com.example.data.request;

import com.example.data.entity.Book;
import com.example.data.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String nationality;
    private Gender gender;
//    private List<Book> books = new ArrayList<>();

}
