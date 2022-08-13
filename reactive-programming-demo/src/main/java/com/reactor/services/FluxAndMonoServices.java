package com.reactor.services;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoServices {

	public Flux<String> fruitsFlux() {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana"));

	}

	// Operators Map

	public Flux<String> fruitsFluxMap() {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).map(String::toUpperCase);

	}

	// Filter Operator

	public Flux<String> fruitsFluxFilter(int number) {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).filter(x -> x.length() > number).log();

	}

	// Map and Filter

	public Flux<String> fruitsFluxFilterMap(int number) {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))

				.filter(x -> x.length() > number).map(String::toUpperCase);

	}

	// FlatMap

	public Flux<String> fruitsFluxFlatMap() {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).flatMap(x -> Flux.just(x.split(""))).log();

	}

	// FlatMap with Delay Async

	public Flux<String> fruitsFluxFlatMapAsync() {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).flatMap(x -> Flux.just(x.split("")))
				.delayElements(Duration.ofMillis(

						new Random().nextInt(1000)))
				.log();

	}

	public Mono<String> fruitsMono() {

		return Mono.just("Guava").log();
	}

	public Mono<List<String>> fruitsMonoFlatMap() {

		return Mono.just("Guava").flatMap(x -> Mono.just(List.of(x.split("")))).log();
	}

	public static void main(String[] args) {
		FluxAndMonoServices fx = new FluxAndMonoServices();

		fx.fruitsFlux().subscribe(s -> System.out.println(s));

		fx.fruitsMono().subscribe(s -> System.out.println("Mono ->" + s));
	}
}
