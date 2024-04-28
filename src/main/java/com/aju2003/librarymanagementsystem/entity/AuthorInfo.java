package com.aju2003.librarymanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String livedYears; //ex. {1874-1955} years or {1968-present}
    private String shortInfoAboutAuthor;

    private String lastUpdatedBy;
    private LocalDate lastUpdateTime;
    private String createdBy;
    private LocalDate creationTime;
}
