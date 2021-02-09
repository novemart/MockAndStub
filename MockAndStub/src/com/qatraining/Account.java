package com.qatraining;

public class Account {
	
	private ICalculator calc;
	private double balance; 
	private double interest;
	
	//needs a 'ICalculator' object to calculate balance
	public Account(double initBalance, ICalculator calc){
		if ( initBalance < 0 ) {
			//runtime exception
			throw new IllegalArgumentException(
					"Initial balance cannot be negative");
		}
		this.calc = calc; 
		this.balance = calc.add(initBalance, 500.0);
	}
	
	public double getBalance() { 
		return this.balance; 
	}
	public double getInterest() { return this.interest; }
	
	public void calculateInterest() throws DivisionByZeroException{
		//random interest calculation to demonstrate custom exception
			this.interest = calc.divide(1000, this.balance - 750.0);
	}
}
