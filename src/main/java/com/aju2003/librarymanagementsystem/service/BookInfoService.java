package com.aju2003.librarymanagementsystem.service;

import com.aju2003.librarymanagementsystem.entity.AuthorInfo;
import com.aju2003.librarymanagementsystem.entity.BookGenreInfo;
import com.aju2003.librarymanagementsystem.entity.BookInfo;
import com.aju2003.librarymanagementsystem.repository.AuthorInfoRepository;
import com.aju2003.librarymanagementsystem.repository.BookGenreInfoRepository;
import com.aju2003.librarymanagementsystem.repository.BookInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookInfoService {
    private final BookInfoRepository bookInfoRepository;
    private final AuthorInfoRepository authorInfoRepository;
    private final BookGenreInfoRepository bookGenreInfoRepository;

    @Autowired
    public BookInfoService(BookInfoRepository bookInfoRepository, AuthorInfoRepository authorInfoRepository, BookGenreInfoRepository bookGenreInfoRepository) {
        this.bookInfoRepository = bookInfoRepository;
        this.authorInfoRepository = authorInfoRepository;
        this.bookGenreInfoRepository = bookGenreInfoRepository;
    }

    public List<BookInfo> getAllBooks() {
        return bookInfoRepository.findAll();
    }

    public BookInfo getBookById(Long id) {
        return bookInfoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("BookInfo not found with id " + id));
    }

    public BookInfo saveBook(BookInfo bookInfo) {
        // Set audit fields
        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();
        bookInfo.setLastUpdatedBy(currentUser);
        bookInfo.setLastUpdateTime(currentTime);

        List<AuthorInfo> authorInfoList = bookInfo.getAuthors();
        authorInfoRepository.saveAll(authorInfoList);
        List<BookGenreInfo> bookGenreInfoList =bookInfo.getGenres();
        bookGenreInfoRepository.saveAll(bookGenreInfoList);

        return bookInfoRepository.save(bookInfo);
    }

    public BookInfo updateBook(Long id, BookInfo bookInfo) {
        BookInfo existingBook = getBookById(id);

        if (bookInfo.getName() != null) {
            existingBook.setName(bookInfo.getName());
        }
        if (bookInfo.getDescription() != null) {
            existingBook.setDescription(bookInfo.getDescription());
        }
        if (bookInfo.getLikeCount() != null) {
            existingBook.setLikeCount(bookInfo.getLikeCount());
        }
        if (bookInfo.getGenres() != null) {
            existingBook.setGenres(bookInfo.getGenres());
        }
        if (bookInfo.getAuthors() != null) {
            existingBook.setAuthors(bookInfo.getAuthors());
        }


        // Set audit fields
        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();
        bookInfo.setLastUpdatedBy(currentUser);
        bookInfo.setLastUpdateTime(currentTime);
        bookInfo.setCreatedBy(currentUser);
        bookInfo.setCreationTime(currentTime);
        return bookInfoRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if (bookInfoRepository.existsById(id)) {
            bookInfoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
    }
}