package com.example.readingisgood.service;

import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.model.Book;

public interface BookService {

    BookDto create(BookDto bookDto);

    Book findById(String id);

    BookDto update(String id, BookDto bookDto);
}
