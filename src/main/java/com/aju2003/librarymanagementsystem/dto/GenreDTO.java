package com.aju2003.librarymanagementsystem.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {
    private Long id;
    private String name;
    private String description;
    private List<BookDTO> bookDTOS;

    private String lastUpdatedBy;
    private String lastUpdateTime;
    private String createdBy;
    private String creationTime;
}
