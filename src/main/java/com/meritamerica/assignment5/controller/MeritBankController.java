package com.meritamerica.assignment5.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.exceptions.NegativeAmountException;
import com.meritamerica.assignment5.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment5.model.AccountHolder;
import com.meritamerica.assignment5.model.BankAccount;
import com.meritamerica.assignment5.model.CDAccount;
import com.meritamerica.assignment5.model.CDOffering;
import com.meritamerica.assignment5.model.CheckingAccount;
import com.meritamerica.assignment5.model.MeritBank;
import com.meritamerica.assignment5.model.SavingsAccount;

import javassist.NotFoundException;

@RestController
public class MeritBankController {

	// private Logger log = new LoggerFactory.getLogger(this.getClass());

	// server http://localhost:8080
	List<AccountHolder> accountHolders = new ArrayList<AccountHolder>();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String test() {
		return "Program Starting.....";
	}

	@GetMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountHolder> getAccountHolders() {
		return MeritBank.getAccountHolders();
	}

	@PostMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		MeritBank.addAccountHolder(accountHolder);
		// accountHolders.add(accountHolder);
		return accountHolder;
	}

	@GetMapping(value = "/AccountHolders/{id}")
	public AccountHolder getAccountHolderById(@PathVariable(name = "id") int id) throws NoSuchResourceFoundException {
		AccountHolder accthold = MeritBank.getAccountHolderById(id);
		if (accthold == null) {
			throw new NoSuchResourceFoundException("Invalid Id");
		}
//			if(id > accountHolders.size()-1) {
//				throw new NoSuchResourceFoundException("Invalid Id");
//			}
		return accthold;
	}

	// == CHECKING ACCOUNTS ==

	@PostMapping("/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addCheckingAccount(@PathVariable(name = "id") long id,
			@RequestBody @Valid CheckingAccount checkingAccount)
			throws NotFoundException, ExceedsCombinedBalanceLimitException, NegativeAmountException {

		AccountHolder acctholder = MeritBank.getAccountHolderById(id);

		acctholder.addCheckingAccount(checkingAccount);
		return checkingAccount;
	}

	@GetMapping("/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<BankAccount> getCheckingAccounts(@PathVariable(name = "id") long id) throws  NoSuchResourceFoundException {
		AccountHolder acctholder = MeritBank.getAccountHolderById(id);
		if (acctholder == null) {
			throw new NoSuchResourceFoundException("Invalid ID");
		}
		return acctholder.getCheckingAccounts();
	}

	// == SAVINGS ACCOUNTS ==
	
	@GetMapping("/AccountHolders/{id}/SavingsAccounts")
	public List<BankAccount> getSavingsAccounts(@PathVariable(name = "id") long id) throws  NoSuchResourceFoundException {
		if(id > MeritBank.getAccountHolders().size() - 1) {
			throw new NoSuchResourceFoundException("Invalid Id");
		}
		AccountHolder acctholder = MeritBank.getAccountHolderById(id);
		return acctholder.getSavingsAccounts();
	}


//	@GetMapping("/AccountHolders/{id}/SavingsAccounts")
//	@ResponseStatus(HttpStatus.OK)
//	public List<BankAccount> getSavingsAccounts(@PathVariable(name = "id") long id) throws  NoSuchResourceFoundException {
//		AccountHolder acctholder = MeritBank.getAccountHolderById(id);
//		if (acctholder == null) {
//			throw new NoSuchResourceFoundException("Invalid ID");
//		}
//		return acctholder.getSavingsAccounts();
//	}
	
	@PostMapping("/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@PathVariable(name = "id") long id,
			@RequestBody @Valid SavingsAccount savingsAccount)
			throws NotFoundException, ExceedsCombinedBalanceLimitException, NegativeAmountException {

		AccountHolder acctholder = MeritBank.getAccountHolderById(id);
		acctholder.addSavingsAccount(savingsAccount);
		return savingsAccount;
	}

//	@GetMapping("/AccountHolders/{id}/SavingsAccounts")
//	//@ResponseStatus(HttpStatus.OK)
//	public List<BankAccount> getSavingsAccounts(@PathVariable(name = "id") long id) throws NotFoundException, NoSuchResourceFoundException {
//		AccountHolder acctholder = MeritBank.getAccountHolderById(id);
//		if (acctholder == null) {
//		throw new NoSuchResourceFoundException("Invalid ID");
//		}
//		return acctholder.getSavingsAccounts();
//	}

	// ==CDACCOUNTS==

	@GetMapping("/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<BankAccount> getCDAccounts(@PathVariable(name = "id") long id) throws NoSuchResourceFoundException {
		AccountHolder acctholder = MeritBank.getAccountHolderById(id);
		if (acctholder == null)
			throw new NoSuchResourceFoundException("Invalid Id");

		return acctholder.getCdAccounts();
	}

	@PostMapping("AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@PathVariable(name = "id") long id, @RequestBody @Valid CDAccount cdAccount)
			throws NotFoundException, ExceedsCombinedBalanceLimitException, NegativeAmountException {
		AccountHolder acctholder = MeritBank.getAccountHolderById(id);
		acctholder.addCDAccount(cdAccount);
		return cdAccount;
	}

	// == CD OFFERINGS ==

	@PostMapping("/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering createCDOffering(@RequestBody CDOffering cdOffering) {
		MeritBank.addCDOffering(cdOffering);
		return cdOffering;
	}

	@GetMapping("/CDOfferings")
	@ResponseStatus(HttpStatus.OK)
	public List<CDOffering> getCDOfferings() throws NoSuchResourceFoundException {
		List<CDOffering> cdOffering = MeritBank.getCDOfferings();
		return cdOffering;
	}

}

