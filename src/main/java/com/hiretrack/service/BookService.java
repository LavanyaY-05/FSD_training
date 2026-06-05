package com.hiretrack.service;

import com.hiretrack.Mapper.BookMapper;
import com.hiretrack.dto.BookReqDto;
import com.hiretrack.model.Author;
import com.hiretrack.model.Book;
import com.hiretrack.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private final AuthorService authorService;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    public void add(int id, @Valid BookReqDto dto) {

        // get the author
        Author author = authorService.getById(id);

        //add the book details
         Book book=  bookMapper.mapDtoToEntity(dto);

         book.setAuthor(author);

        // save it
      bookRepository.save(book);


    }
}
