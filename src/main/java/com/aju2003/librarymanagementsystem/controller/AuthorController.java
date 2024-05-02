package com.aju2003.librarymanagementsystem.controller;

import com.aju2003.librarymanagementsystem.dto.AuthorDTO;
import com.aju2003.librarymanagementsystem.entity.Author;
import com.aju2003.librarymanagementsystem.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        AuthorDTO author;
        try {
            author = authorService.getAuthorInfoById(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.createAuthorInfo(author), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        AuthorDTO existingAuthor = authorService.getAuthorInfoById(id);
        if (existingAuthor == null) {
            return ResponseEntity.notFound().build();
        }
        author.setId(id); // Ensure the ID is set on the author object
        return new ResponseEntity<>(authorService.updateAuthorInfo(author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}