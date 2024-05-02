package com.aju2003.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
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
