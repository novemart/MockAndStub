package com.qatraining;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

//this annotation needs to be present because I initialise my mocks with @Mock
//there are other syntax options available - see slides
@RunWith(MockitoJUnitRunner.class)
public class CaclulatorMockTest {

	//mock of an interface
	@Mock
    ICalculator calc;
	
	@Before
	public void setUp() throws Exception {
		//when I call add method with ANY 2 params of type double
		when(calc.add(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(750.00);

		//telling my mock that if someone calls the method add with these two params
		//it should return 750.00 - random value
		when(calc.add(250.00, 500.00)).thenReturn(850.00);


		when(calc.divide(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(1.5);
		
		//just an example of how to make your mock throw an exception
		when(calc.divide(1000.0, 0.0)).thenThrow(DivisionByZeroException.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMock() {
		//mocking
		// when I create an account I call calc.add in the constructor
		//I don't create a real instance, just a mock
		Account acc = new Account(200, calc);
		//I can verify that the mock has been called with these parameters
		verify(calc).add(200.0, 500.0);
		
		acc = new Account(100, calc);
		verify(calc).add(100.0, 500.0);
	}
	
	@Test
	public void testStub(){
		//stubbing
		//set up done in the @Before method
		//when I call add on the mock I return a fixed value
		//that value is then set in the balance of the account
		Account acc= new Account(100, calc);
		//I'm testing state, after receiving the value from calc mock I set it in my obj
		//I am not checking if the mock was called but if the result of the call was used correctly
		assertEquals(acc.getBalance(), 750.00, 0.001);
	}
	
	//testing that runtime exception was thrown
	@Test(expected=IllegalArgumentException.class)
	public void testException(){
		Account acc = new Account(-100, calc);
	}
	
	//testing checked exception was thrown
	@Test(expected= DivisionByZeroException.class)
	public void testDivision()throws DivisionByZeroException {
		Account acc = new Account(100, calc);
		acc.calculateInterest();
	}

	@Test
	public void testingDivide() throws DivisionByZeroException{
		Account acc = new Account(250.00, calc);
		acc.calculateInterest();
		assertEquals(acc.getInterest(), 1.5, 0.001);
	}
	
	
}
