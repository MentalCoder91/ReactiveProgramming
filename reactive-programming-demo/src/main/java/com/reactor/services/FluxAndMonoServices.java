package com.reactor.services;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoServices {

	public Flux<String> fruitsFlux() {

		return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).log();

	}
	
	
	public Mono<String> fruitsMono(){
		
		
		return Mono.just("Guava").log();
	}

	
	
	
	public static void main(String[] args) {
		FluxAndMonoServices fx = new FluxAndMonoServices();
		
		fx.fruitsFlux().subscribe(s-> System.out.println(s));
		
		
		fx.fruitsMono().subscribe(s-> System.out.println("Mono ->"+s));
	}
}
