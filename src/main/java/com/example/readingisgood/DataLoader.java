package com.example.readingisgood;

import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader {

    @Bean
    public boolean DataLoader(PasswordEncoder passwordEncoder, CustomerRepository customerRepository, BookRepository bookRepository) {

        if(customerRepository.count() == 0) {
            Customer customer = new Customer();
            customer.setFirstName("Erhan");
            customer.setLastName("Yıldırım");
            customer.setEmail("erhan_yil@windowslive.com");
            customer.setPassword(passwordEncoder.encode("123"));
            customerRepository.save(customer);
        }

        if(bookRepository.count() == 0) {
            Book book_1 = new Book();
            book_1.setName("Untamed");
            book_1.setAuthor("Glennon Doyle");
            book_1.setPrice(40F);
            book_1.setStock((short) 150);

            Book book_2 = new Book();
            book_2.setName("'Caste: The Origins of Our Discontents");
            book_2.setAuthor("Isabel Wilkerson");
            book_2.setPrice(45F);
            book_2.setStock((short) 100);

            Book book_3 = new Book();
            book_3.setName("Greenlights");
            book_3.setAuthor("'Matthew McConaughey");
            book_3.setPrice(60F);
            book_3.setStock((short) 250);

            Book book_4 = new Book();
            book_4.setName("'Brit Bennett");
            book_4.setAuthor("The Vanishing Half");
            book_4.setPrice(20F);
            book_4.setStock((short) 50);

            Book book_5 = new Book();
            book_5.setName("Grisham");
            book_5.setAuthor("John Grisham");
            book_5.setPrice(80F);
            book_5.setStock((short) 1);

            bookRepository.saveAll(Arrays.asList(book_1, book_2, book_3, book_4, book_5));
        }
        return true;
    }
}