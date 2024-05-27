package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
    webEnvironment = RANDOM_PORT
)
class CatalogServiceApplicationTests {

    @Autowired private WebTestClient webTestClient;

    @Test
    void createBookOnPostRequestBook() {
        var expectedBook = new Book("1231231231", "Title", "Äuthor", 9.90);
        webTestClient.post()
            .uri("/books")
            .bodyValue(expectedBook)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Book.class).value(actualBook -> {
                assertThat(actualBook, notNullValue());
                assertThat(actualBook.isbn(), equalTo(expectedBook.isbn()));
            });
    }

}
