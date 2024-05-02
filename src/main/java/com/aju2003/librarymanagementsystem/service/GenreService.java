package com.aju2003.librarymanagementsystem.service;

import com.aju2003.librarymanagementsystem.dto.GenreDTO;
import com.aju2003.librarymanagementsystem.entity.Genre;
import com.aju2003.librarymanagementsystem.mapper.GenreMapper;
import com.aju2003.librarymanagementsystem.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository repository;
    private final GenreMapper genreMapper = GenreMapper.INSTANCE;

    @Autowired
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<GenreDTO> getAllGenres() {
        return repository.findAll()
                .stream()
                .map(genreMapper::toDto)
                .toList();
    }

    public GenreDTO getGenreById(Long id) {
        Optional<Genre> genre = repository.findById(id);
        if (genre.isEmpty()) {
            throw new EntityNotFoundException("Genre not found with id " + id);
        }
        return genreMapper.toDto(genre.get());
    }

    public GenreDTO updateGenre(Long id, Genre genre) {
        Genre existingGenre = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Genre not found with id " + id));

        if (genre.getName() != null) {
            existingGenre.setName(genre.getName());
        }
        if (genre.getDescription() != null) {
            existingGenre.setDescription(genre.getDescription());
        }

        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();
        existingGenre.setLastUpdatedBy(currentUser);
        existingGenre.setLastUpdateTime(currentTime);

        return genreMapper.toDto(repository.save(existingGenre));
    }

    public GenreDTO saveGenre(Genre bookGenre) {
        // Check if genreName already exists
        List<Genre> existingGenres = repository.findAll();
        if (existingGenres.stream().anyMatch(genre -> genre.getName().equals(bookGenre.getName()))) {
            throw new IllegalArgumentException("Genre with name " + bookGenre.getName() + " already exists");
        }

        // Set audit fields
        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();
        bookGenre.setCreatedBy(currentUser);
        bookGenre.setCreationTime(currentTime);

        // Save and return the new genre
        return genreMapper.toDto(repository.save(bookGenre));
    }

    public List<Genre> saveGenres(List<Genre> genres) {
        List<Genre> savedGenres = new ArrayList<>();
        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();

        for (Genre genre : genres) {
            // Check if genreName already exists
            List<Genre> existingGenres = repository.findAll();
            Optional<Genre> existingGenre = existingGenres.stream()
                    .filter(genre1 -> Objects.equals(genre1.getName(), genre.getName()))
                    .findFirst();

            if (existingGenre.isPresent()) {
                // If genre already exists, add it to the list
                savedGenres.add(existingGenre.get());
            } else {
                // Set audit fields
                genre.setCreatedBy(currentUser);
                genre.setCreationTime(currentTime);

                // Save and add the new genre to the list
                savedGenres.add(repository.save(genre));
            }
        }

        return savedGenres;
    }

    public void deleteGenre(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Genre not found with id " + id);
        }
    }
}