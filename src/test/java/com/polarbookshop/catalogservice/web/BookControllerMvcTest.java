package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.exceptions.BookNotFoundException;
import com.polarbookshop.catalogservice.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerMvcTest {

    @MockBean private BookService bookService;//These aren't just mocked, they are included under the applciation context
    @Autowired private MockMvc mockMvc;

    //@Test
    void testGetBookOnNotExistingIsbnReturnsNotFound() throws Exception {
        String isbn = "123";
        given(bookService.viewBookDetails(isbn))
            .willThrow(new BookNotFoundException(isbn));

        mockMvc.perform(get("/books/"+isbn))
            .andExpect(status().isNotFound());
    }
}
