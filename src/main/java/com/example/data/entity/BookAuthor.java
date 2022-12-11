package com.example.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "books_authors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthor {

    @EmbeddedId
    private BookAuthorKey key;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("authorId")
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("bookId")
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    private LocalDate createdAt;

}
