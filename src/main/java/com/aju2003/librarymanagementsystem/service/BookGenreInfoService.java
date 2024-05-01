package com.aju2003.librarymanagementsystem.service;

import com.aju2003.librarymanagementsystem.entity.BookGenreInfo;
import com.aju2003.librarymanagementsystem.repository.BookGenreInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookGenreInfoService {
    private final BookGenreInfoRepository repository;

    @Autowired
    public BookGenreInfoService(BookGenreInfoRepository repository) {
        this.repository = repository;
    }

    public List<BookGenreInfo> getAllGenres() {
        return repository.findAll();
    }

    public BookGenreInfo getGenreById(Long id) {
        Optional<BookGenreInfo> genre = repository.findById(id);
        if (genre.isEmpty()) {
            throw new EntityNotFoundException("BookGenreInfo not found with id " + id);
        }
        return genre.get();
    }

    public BookGenreInfo updateGenre(Long id, BookGenreInfo bookGenreInfo) {
        BookGenreInfo existingGenre = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BookGenreInfo not found with id " + id));

        if (bookGenreInfo.getGenreName() != null) {
            existingGenre.setGenreName(bookGenreInfo.getGenreName());
        }
        if (bookGenreInfo.getDescription() != null) {
            existingGenre.setDescription(bookGenreInfo.getDescription());
        }

        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();
        existingGenre.setLastUpdatedBy(currentUser);
        existingGenre.setLastUpdateTime(currentTime);

        return repository.save(existingGenre);
    }

    public BookGenreInfo saveGenre(BookGenreInfo bookGenreInfo) {
        // Check if genreName already exists
        List<BookGenreInfo> existingGenres = repository.findAll();
        if (existingGenres.stream().anyMatch(genre -> genre.getGenreName().equals(bookGenreInfo.getGenreName()))) {
            throw new IllegalArgumentException("Genre with name " + bookGenreInfo.getGenreName() + " already exists");
        }

        // Set audit fields
        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();
        bookGenreInfo.setLastUpdatedBy(currentUser);
        bookGenreInfo.setLastUpdateTime(currentTime);
        bookGenreInfo.setCreatedBy(currentUser);
        bookGenreInfo.setCreationTime(currentTime);

        // Save and return the new genre
        return repository.save(bookGenreInfo);
    }

    public void deleteGenre(Long id) {
    if (repository.existsById(id)) {
        repository.deleteById(id);
    } else {
        throw new EntityNotFoundException("BookGenreInfo not found with id " + id);
    }
}
}