package com.spring.angular;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CarTest {

	@Mock
	Engine engine;
	
	@InjectMocks
	Car car;
	
	@Before
	public void setup(){
		
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void testCarSpeed(){
		
		when(engine.getRPM()).thenReturn(6000);
		
		car.accelerate();
		
		assertEquals("Slow Down!", car.getWarningMessage());
	}
}
