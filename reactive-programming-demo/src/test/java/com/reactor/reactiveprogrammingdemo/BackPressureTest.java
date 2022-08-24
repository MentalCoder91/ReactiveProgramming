package com.reactor.reactiveprogrammingdemo;

import org.junit.Test;
import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {
	
	
	@Test
	public void testBackPressure() {
		
		
		var numbers = Flux.range(1, 100	).log();
		
		//numbers.subscribe(integer-> System.out.println(integer));
		
		numbers.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				System.out.println("The value->"+value);
				
				if(value == 3)
					cancel();
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("Completed");
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				// TODO Auto-generated method stub
				super.hookOnError(throwable);
			}

			@Override
			protected void hookOnCancel() {
				// TODO Auto-generated method stub
				super.hookOnCancel();
			}

			

			
			
		});
	}
	
	
	@Test
	public void testBackPressureDrop() {
		
		
		var numbers = Flux.range(1, 100	).log();
		
		//numbers.subscribe(integer-> System.out.println(integer));
		
		numbers
		.onBackpressureDrop(integer-> System.out.println("Dropped Values->"+integer))
		.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				System.out.println("The value->"+value);
				
				if(value == 3)
					hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("Completed");
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				// TODO Auto-generated method stub
				super.hookOnError(throwable);
			}

			@Override
			protected void hookOnCancel() {
				// TODO Auto-generated method stub
				super.hookOnCancel();
			}

			

			
			
		});
	}
	
	
	
	@Test
	public void testBackPressureBuffer() {
		
		
		var numbers = Flux.range(1, 100	).log();
		
		//numbers.subscribe(integer-> System.out.println(integer));
		
		numbers
		.onBackpressureBuffer(10,integer-> System.out.println("Buffered Value->"+integer))
		.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				System.out.println("The value->"+value);
				
				if(value == 3)
					hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("Completed");
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				// TODO Auto-generated method stub
				super.hookOnError(throwable);
			}

			@Override
			protected void hookOnCancel() {
				// TODO Auto-generated method stub
				super.hookOnCancel();
			}

			

			
			
		});
	}
	
	@Test
	public void testBackPressureError() {
		
		
		var numbers = Flux.range(1, 100	).log();
		
		//numbers.subscribe(integer-> System.out.println(integer));
		
		numbers
		.onBackpressureError()
		.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				System.out.println("The value->"+value);
				
				if(value == 3)
					hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("Completed");
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				System.out.println("Throwable->"+throwable);
			}

			@Override
			protected void hookOnCancel() {
				// TODO Auto-generated method stub
				super.hookOnCancel();
			}

			

			
			
		});
	}
	
}
