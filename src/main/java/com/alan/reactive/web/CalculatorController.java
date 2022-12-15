package com.alan.reactive.web;


import com.alan.reactive.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/add")
    public Mono<Integer> add(@RequestParam("a") Integer numA, @RequestParam("b") Integer numB) {
        return calculatorService.add(numA, numB);
    }
}
