package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired private JacksonTester<Book> bookJacksonTester;

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1234567890", "Title", "Author", 9.90);
        var jsonContent = bookJacksonTester.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo("Title");
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo("Author");
    }
}
