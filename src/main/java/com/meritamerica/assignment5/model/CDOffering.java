package com.meritamerica.assignment5.model;

public class CDOffering {
	
	private int term;
	private double interestRate;
	
	
	public CDOffering(int term, double interestRate) {
		super();
		this.term = term;
		this.interestRate = interestRate;
	}

	public CDOffering() {}

	public int getTerm() {
		return term;
	}


	public void setTerm(int term) {
		this.term = term;
	}


	public double getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	
	
	

}
