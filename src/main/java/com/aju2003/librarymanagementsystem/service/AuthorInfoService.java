package com.aju2003.librarymanagementsystem.service;

import com.aju2003.librarymanagementsystem.entity.AuthorInfo;
import com.aju2003.librarymanagementsystem.repository.AuthorInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorInfoService {
    private final AuthorInfoRepository repository;


    public AuthorInfoService(AuthorInfoRepository repository) {
        this.repository = repository;
    }

    public List<AuthorInfo> getAllAuthors() {
        return repository.findAll();
    }

    public AuthorInfo getAuthorInfoById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public AuthorInfo createAuthorInfo(AuthorInfo authorInfo) {
        authorInfo.setCreationTime(LocalDate.now());
        return repository.save(authorInfo);
    }

    public AuthorInfo updateAuthorInfo(AuthorInfo authorInfo) {
        AuthorInfo existingAuthorInfo = repository.findById(authorInfo.getId()).orElseThrow(EntityNotFoundException::new);
        if (authorInfo.getFullName() != null) {
            existingAuthorInfo.setFullName(authorInfo.getFullName());
        }
        if (authorInfo.getLivedYears() != null) {
            existingAuthorInfo.setLivedYears(authorInfo.getLivedYears());
        }
        if (authorInfo.getShortInfoAboutAuthor() != null) {
            existingAuthorInfo.setShortInfoAboutAuthor(authorInfo.getShortInfoAboutAuthor());
        }

        existingAuthorInfo.setLastUpdateTime(LocalDate.now());
        return repository.save(existingAuthorInfo);
    }

    public void deleteAuthorInfoById(Long id) {
        repository.deleteById(id);
    }
}
