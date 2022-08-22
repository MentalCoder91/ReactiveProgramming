package com.reactor.services;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import javax.management.RuntimeErrorException;

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

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).filter(x -> x.length() > number);

	}

	// Map and Filter

	public Flux<String> fruitsFluxFilterMap(int number) {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))

				.filter(x -> x.length() > number).map(String::toUpperCase);

	}

	// FlatMap

	public Flux<String> fruitsFluxFlatMap() {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).flatMap(x -> Flux.just(x.split("")));

	}

	// FlatMap with Delay Async

	public Flux<String> fruitsFluxFlatMapAsync() {

		return Flux.fromIterable(List.of("Anish", "Orange", "Banana")).flatMap(x -> Flux.just(x.split("")))
				.delayElements(Duration.ofMillis(

						new Random().nextInt(1000)));

	}

	// ConcatMap maintains insertion order whereas flatMap does not.

	public Flux<String> fruitsFluxConcatMap() {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).concatMap(x -> Flux.just(x.split("")))
				.delayElements(Duration.ofMillis(2000));

	}

	public Flux<String> fruitsFluxTransform(int number) {

		Function<Flux<String>, Flux<String>> filterData = data -> data.filter(x -> x.length() > number);

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).transform(filterData);

	}

	public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number) {

		Function<Flux<String>, Flux<String>> filterData = data -> data.filter(x -> x.length() > number);

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).transform(filterData).defaultIfEmpty("Default");

	}

	public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {

		Function<Flux<String>, Flux<String>> filterData = data -> data.filter(x -> x.length() > number);

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).transform(filterData)
				.switchIfEmpty(Flux.just("Pineapple", "DragonFruit")).transform(filterData);

	}

	public Flux<String> fruitsFluxConcat() {

		var fruits = Flux.just("Mango", "Orange", "Apple");
		var veggies = Flux.just("Tomato", "Carrot", "Lemon");

		return Flux.concat(fruits, veggies); // sequential subscription.

	}

	public Flux<String> fruitsFluxConcatWith() {

		var fruits = Flux.just("Mango", "Orange", "Apple");
		var veggies = Flux.just("Tomato", "Carrot", "Lemon");

		return fruits.concatWith(veggies); // sequential subscription.

	}

	public Flux<String> fruitsFluxMerge() {

		var fruits = Flux.just("Mango", "Orange", "Apple").delayElements(Duration.ofMillis(70));
		var veggies = Flux.just("Tomato", "Carrot", "Lemon").delayElements(Duration.ofMillis(50));

		return Flux.merge(fruits, veggies); // Interleaved subscription.

	}

	public Flux<String> fruitsFluxMergeWith() {

		var fruits = Flux.just("Mango", "Orange", "Apple").delayElements(Duration.ofMillis(70));
		var veggies = Flux.just("Tomato", "Carrot", "Lemon").delayElements(Duration.ofMillis(50));

		return fruits.mergeWith(veggies); // Interleaved subscription.

	}

	public Flux<String> fruitsFluxMergeWithSequential() {

		var fruits = Flux.just("Mango", "Orange", "Apple").delayElements(Duration.ofMillis(70));
		var veggies = Flux.just("Tomato", "Carrot", "Lemon").delayElements(Duration.ofMillis(50));

		return Flux.mergeSequential(fruits, veggies); // sequential subscription.

	}

	public Flux<String> fruitsFluxZip() {

		var fruits = Flux.just("Mango", "Orange");
		var veggies = Flux.just("Tomato", "Carrot");
		// Mango=>Tomato

		return Flux.zip(fruits, veggies, (x, y) -> x + "=>" + y);
	}

	public Flux<String> fruitsFluxZipWith() {

		var fruits = Flux.just("Mango", "Orange");
		var veggies = Flux.just("Tomato", "Carrot");
		// Mango=>Tomato

		return fruits.zipWith(veggies, (x, y) -> x + y).log();
	}

	public Flux<String> fruitsFluxZipTuple() {

		var fruits = Flux.just("Mango", "Orange");
		var veggies = Flux.just("Tomato", "Carrot");
		var veggiesMore = Flux.just("Cucumber", "Brinjal");
		// Mango=>Tomato

		return Flux.zip(fruits, veggies, veggiesMore) // BiFunction(only 2 publishers) wont work hence use map operator
				.map(Object -> Object.getT1() + Object.getT2() + Object.getT3()).log();
	}

	public Flux<String> fruitsFluxFilterDoOn(int number) {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).filter(x -> x.length() > number).doOnNext(x -> {
			System.out.println("item=>" + x);
		}).doOnSubscribe(subs -> {
			System.out.println("Subscritpion=>" + subs.toString());
		}).doOnComplete(() -> {
			System.out.println("Completed");
		});

	}

	public Flux<String> fruitsFluxOnErrorReturn() {

		return Flux.just("Apple", "Mango")
				.concatWith(Flux.error(new RuntimeException("Error Occured")))
				.onErrorReturn("Orange").log();

	}
	
	
	
	public Flux<String> fruitsFluxOnErrorContinue() {

		return Flux.just("Apple", "Mango","Guava")
				.map(x->{
					
					if(x.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Exception Occured");
					return x.toUpperCase();
				})
				.onErrorContinue((e,f)->{
					
					System.out.println("error->"+e);
					System.out.println("Object->"+f);
				})
				.log();

	}
	
	public Flux<String> fruitsFluxOnErrorMap() {

		return Flux.just("Apple", "Mango","Guava")
				.map(x->{
					
					if(x.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Exception Occured");
					return x.toUpperCase();
				})
				.onErrorMap(t->{
					
					
					System.out.println("throwable =>"+t);
					return new IllegalArgumentException("Not Allowed");
				})
				.log();

	}
	
	public Flux<String> fruitsFluxdoOnError() {

		return Flux.just("Apple", "Mango","Guava")
				.map(x->{
					
					if(x.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Exception Occured");
					return x.toUpperCase();
				})
				.doOnError(t->{
					System.out.println("throwable =>"+t);
				})
				.log();

	}

	// ********************************* Mono *****************************

	public Mono<String> fruitsMono() {

		return Mono.just("Guava");
	}

	public Mono<List<String>> fruitsMonoFlatMap() {

		return Mono.just("Guava").flatMap(x -> Mono.just(List.of(x.split(""))));
	}

	// Convert from Mono at input to Flux
	public Flux<String> fruitsMonoFlatMapMany() {

		return Mono.just("Guava").flatMapMany(x -> Flux.just(x.split("")));
	}

	public Flux<String> fruitsMonoConcatWith() {

		var fruits = Mono.just("Mango");
		var veggies = Flux.just("Tomato");

		return fruits.concatWith(veggies); // sequential subscription.

	}

	public Mono<String> fruitsMonoZipWith() {

		var fruits = Mono.just("Mango");
		var veggies = Mono.just("Tomato");
		// Mango=>Tomato

		return fruits.zipWith(veggies, (x, y) -> x + y).log();
	}

	// *****************************MAIN****************************************************

	public static void main(String[] args) {
		FluxAndMonoServices fx = new FluxAndMonoServices();

		fx.fruitsFlux().subscribe(s -> System.out.println(s));

		fx.fruitsMono().subscribe(s -> System.out.println("Mono ->" + s));
	}
}
