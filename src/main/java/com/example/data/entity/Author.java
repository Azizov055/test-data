package com.example.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String name;

    @Column(name = "last_name", length = 50, nullable = false)
    private String surname;

    private String email;
    private LocalDate birthDate;
    private String nationality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private LogTime logTime;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<BookAuthor> bookAuthors;

    public void setAllBooks(List<Book> books) {
        List<BookAuthor> bookAuthorList = new ArrayList<>();
        for (var book : books) {
            var bookAuthor = BookAuthor.builder()
                    .key(BookAuthorKey.builder().authorId(this.id).bookId(book.getId()).build())
                    .author(this)
                    .book(book)
                    .createdAt(LocalDate.now())
                    .build();
            book.getBookAuthors().add(bookAuthor);
        }

        this.bookAuthors = bookAuthorList;
    }

}
