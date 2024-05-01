package com.aju2003.librarymanagementsystem.controller;

import com.aju2003.librarymanagementsystem.entity.AuthorInfo;
import com.aju2003.librarymanagementsystem.service.AuthorInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/author")
public class AuthorInfoController {
    private final AuthorInfoService authorInfoService;

    public AuthorInfoController(AuthorInfoService authorInfoService) {
        this.authorInfoService = authorInfoService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorInfo>> getAllAuthors() {
        return new ResponseEntity<>(authorInfoService.getAllAuthors(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuthorInfo> getAuthorById(@PathVariable Long id) {
        AuthorInfo authorInfo;
        try {
            authorInfo = authorInfoService.getAuthorInfoById(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(authorInfo, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AuthorInfo> createAuthor(@RequestBody AuthorInfo authorInfo) {
        return new ResponseEntity<>(authorInfoService.createAuthorInfo(authorInfo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorInfo> updateAuthor(@PathVariable Long id, @RequestBody AuthorInfo authorInfo) {
        AuthorInfo existingAuthorInfo = authorInfoService.getAuthorInfoById(id);
        if (existingAuthorInfo == null) {
            return ResponseEntity.notFound().build();
        }
        authorInfo.setId(id); // Ensure the ID is set on the authorInfo object
        return new ResponseEntity<>(authorInfoService.updateAuthorInfo(authorInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorInfoService.deleteAuthorInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}