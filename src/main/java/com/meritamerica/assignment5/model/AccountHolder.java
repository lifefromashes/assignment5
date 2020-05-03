	package com.meritamerica.assignment5.model;

	import java.util.ArrayList;
	import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.meritamerica.assignment5.exceptions.ExceedsCombinedBalanceLimitException;

	public class AccountHolder implements Comparable<AccountHolder> {

		static int nextId = 1;

		long id;
		private List<BankAccount> checkingAccounts;
		private List<BankAccount> savingsAccounts;
		private List<BankAccount> cdAccounts;

		@NotBlank(message = "First, Middle, Last and SSN required.")
		@NotEmpty
		private String firstName = "";
		private String middleName = "";
		private String lastName = "";
		private String ssn = "";

		public AccountHolder() {
			this.id = nextId++;
			this.firstName = "";
			this.middleName = "";
			this.lastName = "";
			this.ssn = "";

			this.checkingAccounts = new ArrayList<>();
			this.savingsAccounts = new ArrayList<>();
			this.cdAccounts = new ArrayList<>();
			// MeritBank.setNextAccountHolderNumber(MeritBank.getNextAccountHolderNumber() +
			// 1);
		}

		public boolean addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {
			if (checkingAccount == null) {
				return false;
			}
			if (getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance() >= 250000) {
				throw new ExceedsCombinedBalanceLimitException("Unable to create Account");
			}
			checkingAccounts.add(checkingAccount);
			return true;
		}

		public boolean addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {
			if (savingsAccount == null) {
				return false;
			}
			if (getSavingsBalance() + getCheckingBalance() + savingsAccount.getBalance() >= 250000) {
				throw new ExceedsCombinedBalanceLimitException("Unable to create Account");
			}
			savingsAccounts.add(savingsAccount);
			return true;
		}

		public boolean addCDAccount(CDAccount cdAccount) {
			if (cdAccount == null)
				return false;

			cdAccounts.add(cdAccount);
			return true;
		}

		public double getCheckingBalance() {
			double sum = 0;
			for (BankAccount bankAcc : checkingAccounts) {
				sum += bankAcc.getBalance();
			}
			return sum;
		}

		public double getSavingsBalance() {
			double sum = 0;
			for (BankAccount savAcc : savingsAccounts) {
				sum += savAcc.getBalance();
			}
			return sum;
		}

		public double getCDBalance() {
			double sum = 0;
			for (BankAccount cdAcc : cdAccounts) {
				sum += cdAcc.getBalance();
			}
			return sum;
		}

		public double getCombinedBalance() {
			double sum = 0;
			sum += getCheckingBalance();
			sum += getSavingsBalance();
			sum += getCDBalance();
			return sum;
		}

		public long getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getSsn() {
			return ssn;
		}

		public void setSsn(String ssn) {
			this.ssn = ssn;
		}

		public List<BankAccount> getCheckingAccounts() {
			return checkingAccounts;
		}

		public List<BankAccount> getSavingsAccounts() {
			return savingsAccounts;
		}

		public List<BankAccount> getCdAccounts() {
			return cdAccounts;
		}

		public int getNumOfCheckingAccounts() {
			return checkingAccounts.size();
		}

		public int getNumofSavingsAccounts() {
			return savingsAccounts.size();
		}

		public int getNumOfCDAccounts() {
			return cdAccounts.size();
		}

		@Override
		public int compareTo(AccountHolder other) {
			int mySum = (int) getCombinedBalance();
			int otherSum = (int) other.getCombinedBalance();
			return mySum - otherSum;
		}

	}


