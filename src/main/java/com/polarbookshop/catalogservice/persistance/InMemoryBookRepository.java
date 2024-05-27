package com.polarbookshop.catalogservice.persistance;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InMemoryBookRepository implements BookRepository {

    private final HashMap<String, Book> dataSource;

    @Override
    public Iterable<Book> findAll() {
        return dataSource.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(dataSource.get(isbn)) : Optional.empty();
    }

    @Override
    public Book save(Book book) {
        dataSource.put(book.isbn(), book);
        return book;
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return dataSource.containsKey(isbn);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        dataSource.remove(isbn);
    }
}
