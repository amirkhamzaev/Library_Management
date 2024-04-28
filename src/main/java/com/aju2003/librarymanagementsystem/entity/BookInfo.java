package com.aju2003.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer likeCount;

    @ManyToMany
    private List<BookGenreInfo> genres; //book can have multiple genres: Science, Fiction, Biography, Mystery...
    @ManyToMany
    private List<AuthorInfo> authors; //one book might be written by more than one person

    private String lastUpdatedBy;
    private LocalDate lastUpdateTime;
    private String createdBy;
    private LocalDate creationTime;


}
