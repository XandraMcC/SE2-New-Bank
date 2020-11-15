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
			s += a.toString();
		}
		return s;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}


	// Creates a method for a deposit which can be accessed in NewBank
	public String Deposit(String accType, String amount){

		String s = null;
		for (Account a : accounts) {
			if (a.getAccountName().equals(accType)) {
				s = a.getAccountName() + ": " + (a.getCurrentBalance() + Double.parseDouble(amount));
				return s;
			}
		}
		return s;
	}

	/**
	 * Updates customer's password
	 *
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
}
