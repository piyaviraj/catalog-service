package com.polarbookshop.catalogservice.services;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.exceptions.BookAlreadyExistException;
import com.polarbookshop.catalogservice.exceptions.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
            .map(exitingBook -> {
                var bookToEdit = new Book(
                    exitingBook.id(),
                    exitingBook.isbn(),
                    book.title(),
                    book.author(),
                    book.price(),
                    book.isbn(),
                    exitingBook.createdDate(),
                    exitingBook.lastModifiedDate(),
                    exitingBook.version()
                );
                return bookRepository.save(bookToEdit);
            }).orElseGet(() -> addBookToCatalog(book));
    }
}
