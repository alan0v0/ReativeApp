package com.alan.reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CalculatorService {
    public Mono<Integer> add(int numA, int numB) {
        return Mono.just(numA + numB).delayElement(Duration.ofMillis(5000));
    }
}
