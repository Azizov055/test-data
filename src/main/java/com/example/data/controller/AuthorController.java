package com.example.data.controller;

import com.example.data.dto.AuthorDto;
import com.example.data.dto.BookDto;
import com.example.data.entity.Author;
import com.example.data.entity.Book;
import com.example.data.entity.LogTime;
import com.example.data.repository.AuthorRepository;
import com.example.data.repository.BookAuthorRepository;
import com.example.data.repository.BookRepository;
import com.example.data.request.AuthorRequest;
import com.example.data.request.AuthorsRequest;
import com.example.data.request.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @PostMapping
    public void createAuthor(@RequestBody AuthorRequest authorRequest) {
        Author author = Author.builder()
                .name(authorRequest.getName())
                .email(authorRequest.getEmail())
                .gender(authorRequest.getGender())
                .nationality(authorRequest.getNationality())
                .birthDate(authorRequest.getBirthDate())
                .surname(authorRequest.getSurname())
                .build();

//        List<Book> books = authorRequest.getBooks().stream()
//                        .map(book -> new Book(book.getName(), book.getPublishedAt(), book.getPageCount()))
//                        .collect(Collectors.toList());

//        author.setAllBooks(books);

        authorRepository.save(author);
    }

    @PostMapping("/{authorId}/books")
    public void addBooksToAuthor(@PathVariable Long authorId, @RequestBody List<BookRequest> bookRequests) {
        Author author = authorRepository.findById(authorId).get();
        List<Book> books = bookRequests.stream()
                .map(book -> new Book(book.getName(), book.getPublishedAt(), book.getPageCount()))
                .collect(Collectors.toList());
        author.setAllBooks(books);
        authorRepository.save(author);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId) {
        var author = authorRepository.findById(authorId).get();
        var authorDto = AuthorDto.builder()
                .gender(author.getGender())
                .name(author.getName())
                .build();

        List<BookDto> bookDtoList = new ArrayList<>();
//        for (Book book : author.getBooks()) {
//            bookDtoList.add(BookDto.builder()
//                    .id(book.getId())
//                    .name(book.getName())
//                    .build());
//        }
        authorDto.setBooks(bookDtoList);

        return ResponseEntity.ok(authorDto);
    }

    @PostMapping("/many")
    public void createAuthors(@RequestBody AuthorsRequest authorsRequest) {
        var requestedAt = LocalDateTime.now();

        List<Book> books = new ArrayList<>();
        for (var bookRequest : authorsRequest.getBooks()) {
            Book book = Book.builder()
                    .name(bookRequest.getName())
                    .pageCount(bookRequest.getPageCount())
                    .publishedAt(bookRequest.getPublishedAt())
//                    .bookAuthors(new ArrayList<>())
                    .logTime(LogTime.builder().createdAt(requestedAt).updatedAt(requestedAt).build())
                    .build();

            books.add(book);
        }

        bookRepository.saveAll(books);

        for (var authorRequest : authorsRequest.getAuthors()) {
            Author author = Author.builder()
                    .name(authorRequest.getName())
                    .email(authorRequest.getEmail())
                    .gender(authorRequest.getGender())
                    .nationality(authorRequest.getNationality())
                    .birthDate(authorRequest.getBirthDate())
                    .surname(authorRequest.getSurname())
                    .logTime(LogTime.builder().createdAt(requestedAt).updatedAt(requestedAt).build())
                    .build();

            author.setAllBooks(books);
            authorRepository.save(author);
//            bookAuthorRepository.saveAll(author.getBookAuthors());
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        List<Author> authors = authorRepository.findAllWithBooks(); // authorRepository.findAll();

        List<AuthorDto> authorDtoList = new ArrayList<>();

        for (var author : authors) {
            List<BookDto> bookDtoList = new ArrayList<>();

            for (var book : author.getBooks()) {
                bookDtoList.add(
                        BookDto.builder()
                                .name(book.getName())
                                .id(book.getId())
                                .build()
                );
            }

            var authorDto = AuthorDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .surname(author.getSurname())
                    .birthDate(author.getBirthDate())
                    .books(bookDtoList)
                    .build();

            authorDtoList.add(authorDto);
        }

        return ResponseEntity.ok(authorDtoList);
    }

}
