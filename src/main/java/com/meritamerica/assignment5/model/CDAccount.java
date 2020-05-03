package com.meritamerica.assignment5.model;

import java.util.Date;

public class CDAccount extends BankAccount{
	
	private int term;

	public CDAccount() {
		super();
	}
	
	public CDAccount(double balance, double interestRate, long accountNumber, Date accountOpenedOn) {
		super(balance, interestRate, accountNumber, accountOpenedOn);
	}

	public CDAccount(double openingBalance, double interestRate, int term) {
		super(openingBalance, interestRate);
		this.term = term;
	}
	
	@Override
	public boolean withdraw(double amount) {
		return false;
	}
	
	public boolean deposit(double amount) {
		return false;
	}
	
	public int getTerm() {
		return this.term;
	}
	
	
	
	
}
