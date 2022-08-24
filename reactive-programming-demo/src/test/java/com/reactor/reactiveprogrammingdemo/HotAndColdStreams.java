package com.reactor.reactiveprogrammingdemo;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotAndColdStreams {
	
	@Test
	public void coldStreamTest() {
		
		var numbers = Flux.range(1,10);
		
		
		numbers.subscribe(integer-> System.out.println("Subscribe 1="+integer));
		numbers.subscribe(integer-> System.out.println("Subscribe 2="+integer));
		
	}
	
	@SneakyThrows
	@Test
	public void hotStreamTest() throws InterruptedException {
		
		var numbers = Flux.range(1,10)
				.delayElements(Duration.ofMillis(1000));
		
		ConnectableFlux<Integer> publisher =  numbers.publish();
		
		publisher.connect();
		
		publisher.subscribe(integer-> System.out.println("Subscribe 1="+integer));
		Thread.sleep(4000);
		
		publisher.subscribe(integer-> System.out.println("Subscribe 2="+integer));
		Thread.sleep(10000);
		
		
		
		
	}

}
