package com.aju2003.librarymanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is mandatory")
    @Size(max = 100, message = "Full name should not be longer than 100 characters")
    private String fullName;

    @NotBlank(message = "Lived years is mandatory")
    @Size(max = 50, message = "Lived years should not be longer than 50 characters")
    private String livedYears; //ex. {1874-1955} years or {1968-present}

    @Size(max = 500, message = "About author should not be longer than 500 characters")
    private String aboutAuthor;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    private List<Book> books;

    private String lastUpdatedBy;
    private LocalDate lastUpdateTime;
    private String createdBy;
    private LocalDate creationTime;
}