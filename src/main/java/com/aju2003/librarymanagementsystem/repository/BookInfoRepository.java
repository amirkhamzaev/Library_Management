package com.aju2003.librarymanagementsystem.repository;

import com.aju2003.librarymanagementsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends JpaRepository<Book, Long> {
}
