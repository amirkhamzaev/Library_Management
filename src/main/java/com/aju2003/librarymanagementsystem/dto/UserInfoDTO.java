package com.aju2003.librarymanagementsystem.dto;

import com.aju2003.librarymanagementsystem.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private Long id;
    private String name;
    private String aboutUser;
    private String phone1;
    private String phone2;
    private String addressLine1;
    private String district;
    private String province;
    private String country;
    private List<UserRole> roles;
    private List<BookDTO> bookDTOS;

    private String lastUpdatedBy;
    private String lastUpdateTime;
    private String createdBy;
    private String creationTime;
}
