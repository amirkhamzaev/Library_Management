package com.aju2003.librarymanagementsystem.mapper;

import com.aju2003.librarymanagementsystem.dto.GenreDTO;
import com.aju2003.librarymanagementsystem.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    @Mapping(source = "books", target = "bookDTOS")
    GenreDTO toDto(Genre genre);

    @Mapping(source = "bookDTOS", target = "books")
    Genre toEntity(GenreDTO genreDTO);
}

