package com.example.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private LocalDate publishedAt;
    private int pageCount;

    @Embedded
    private LogTime logTime;

//    @ManyToMany
//    @JoinTable(
//            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
//    )
//    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors = new ArrayList<>();

    public Book(String name, LocalDate publishedAt, int pageCount) {
        this.name = name;
        this.publishedAt = publishedAt;
        this.pageCount = pageCount;
    }

}
