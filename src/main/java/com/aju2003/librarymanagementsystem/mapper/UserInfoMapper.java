package com.aju2003.librarymanagementsystem.mapper;


import com.aju2003.librarymanagementsystem.dto.UserInfoDTO;
import com.aju2003.librarymanagementsystem.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserInfoMapper {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    UserInfoDTO toDto(UserInfo userInfo);

    UserInfo toEntity(UserInfoDTO userInfoDTO);
}