package com.meritamerica.assignment5.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeritBank {

	private static List<AccountHolder> accountHolders = new ArrayList<>();
	private static List<CDOffering> cdOfferings = new ArrayList<>();
	private static long nextAccountNumber = 1;
	private static long nextAccountHolderNumber = 1;

	public static void addAccountHolder(AccountHolder accountHolder) {
		accountHolders.add(accountHolder);
	}

	public static List<AccountHolder> getAccountHolders() {
		return accountHolders;
	}

	public static List<CDOffering> getCDOfferings() {
		return cdOfferings;
	}

	public static BankAccount getBankAccountByAccountNumber(long id) {
		for (AccountHolder acctHold : accountHolders) {
			for (BankAccount bankacc : acctHold.getCheckingAccounts()) {
				if (bankacc.getAccountNumber() == id) {
					return bankacc;
				}
			}
			for (BankAccount bankacc : acctHold.getSavingsAccounts()) {
				if (bankacc.getAccountNumber() == id) {
					return bankacc;
				}
			}
			for (BankAccount bankAcc : acctHold.getCdAccounts()) {
				if (bankAcc.getAccountNumber() == id) {
					return bankAcc;
				}
			}
		}
		return null;

	}
	static CDOffering getSecondBestCDOffering(double depositAmount) {
		if(cdOfferings.size() <= 1) { return null; }
		
		CDOffering best = getBestCDOffering(depositAmount);
		CDOffering secondBest = null;
		double secondBestRate = 0;
		
		for(CDOffering cdo : cdOfferings) {
			if(cdo == best) { continue; }
			if(cdo.getInterestRate() > secondBestRate) {
				secondBest = cdo;
				secondBestRate = cdo.getInterestRate();
			}
			
		}
		
		return secondBest;
	}
	
	public static void setCDOfferings (List<CDOffering> cdOffers) {
		for(CDOffering offering : cdOffers) {
			cdOfferings.add(offering);
		}
	}
	
	public static void addCDOffering(CDOffering cdOffering) {
		cdOfferings.add(cdOffering);
	}
	
	public static CDOffering getBestCDOffering(double depositAmount) {
		if(cdOfferings.size() == 0) return null;
		
		double bestRate = 0;
		CDOffering best = null;
		
		for(CDOffering cdOffering : cdOfferings) {
			if(cdOffering.getInterestRate() > bestRate) {
				best = cdOffering;
				bestRate = cdOffering.getInterestRate();
			}
		}
		return best;
	}

	public static long getNextAccountNumber() {
		return nextAccountNumber;

	}

	public static void setNextAccountNumber(long accountNumber) {
		nextAccountNumber = accountNumber;
	}

	public static AccountHolder getAccountHolderById(long id) {
		for (AccountHolder accthold : accountHolders) {
			if (accthold.getId() == id) {
				return accthold;
			}
		}
		return null;

	}
	
	public static double totalBalances() {
		double sum = 0;
		for(AccountHolder acctholder : accountHolders) {
			for(BankAccount bankacc : acctholder.getCheckingAccounts()) {
				sum += bankacc.getBalance();
			}
			
			for(BankAccount bankacc : acctholder.getSavingsAccounts()) {
				sum += bankacc.getBalance();
			}
			
			for(BankAccount bankacc: acctholder.getCdAccounts()) {
				sum += bankacc.getBalance();
			}
		}
		return sum;
	}
	
	public static void sortAccountHolders() {
		Collections.sort(accountHolders);       
	}

}
