package newbank.server;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Customer {
	private String name;
	private String password;
	private ArrayList<Account> accounts;

	public Customer(String name, String password) {
		this.name = name;
		this.password = password;
		accounts = new ArrayList<>();
	}

	public String accountsToString() {
		String s = "";
		for (Account a : accounts) {
			s += a.toString() + "\n";
		}
		return s;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	/**
	 * Creates a method for a deposit which can be accessed in NewBank
	 * @param accType
	 * @param amount
	 * @return
	 */
	public String Deposit(String accType, float amount) {
		String s = null;
		for (Account a : accounts) {
			if (a.getAccountName().equals(accType)) {
				a.setCurrentBalance(a.getCurrentBalance() + amount);
				return "Deposited £" + amount + " to account " + a.getAccountName();
			}
		}
		return "Account not found: " + accType;
	}

	/**
	 * Updates customer's password
	 * @param password new value
	 * @throws InvalidParameterException if password is 4 characters or less
	 */
	public void updatePassword(String password) throws InvalidParameterException {
		if (password.length() > 4) {
			this.password = password;
		} else {
			throw new InvalidParameterException();
		}
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}

	/**
	 * Method to allow access to update account balances
	 * @param account
	 */
	public void updateAccount(Account account){
		for(int i =0; i <accounts.size(); i ++) {
			if (account.getAccountName().equals(accounts.get(i).getAccountName())){
				accounts.set(i,account);
			}
		}
	}

	/**
	 * Method to allow account to be transacted upon to be accessed
	 * @param accountName
	 * @return
	 */
	public Account getTransactionAccount(String accountName) {
		Account account = null;
		for (Account a : accounts) {
			if (accountName.equals(a.getAccountName())) {
				account = a;
			}
		}
		return account;
	}

	/**
	 * Method to get current balance of specific account type.
	 * @param accountType
	 * @return
	 */
	public String currentBalance(String accountType) {
		String NoACC = "Error No Account Found";
		if (accountType.equals("ALL")) {
			String allBal = "";
			for (Account allACC : accounts) {
				allBal += allACC.getAccountName() + ": " + allACC.getCurrentBalance() + "\n";
			}
			return allBal;
		} else {
			for (Account all : accounts) { // Loop through each of the customers accounts.
				if (all.getAccountName().equals(accountType)) { // If the chosen account is matched with an actual account balance is returned.
					String currBal = all.getAccountName() + ": " + all.getCurrentBalance() + "\n";
					return currBal;
				}
			}
		}
		return NoACC; //If no account is found with that name return error.
	}


	public String Withdraw(String accType, float amount) {
		for (Account a : accounts) {
			if (a.getAccountName().equals(accType)) {
				if (a.getCurrentBalance() < amount) {
					return "Cannot withdraw £" + amount + " from " + a.getAccountName() + " not enough funds";
				}
				a.setCurrentBalance(a.getCurrentBalance() - amount);
				return "Withdrew £" + amount + " from " + a.getAccountName();
			}
		}
		return "Account not found: " + accType;
	}
}