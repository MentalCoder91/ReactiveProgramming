package com.reactor.reactiveprogrammingdemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.reactor.services.FluxAndMonoServices;

import reactor.test.StepVerifier;

class FluxAndmonoServicesTest {
	
	FluxAndMonoServices fMonoServices = new FluxAndMonoServices();

	

	

	@Test
	void fruitsFlux() {
			var fruitsFlux = fMonoServices.fruitsFlux();
			
		 StepVerifier.create(fruitsFlux)
		 	.expectNext("Mango", "Orange", "Banana")
		 		.verifyComplete();
		
			
	}
	
	@Test
	void fruitsMono() {
			var fruitsMono = fMonoServices.fruitsMono();
			
		 StepVerifier.create(fruitsMono)
		 	.expectNext("Guava")
		 		.verifyComplete();
		
			
	}
}
