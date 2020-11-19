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
		this.accounts = new ArrayList<>();
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public String printAccounts() {
		String s = "";
		for (Account a : accounts) {
			s += a.toString() + "\n";
		}
		return s;
	}

	public void setName(String name) {
		this.name = password;
	}

	public String getName() {
		return name;
	}

	/**
	 * Updates customer's password
	 * @param password new value
	 * @throws InvalidParameterException if password is 4 characters or less
	 */
	public void setPassword(String password) throws InvalidParameterException {
		if (password.length() > 4) {
			this.password = password;
		} else {
			throw new InvalidParameterException();
		}
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

	/**
	 * Withdraw or deposit money
	 * @param accName
	 * @param amount
	 * @param action
	 * @return
	 */
	public String transaction(String accName, float amount, String action) {
		for (Account a : accounts) {
			if (a.getAccountName().equals(accName)) {
				if (action.equals("deposit")) {
					a.deposit(amount);
				}
				else if (action.equals("withdraw")) {
					a.withdraw(amount);
				}
				else {
					return "Action not valid";
				}
			}
		}
		return "Account not found: " + accName;
	}
	public String newACC(String accType, double Amount) {
		for (Account a : accounts){
			if (a.getAccountName().equals(accType)){
				return "Account Type Already Exists " + currentBalance(accType);
			}
		}
		addAccount(new Account(accType, Amount));
		return "New Account Added " + currentBalance(accType);
	}

	/**
	 * Method to return true or false if customer has acc type.
	 * @param accType
	 * @return
	 */
	public boolean hasACC(String accType){
		for (Account a : accounts){
			if(a.getAccountName().equals(accType)){
				return true;
			}
		}
		return false;
	}
}