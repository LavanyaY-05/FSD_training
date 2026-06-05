package com.hiretrack.Mapper;

import com.hiretrack.dto.BookReqDto;
import com.hiretrack.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapDtoToEntity(BookReqDto dto){
        Book book = new Book();
        book.setTitle(dto.title());
        book.setSummary(dto.summary());
        return book;
    }
}
