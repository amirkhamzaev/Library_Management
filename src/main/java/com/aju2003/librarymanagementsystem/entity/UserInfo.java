package com.aju2003.librarymanagementsystem.entity;

import com.aju2003.librarymanagementsystem.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String aboutUser;
    private String phone1;
    private String phone2;
    private String addressLine1;
    private String district;
    private String province;
    private String country;

    private List<UserRole> userRoles;
    @ManyToMany
    private List<BookInfo> likedBooks;

    private String lastUpdatedBy;
    private LocalDate lastUpdateTime;
    private String createdBy;
    private LocalDate creationTime;
}
