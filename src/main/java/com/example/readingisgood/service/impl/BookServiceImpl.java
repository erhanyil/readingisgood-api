package com.example.readingisgood.service.impl;

import com.example.readingisgood.constant.GenericError;
import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.handler.FriendlyException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findByName(bookDto.getName());
        if (optionalBook.isEmpty()) {
            Book book = optionalBook.orElse(new Book(bookDto));
            bookRepository.save(book);
            bookDto.setId(book.getId());
            return bookDto;
        }
        throw new FriendlyException(GenericError.RECORD_ALREADY_EXIST);
    }

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new FriendlyException(GenericError.RECORD_NOT_FOUND));
    }

    @Override
    @Transactional
    public BookDto update(String id, BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setStock(bookDto.getStock());
            bookRepository.save(book);
            return new BookDto(book);
        }
        throw new FriendlyException(GenericError.RECORD_NOT_FOUND);
    }
}
