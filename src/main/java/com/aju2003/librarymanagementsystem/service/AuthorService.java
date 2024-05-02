package com.aju2003.librarymanagementsystem.service;

import com.aju2003.librarymanagementsystem.dto.AuthorDTO;
import com.aju2003.librarymanagementsystem.entity.Author;
import com.aju2003.librarymanagementsystem.mapper.AuthorMapper;
import com.aju2003.librarymanagementsystem.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository repository;
    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;


    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<AuthorDTO> getAllAuthors() {
        return repository
                .findAll()
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    public AuthorDTO getAuthorInfoById(Long id) {
        Author author = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return authorMapper.toDto(author);
    }

    public AuthorDTO createAuthorInfo(Author author) {
        author.setCreationTime(LocalDate.now());
        return authorMapper.toDto(repository.save(author));
    }

    public List<Author> saveAuthors(List<Author> authors) {
        List<Author> savedAuthors = new ArrayList<>();
        String currentUser = "system"; // Replace this with actual current user
        LocalDate currentTime = LocalDate.now();

        for (Author author : authors) {
            // Check if author already exists
            List<Author> existingAuthors = repository.findAll();
            Optional<Author> existingAuthor =
                    existingAuthors
                            .stream()
                            .filter(authorF -> Objects.equals(authorF.getFullName(), author.getFullName())
                                    && Objects.equals(authorF.getLivedYears(), author.getLivedYears())
                                    && Objects.equals(authorF.getAboutAuthor(), author.getAboutAuthor()))
                            .findFirst();
            if (existingAuthor.isPresent()) {
                // If author already exists, add it to the list
                savedAuthors.add(existingAuthor.get());
            } else {
                // Set audit fields
                author.setCreatedBy(currentUser);
                author.setCreationTime(currentTime);

                // Save and add the new author to the list
                savedAuthors.add(repository.save(author));
            }
        }

        return savedAuthors;
    }

    public AuthorDTO updateAuthorInfo(Author author) {
        Author existingAuthor = repository.findById(author.getId()).orElseThrow(EntityNotFoundException::new);
        if (author.getFullName() != null) {
            existingAuthor.setFullName(author.getFullName());
        }
        if (author.getLivedYears() != null) {
            existingAuthor.setLivedYears(author.getLivedYears());
        }
        if (author.getAboutAuthor() != null) {
            existingAuthor.setAboutAuthor(author.getAboutAuthor());
        }

        existingAuthor.setLastUpdateTime(LocalDate.now());
        return authorMapper.toDto(repository.save(existingAuthor));
    }

    public void deleteAuthorInfoById(Long id) {
        repository.deleteById(id);
    }

}
