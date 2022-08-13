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

	void fruitsFluxFlatMapAsync() {

		var fruitsFluxMap1 = fMonoServices.fruitsFluxFlatMapAsync();

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

}
