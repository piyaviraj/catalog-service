package com.polarbookshop.catalogservice.persistance;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class InMemoryBookRepository {

    private final HashMap<String, Book> dataSource;

    public Iterable<Book> findAll() {
        return dataSource.values();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(dataSource.get(isbn)) : Optional.empty();
    }

    public Book save(Book book) {
        dataSource.put(book.isbn(), book);
        return book;
    }

    public boolean existsByIsbn(String isbn) {
        return dataSource.containsKey(isbn);
    }

    public void deleteByIsbn(String isbn) {
        dataSource.remove(isbn);
    }
}
