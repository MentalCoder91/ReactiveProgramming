package com.reactor.reactiveprogrammingdemo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.reactor.services.FluxAndMonoServices;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxAndmonoServicesTest {

	FluxAndMonoServices fMonoServices = new FluxAndMonoServices();

	@Test
	void fruitsFlux() {
		var fruitsFlux = fMonoServices.fruitsFlux();

		StepVerifier.create(fruitsFlux).expectNext("Mango", "Orange", "Banana").verifyComplete();

	}

	@Test
	void fruitsFluxMap() {
		var fruitsFluxMap = fMonoServices.fruitsFluxMap();

		StepVerifier.create(fruitsFluxMap).expectNext("MANGO", "ORANGE", "BANANA").verifyComplete();

	}

	@Test
	void fruitsFluxFilter() {

		var fruitsFluxMap1 = fMonoServices.fruitsFluxFilter(5);

		StepVerifier.create(fruitsFluxMap1).expectNext("Orange", "Banana").verifyComplete();

	}

	@Test
	void fruitsFluxFilterMap() {

		var fruitsFluxMap1 = fMonoServices.fruitsFluxFilterMap(5);

		StepVerifier.create(fruitsFluxMap1).expectNext("ORANGE", "BANANA").verifyComplete();

	}

	@Test
	void fruitsFluxFlatMap() {

		var fruitsFluxMap1 = fMonoServices.fruitsFluxFlatMap();

		StepVerifier.create(fruitsFluxMap1).expectNextCount(17).verifyComplete();

	}

	@Test
	void fruitsFluxFlatMapAsync() {

		var fruitsFluxMap1 = fMonoServices.fruitsFluxFlatMapAsync();

		StepVerifier.create(fruitsFluxMap1).expectNextCount(17).verifyComplete();

	}
	
	@Test
	void fruitsFluxConcatMap() {
		var fruitsFluxMap1 = fMonoServices.fruitsFluxConcatMap();

		StepVerifier.create(fruitsFluxMap1).expectNextCount(17).verifyComplete();
	}

	@Test
	void fruitsMono() {
		var fruitsMono = fMonoServices.fruitsMono();

		StepVerifier.create(fruitsMono).expectNext("Guava").verifyComplete();

	}

	@Test
	void fruitsMonoFlatMap() {
		var fruitsMono = fMonoServices.fruitsMonoFlatMap();

		StepVerifier.create(fruitsMono).expectNextCount(1).verifyComplete();

	}
	
	@Test
	void fruitsFluxTransform() {
		var fruitsFluxMap1 = fMonoServices.fruitsFluxTransform(5);

		StepVerifier.create(fruitsFluxMap1).expectNext("Orange", "Banana").verifyComplete();

	}
	
	
	@Test
	void fruitsFluxTransformDefaultIfEmpty(){
		var fruitsFluxMap1 = fMonoServices.fruitsFluxTransformDefaultIfEmpty(10);

		StepVerifier.create(fruitsFluxMap1).expectNext("Default").verifyComplete();

		
	}
	
	
	
	@Test
	void fruitsFluxTransformSwitchIfEmpty() {
		var fruitsFluxMap1 = fMonoServices.fruitsFluxTransformSwitchIfEmpty(8);

		StepVerifier.create(fruitsFluxMap1).expectNext("Pineapple","DragonFruit")
				.verifyComplete();

		
		
	}
	
	@Test
	void fruitsFluxConcat() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxConcat();

		StepVerifier.create(fruitsFluxMap1).expectNext("Mango","Orange","Apple","Tomato","Carrot","Lemon")
				.verifyComplete();
		
	}
	
	
	
	@Test
	void fruitsFluxConcatWith() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxConcatWith();

		StepVerifier.create(fruitsFluxMap1).expectNext("Mango","Orange","Apple","Tomato","Carrot","Lemon")
				.verifyComplete();
		
	}
	
	

	@Test
	void fruitsFluxMerge() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxMerge();

		StepVerifier.create(fruitsFluxMap1).expectNext("Tomato","Mango","Carrot","Orange","Lemon","Apple")
								.verifyComplete();
		
	}
	
	
	
	@Test
	void fruitsFluxMergeWith() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxMergeWith();

		StepVerifier.create(fruitsFluxMap1).expectNext("Tomato","Mango","Carrot","Orange","Lemon","Apple")
								.verifyComplete();
		
	}
	
	
	@Test
	void fruitsFluxMergeWithSequential(){
		

		var fruitsFluxMap1 = fMonoServices.fruitsFluxMergeWithSequential();

		StepVerifier.create(fruitsFluxMap1).expectNext("Mango","Orange","Apple","Tomato","Carrot","Lemon")
				.verifyComplete();
		
		
		
	}
	
	@Test
	void fruitsFluxZip() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxZip();

		StepVerifier.create(fruitsFluxMap1).expectNext("Mango=>Tomato","Orange=>Carrot")
				.verifyComplete();
		
		
	}
	
	@Test
	void fruitsFluxZipWith() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxZipWith();

		StepVerifier.create(fruitsFluxMap1).expectNext("MangoTomato","OrangeCarrot")
				.verifyComplete();
		
		
	}
	
	
	@Test
	void fruitsFluxZipTuple() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxZipTuple();

		StepVerifier.create(fruitsFluxMap1).expectNext("MangoTomatoCucumber","OrangeCarrotBrinjal")
				.verifyComplete();
		
		
	}
	
	
	@Test
	void fruitsFluxFilterDoOn() {
		
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxFilterDoOn(5);

		StepVerifier.create(fruitsFluxMap1).expectNext("Orange", "Banana").verifyComplete();
		
		
	}
	
	
	@Test
	void fruitsFluxOnErrorReturn() {
		
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxOnErrorReturn();

		StepVerifier.create(fruitsFluxMap1).expectNext("Apple", "Mango","Orange").verifyComplete();
		
		
		
	}
	
	@Test
	void fruitsFluxOnErrorContinue() {
		
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxOnErrorContinue();

		StepVerifier.create(fruitsFluxMap1).expectNext("APPLE", "GUAVA").verifyComplete();
		
		
		
	}
	
	
	@Test
	void fruitsFluxOnErrorMap() {
		
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxOnErrorMap();

		StepVerifier.create(fruitsFluxMap1)
		.expectNext("APPLE")
		.expectError(IllegalArgumentException.class)
		.verify();
		
		
		
	}
	
	@Test
	void fruitsFluxdoOnError() {
		
		
		var fruitsFluxMap1 = fMonoServices.fruitsFluxdoOnError();

		StepVerifier.create(fruitsFluxMap1)
		.expectNext("APPLE")
		.expectError(RuntimeException.class)
		.verify();
		
		
		
	}
	
	
	
	
	
	
	
	@Test
	void fruitsMonoConcatWith() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsMonoConcatWith();

		StepVerifier.create(fruitsFluxMap1).expectNext("Mango","Tomato")
				.verifyComplete();
		
	}
	
	
	@Test
	void fruitsMonoFlatMapMany() {
		
		
		var fruitsMono = fMonoServices.fruitsMonoFlatMapMany();

		StepVerifier.create(fruitsMono).expectNextCount(5).verifyComplete();

		
	}
	
	
	@Test
	void fruitsMonoZipWith() {
		
		var fruitsFluxMap1 = fMonoServices.fruitsMonoZipWith();

		StepVerifier.create(fruitsFluxMap1).expectNext("MangoTomato")
				.verifyComplete();
		
		
	}

}
