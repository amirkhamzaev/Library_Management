package com.aju2003.librarymanagementsystem.repository;

import com.aju2003.librarymanagementsystem.entity.AuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorInfoRepository extends JpaRepository<AuthorInfo, Long> {
}