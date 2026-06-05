package com.hiretrack.service;

import com.hiretrack.exceptions.ResourceNotFoundException;
import com.hiretrack.model.Author;
import com.hiretrack.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    public Author getById(int id) {

        return authorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid Id"));

    }
}
