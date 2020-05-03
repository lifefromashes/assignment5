package com.meritamerica.assignment5.model;

import java.util.Date;

public class SavingsAccount extends BankAccount {
	

	private static final double DEFAULT_INTEREST_RATE = .01;
	
	public SavingsAccount() {}

	public SavingsAccount(double balance, double interestRate, long accountNumber, Date accountOpenedOn) {
		super(balance, interestRate, accountNumber, accountOpenedOn);
	}

	public SavingsAccount(double openingBalance) {
		super(openingBalance, DEFAULT_INTEREST_RATE);
	}

	// to load from account object
	public SavingsAccount(BankAccount bankAccount) {
		super(bankAccount.getBalance(), bankAccount.getInterestRate(), bankAccount.getAccountNumber(),
				bankAccount.getAccountOpenedOn());
	}

}

