package com.aju2003.librarymanagementsystem.service;

import com.aju2003.librarymanagementsystem.dto.UserInfoDTO;
import com.aju2003.librarymanagementsystem.entity.UserInfo;
import com.aju2003.librarymanagementsystem.mapper.UserInfoMapper;
import com.aju2003.librarymanagementsystem.repository.UserInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper = UserInfoMapper.INSTANCE;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfoDTO getUserInfo(Long id) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(id);
        if (userInfo.isEmpty()) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        return userInfoMapper.toDto(userInfo.get());
    }

}
