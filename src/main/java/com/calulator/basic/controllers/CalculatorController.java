package com.calulator.basic.controllers;

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

    @GetMapping("/prettyResult")
    public String getPrettyLastResult() {
        return STR."The last result was \{lastResult}";
    }

    @PutMapping("/reset")
    public void resetLastResult() {
        lastResult = 0;
    }

    @PostMapping("/addition")
    public Mono<Integer> addition(@RequestBody List<Integer> numbers) {
        Flux.fromIterable(numbers).reduce(Integer::sum).subscribe(elem -> {lastResult = elem;});
        return Mono.just(lastResult);
    }

    @PostMapping("/subtraction")
    public Mono<Integer> subtraction(@RequestBody List<Integer> numbers) {
        numbers.stream().reduce((n1, n2) -> {
            return n1 - n2;
        }).ifPresent(integer -> lastResult = integer);
        return Mono.just(lastResult);
    }
}