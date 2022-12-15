package com.alan.reactive.projectreactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ContextTest {
    @Test
    void name() {
        Mono<String> mono = Mono.deferContextual(context -> Mono.just(String.format("subscriber:" + context.get("caller"))));
        mono.subscribe(System.out::println,System.err::println,null, Context.of("caller","alan"));
        mono.subscribe(System.out::println,System.err::println,null, Context.of("caller","david"));
        mono.subscribe(System.out::println,System.err::println,null);
    }
}
