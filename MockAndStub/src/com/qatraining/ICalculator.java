package com.qatraining;

public interface ICalculator {
	
	double add (double num1, double num2);
	
	double divide(double num1, double num2) throws DivisionByZeroException;

}
