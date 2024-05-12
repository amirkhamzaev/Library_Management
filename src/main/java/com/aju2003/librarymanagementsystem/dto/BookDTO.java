package com.aju2003.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private String description;
    private Integer likeCount;
    private List<GenreDTO> genreDTOS;
    private List<AuthorDTO> authorDTOS;
    private List<UserInfoDTO> userInfoDTOS;

    private String lastUpdatedBy;
    private LocalDate lastUpdateTime;
    private String createdBy;
    private LocalDate creationTime;
}