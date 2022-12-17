package com.example.data.repository;

import com.example.data.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "books")
    List<Author> findAll();

    @Query("select a from Author a join fetch Book b on a.id = b.author.id")
    List<Author> findAllWithBooks();

}
