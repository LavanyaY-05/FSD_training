package com.hiretrack.controller;

import com.hiretrack.dto.BookReqDto;
import com.hiretrack.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {

   private final BookService bookService;
    @PostMapping("/addBook/{id}")
    public void addBook(@PathVariable int id,
                        @Valid @RequestBody BookReqDto dto){

        bookService.add(id,dto);

    }
}
