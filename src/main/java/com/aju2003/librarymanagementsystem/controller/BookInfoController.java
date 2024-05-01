package com.aju2003.librarymanagementsystem.controller;

import com.aju2003.librarymanagementsystem.entity.BookInfo;
import com.aju2003.librarymanagementsystem.service.BookInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookInfoController {
    private final BookInfoService service;

    @Autowired
    public BookInfoController(BookInfoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookInfo>> getAllBooks() {
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookInfo> getBookById(@PathVariable Long id) {
        try {
            BookInfo bookInfo = service.getBookById(id);
            return new ResponseEntity<>(bookInfo, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody BookInfo bookInfo) {
        try {
            BookInfo savedBook = service.saveBook(bookInfo);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookInfo> updateBook(@PathVariable Long id, @RequestBody BookInfo bookInfo) {
        return new ResponseEntity<>(service.updateBook(id, bookInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            service.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}