package com.tsaidenis.mainservice.sevice.impl;

import com.tsaidenis.mainservice.entity.Author;
import com.tsaidenis.mainservice.repository.AuthorRepository;
import com.tsaidenis.mainservice.sevice.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findAuthorByName(name);
    }
}
