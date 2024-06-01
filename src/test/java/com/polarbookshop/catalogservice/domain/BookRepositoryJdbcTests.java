package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.configurations.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {

    @Autowired private BookRepository bookRepository;
    @Autowired private JdbcAggregateTemplate aggregateTemplate;

    //@Test
    void testFindBookByIsbnOnBookExists() {
        String isbn = "1234561237";
        var book = Book.of(isbn, "Title", "Author", 12.90, "Polarsophia");
        aggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(isbn);

        assertTrue(actualBook.isPresent());
        assertThat(actualBook.get().isbn(), is(isbn));
    }
}
