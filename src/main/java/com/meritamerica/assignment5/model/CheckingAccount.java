package com.meritamerica.assignment5.model;

import java.util.Date;

public class CheckingAccount extends BankAccount {
	


	static final double DEFAULT_INTEREST_RATE = .0001;
	
	public CheckingAccount() {}

	public CheckingAccount(double openingBalance) {
		super(openingBalance, DEFAULT_INTEREST_RATE);
	}

	public CheckingAccount(BankAccount bankAccount) {
		super(bankAccount.getBalance(), bankAccount.getInterestRate(), bankAccount.getAccountNumber(),
				bankAccount.getAccountOpenedOn());

	}

	public CheckingAccount(double balance, double interestRate, long accountNumber, Date accountOpenedOn) {
		super(balance, interestRate, accountNumber, accountOpenedOn);
	}

}

//	static final double DEFAULT_INTEREST_RATE = .0001;
//	
//	// new account
//	public CheckingAccount (double openingBalance) {
//		super(openingBalance, DEFAULT_INTEREST_RATE);
//	}
//	
//	// loaded from account object 
//	public CheckingAccount (BankAccount bankAccount) {
//		super(bankAccount.getBalance(), bankAccount.getInterestRate(), 
//				bankAccount.getAccountNumber(), bankAccount.getAccountOpenedOn());
//	}
//	
//	// loaded from values
//	public CheckingAccount(double balance, double interestRate, Date openedOn, long accountNumber) {
//		super(balance, interestRate, accountNumber, openedOn);
//	}
