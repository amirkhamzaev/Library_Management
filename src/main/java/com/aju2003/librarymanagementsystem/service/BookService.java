package com.aju2003.librarymanagementsystem.service;

import com.aju2003.librarymanagementsystem.dto.BookDTO;
import com.aju2003.librarymanagementsystem.entity.Author;
import com.aju2003.librarymanagementsystem.entity.Book;
import com.aju2003.librarymanagementsystem.entity.Genre;
import com.aju2003.librarymanagementsystem.mapper.BookMapper;
import com.aju2003.librarymanagementsystem.repository.BookInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    private final BookInfoRepository bookInfoRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookMapper bookMapper = BookMapper.INSTANCE;


    @Autowired
    public BookService(BookInfoRepository bookInfoRepository, AuthorService authorService, GenreService genreService) {
        this.bookInfoRepository = bookInfoRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    public List<BookDTO> getAllBooks() {
        return bookInfoRepository
                .findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public BookDTO getBookById(Long id) {
        Book foundBook = bookInfoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
        return bookMapper.toDto(foundBook);
    }

    public BookDTO saveBook(Book book) {
        // Set audit fields
        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();
        book.setCreatedBy(currentUser);
        book.setCreationTime(currentTime);

        List<Author> authorList = book.getAuthors();
        book.setAuthors(authorService.saveAuthors(authorList));

        List<Genre> genreList = book.getGenres();
        book.setGenres(genreService.saveGenres(genreList));
        book = bookInfoRepository.save(book);
        return bookMapper.toDto(book);
    }

    public BookDTO updateBook(Long id, Book book) {
        Book existingBook = bookInfoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with id " + id)
                );

        if (book.getName() != null) {
            existingBook.setName(book.getName());
        }
        if (book.getDescription() != null) {
            existingBook.setDescription(book.getDescription());
        }
        if (book.getLikeCount() != null) {
            existingBook.setLikeCount(book.getLikeCount());
        }
        if (book.getGenres() != null) {
            List<Genre> savedGenres = genreService.saveGenres(book.getGenres());
            existingBook.setGenres(savedGenres);
        }
        if (book.getAuthors() != null) {
            List<Author> savedAuthors = authorService.saveAuthors(book.getAuthors());
            existingBook.setAuthors(savedAuthors);
        }

        // Set audit fields
        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();
        existingBook.setLastUpdatedBy(currentUser);
        existingBook.setLastUpdateTime(currentTime);
        return bookMapper.toDto(bookInfoRepository.save(existingBook));
    }

    public void deleteBook(Long id) {
        if (bookInfoRepository.existsById(id)) {
            bookInfoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
    }
}