package com.polarbookshop.catalogservice.exceptions;

public class BookAlreadyExistException extends RuntimeException {

    public BookAlreadyExistException(String isbn) {
        super(isbn);
    }

}
