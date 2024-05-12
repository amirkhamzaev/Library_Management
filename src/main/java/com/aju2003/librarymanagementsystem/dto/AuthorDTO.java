package com.aju2003.librarymanagementsystem.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String fullName;
    private String livedYears;
    private String aboutAuthor;
    private List<BookDTO> bookDTOS;

    private String lastUpdatedBy;
    private String lastUpdateTime;
    private String createdBy;
    private String creationTime;
}
