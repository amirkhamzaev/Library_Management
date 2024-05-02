package com.aju2003.librarymanagementsystem.entity;

import com.aju2003.librarymanagementsystem.entity.enums.UserRole;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"lastUpdatedBy", "createdBy"})
@ToString(exclude = {"lastUpdatedBy", "createdBy"})
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @Size(max = 200, message = "About user should not be longer than 200 characters")
    private String aboutUser;

    @NotBlank(message = "Phone1 is mandatory")
    @Size(min = 10, max = 15, message = "Phone1 should be between 10 and 15 characters")
    private String phone1;

    @Size(min = 10, max = 15, message = "Phone2 should be between 10 and 15 characters")
    private String phone2;

    @NotBlank(message = "Address line 1 is mandatory")
    @Size(max = 100, message = "Address line 1 should not be longer than 100 characters")
    private String addressLine1;

    @NotBlank(message = "District is mandatory")
    @Size(max = 50, message = "District should not be longer than 50 characters")
    private String district;

    @NotBlank(message = "Province is mandatory")
    @Size(max = 50, message = "Province should not be longer than 50 characters")
    private String province;

    @NotBlank(message = "Country is mandatory")
    @Size(max = 50, message = "Country should not be longer than 50 characters")
    private String country;

    @ElementCollection(targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    private List<UserRole> userRoles;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Book> likedBooks;

    private String lastUpdatedBy;
    private LocalDate lastUpdateTime;
    private String createdBy;
    private LocalDate creationTime;
}