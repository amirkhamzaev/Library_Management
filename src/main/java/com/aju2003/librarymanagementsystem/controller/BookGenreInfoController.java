package com.aju2003.librarymanagementsystem.controller;

import com.aju2003.librarymanagementsystem.entity.BookGenreInfo;
import com.aju2003.librarymanagementsystem.service.BookGenreInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/genre")
public class BookGenreInfoController {
    private final BookGenreInfoService service;

    @Autowired
    public BookGenreInfoController(BookGenreInfoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookGenreInfo>> getAllGenres() {
        return new ResponseEntity<>(service.getAllGenres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookGenreInfo> getGenreById(@PathVariable Long id) {
        try {
            BookGenreInfo bookGenreInfo = service.getGenreById(id);
            return new ResponseEntity<>(bookGenreInfo, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveGenre(@RequestBody BookGenreInfo bookGenreInfo) {
        try {
            BookGenreInfo savedGenre = service.saveGenre(bookGenreInfo);
            return new ResponseEntity<>(savedGenre, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookGenreInfo> updateGenre(@PathVariable Long id, @RequestBody BookGenreInfo bookGenreInfo) {
        return new ResponseEntity<>(service.updateGenre(id, bookGenreInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        try {
            service.deleteGenre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}