package com.calulator.basic.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
    private int lastResult = 0;

    @GetMapping("/result")
    public Mono<Integer> getLastResult() {
        return Mono.just(lastResult);
    }

    @PostMapping("/addition")
    public Mono<Integer> addition(@RequestBody List<Integer> numbers) {
        Flux.fromIterable(numbers).reduce(Integer::sum).subscribe(elem -> {lastResult = elem;});
        return Mono.just(lastResult);
    }
}