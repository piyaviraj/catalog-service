package com.polarbookshop.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidateBookAllFieldsValid() {
        var book = Book.of("9780596520687", "Title", "Author", 9.90, "Polarsophia");
        var violations = validator.validate(book);
        assertThat(violations, emptyIterable());
    }

    @Test
    void testValidateBookIsbnInvalid() {
        var book = Book.of("invalid", "Title", "Author", 9.0, "Polarsophia");
        var violations = validator.validate(book);
        assertThat(violations, iterableWithSize(1));
        assertThat(violations.stream().map(ConstraintViolation::getMessage).toList(),
            contains("The ISBN format must be valid."));
    }
}
