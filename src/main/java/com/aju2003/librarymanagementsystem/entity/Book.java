package com.aju2003.librarymanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"lastUpdatedBy", "createdBy"})
@ToString(exclude = {"lastUpdatedBy", "createdBy"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be longer than 100 characters")
    private String name;

    @Size(max = 500, message = "Description should not be longer than 500 characters")
    private String description;

    @NotNull(message = "Like count is mandatory")
    private Integer likeCount;

    @ManyToMany
    private List<Genre> genres; //book can have multiple genres: Science, Fiction, Biography, Mystery...

    @ManyToMany
    private List<Author> authors; //one book might be written by more than one person

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likedBooks")
    private List<UserInfo> usersWhoLiked; //users who liked this book

    private String lastUpdatedBy;
    private LocalDate lastUpdateTime;
    private String createdBy;
    private LocalDate creationTime;
}