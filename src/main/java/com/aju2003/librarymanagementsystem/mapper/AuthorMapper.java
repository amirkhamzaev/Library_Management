package com.aju2003.librarymanagementsystem.mapper;

import com.aju2003.librarymanagementsystem.dto.AuthorDTO;
import com.aju2003.librarymanagementsystem.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(source = "books", target = "bookDTOS")
    AuthorDTO toDto(Author author);

    @Mapping(source = "bookDTOS", target = "books")
    Author toEntity(AuthorDTO authorDTO);
}