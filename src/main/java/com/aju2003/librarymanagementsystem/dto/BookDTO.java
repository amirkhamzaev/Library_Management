package com.aju2003.librarymanagementsystem.dto;

import lombok.*;

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