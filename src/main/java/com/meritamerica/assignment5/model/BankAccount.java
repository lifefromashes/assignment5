package com.meritamerica.assignment5.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.meritamerica.assignment5.exceptions.ExceedsAvailableBalanceException;
import com.meritamerica.assignment5.exceptions.NegativeAmountException;
import com.meritamerica.assignment5.exceptions.NoSuchResourceFoundException;

public class BankAccount {

	@NotBlank(message = "Balance is required")
	private double balance;
	
	private double interestRate;
	private long accountNumber;
	private Date accountOpenedOn;
	
	public BankAccount() {}

	public BankAccount(double balance, double interestRate, long accountNumber, Date accountOpenedOn) {

		this.balance = balance;
		this.interestRate = interestRate;
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.accountOpenedOn = accountOpenedOn;
		MeritBank.setNextAccountNumber(MeritBank.getNextAccountNumber() + 1); 


	}

	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public boolean withdraw(double amount) throws ExceedsAvailableBalanceException, NoSuchResourceFoundException {
		if (amount > this.balance) {
			throw new ExceedsAvailableBalanceException("Exceeds Available Balance");
		}

		if (amount < 0) {
			throw new NoSuchResourceFoundException("Cannot withdraw neg amount");
		}

		this.balance = this.balance - amount;
		return true;

	}

	public boolean deposit(double amount) throws NegativeAmountException{
		if (amount < 0)
			throw new NegativeAmountException("Unable to deposit");

		this.balance = this.balance + amount;
		return true;
	}

	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getAccountOpenedOn() {
		return accountOpenedOn;
	}

	public void setAccountOpenedOn(Date accountOpenedOn) {
		this.accountOpenedOn = accountOpenedOn;
	}

}

