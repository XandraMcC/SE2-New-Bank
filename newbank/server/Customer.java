package newbank.server;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class Customer {
	private final String name;
	private String password;
	private final HashMap<String, Account> accounts;

	public Customer(String name, String password) {
		this.name = name;
		this.password = password;
		accounts = new HashMap<>();
	}

	public String accountsToString() {
		StringBuilder s = new StringBuilder("Accounts: ");
		for (Account a : accounts.values()) {
			s.append(a.getAccountName());
			s.append("\n");
		}
		return s.toString();
	}

	public void addAccount(Account account) {
		accounts.put(account.getAccountName(), account);
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
	 * Method to allow account to be transacted upon to be accessed
	 * @param accountName
	 * @return
	 */
	public Account getAccount(String accountName) {
		return accounts.get(accountName);
	}

	public Account newAccount(String accountName, Currency amount) throws Exception {
		if (hasAccount(accountName)) {
			throw new Exception("Already have an account with that name");
		}
		Account newAccount = new Account(accountName, amount);
		accounts.put(accountName, newAccount);
		return newAccount;
	}

	/**
	 * Method to return true or false if customer has acc type.
	 * @param accountName
	 * @return
	 */
	public boolean hasAccount(String accountName){
		return accounts.containsKey(accountName);
	}
}