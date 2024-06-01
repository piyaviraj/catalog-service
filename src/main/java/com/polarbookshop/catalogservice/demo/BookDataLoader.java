package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Profile("testData")
@RequiredArgsConstructor
public class BookDataLoader {

    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        bookRepository.deleteAll();
        Stream.of(
            Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90, "Polarsophia1"),
            Book.of("1234567892", "Polar Lights", "Funky", 12.90, "Polarsophia2")
        ).forEach(bookRepository::save);
    }
}
