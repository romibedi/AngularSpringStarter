package com.spring.angular;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CalculatorTest {

	@Mock
	Calculator calculator;
	
	@Before
	public void setup(){
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAbs(){
		
		when(calculator.abs(-4)).thenReturn(4);
		
		assertEquals(4, calculator.abs(-4));
		
	}
}
