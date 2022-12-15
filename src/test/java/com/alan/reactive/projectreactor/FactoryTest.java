package com.alan.reactive.projectreactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public class FactoryTest {
    @Test
    void create() {
//        Flux.create();
        Flux.just(1,2,3).log().subscribe(System.out::println).dispose();
    }

    @Test
    void defer() {
        Flux<String> flux=Flux.defer(()->Flux.just(LocalDateTime.now().toString()));
        flux.subscribe(System.out::println);
    }
}
