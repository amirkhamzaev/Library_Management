package com.aju2003.librarymanagementsystem.mapper;

import com.aju2003.librarymanagementsystem.dto.BookDTO;
import com.aju2003.librarymanagementsystem.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AuthorMapper.class, GenreMapper.class, UserInfoMapper.class})
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "authors", target = "authorDTOS")
    @Mapping(source = "genres", target = "genreDTOS")
    @Mapping(source = "usersWhoLiked", target = "userInfoDTOS")
    BookDTO toDto(Book book);

    @Mapping(source = "authorDTOS", target = "authors")
    @Mapping(source = "genreDTOS", target = "genres")
    @Mapping(source = "userInfoDTOS", target = "usersWhoLiked")
    Book toEntity(BookDTO bookDTO);
}