package com.example.readingisgood.controller;

import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDto add(@Valid @RequestBody BookDto bookDto) {
        return this.bookService.create(bookDto);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable String id, @RequestBody BookDto bookDto) {
        return this.bookService.update(id, bookDto);
    }
}
