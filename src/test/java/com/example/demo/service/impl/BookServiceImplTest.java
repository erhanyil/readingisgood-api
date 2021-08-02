package com.example.demo.service.impl;

import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.handler.FriendlyException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    public void create() {
        BookDto bookDto = new BookDto();
        Book book = new Book();
        when(bookRepository.findByName(anyString())).thenReturn(java.util.Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book);
        BookDto result = bookService.create(bookDto);
        Assert.assertEquals(result, bookDto);
    }

    @Test(expected = Exception.class)
    public void create_is_empty() {
        BookDto bookDto = new BookDto();
        when(bookRepository.findByName(anyString())).thenReturn(Optional.empty());
        bookService.create(bookDto);
    }

    @Test(expected = FriendlyException.class)
    public void findById_with_empty() {
        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());
        bookService.findById("1");
    }

    @Test
    public void findById() {
        Book book = new Book();
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));
        Book result = bookService.findById("1");
        Assert.assertEquals(result, book);
    }

    @Test
    public void update() {
        Book book = new Book();
        BookDto bookDto = new BookDto();
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book);
        BookDto result = bookService.update("1", bookDto);
        Assert.assertEquals(result.getId(), bookDto.getId());
    }

    @Test(expected = FriendlyException.class)
    public void update_with_empty() {
        BookDto bookDto = new BookDto();
        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());
        bookService.update("1", bookDto);
    }
}