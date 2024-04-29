package com.example.joins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.joins.model.book;
import com.example.joins.service.BookService;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/byAuthorAndGenre")
    public ResponseEntity<List<book>> getBooksByAuthorAndGenre(
            @RequestParam("authorId") Long authorId,
            @RequestParam("genreId") Long genreId) {
        List<book> books = bookService.getBooksByAuthorAndGenre(authorId, genreId);
        return ResponseEntity.ok(books);
    }

     @PostMapping("/create")
    public ResponseEntity<List<book>> createBook(@RequestBody List<book> bookRequest) {
        List<book> createdBook = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
}
