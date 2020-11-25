package newbank.server;

import java.security.InvalidParameterException;

public class Account {

	private final String accountName;
	private Currency currentBalance;
	private Currency overdraftLimit;

	/**
	 * Construct a new account with zero balance and no overdraft facility
	 * @param accountName e.g. savings
	 * @throws  InvalidParameterException if accountName is blank
	 */
	public Account(String accountName) throws InvalidParameterException {
		if (accountName.length() <= 0) {
			throw new InvalidParameterException("accountName is blank");
		}
		this.accountName = accountName;
		this.currentBalance = new Currency(0);
		this.overdraftLimit = new Currency(0);
	}

	/**
	 * Construct a new account a set balance and no overdraft facility
	 * @param accountName e.g. savings
	 * @param openingBalance e.g. £5.00
	 * @throws  InvalidParameterException if opening balance is less than £0 or
	 *                                 accountName is blank, or any parameters are null
	 */
	public Account(String accountName, Currency openingBalance) throws InvalidParameterException {
		if (accountName.length() <= 0) {
			throw new InvalidParameterException("accountName is blank");
		}
		if (openingBalance == null) {
			throw new InvalidParameterException("openingBalance is null");
		}
		if (openingBalance.getValue() < 0) {
			throw new InvalidParameterException("openingBalance is negative with no overdraft set");
		}
		this.accountName = accountName;
		this.currentBalance = openingBalance;
		this.overdraftLimit = new Currency(0);
	}

	/**
	 * Construct a new account a set balance and overdraft facility
	 * @param accountName e.g. savings
	 * @param openingBalance e.g. £5.00
	 * @param overdraftLimit positive number
	 * @throws  InvalidParameterException if opening balance is overdrawn more than overdraftLimit,
	 *                                 accountName is blank, or any parameters are null
	 */
	public Account(String accountName, Currency openingBalance, Currency overdraftLimit) throws InvalidParameterException {
		if (accountName.length() <= 0) {
			throw new InvalidParameterException("accountName is blank");
		}
		if (openingBalance == null) {
			throw new InvalidParameterException("openingBalance is null");
		}
		if (overdraftLimit == null) {
			throw new InvalidParameterException("overdraftLimit is null");
		}
		if (openingBalance.getValue() < -overdraftLimit.getValue()) {
			throw new InvalidParameterException("openingBalance exceeds overdraftLimit");
		}
		this.accountName = accountName;
		this.currentBalance = openingBalance;
		this.overdraftLimit = overdraftLimit;
	}

	/**
	 * @return a string describing the name and state of the account
	 */
	public String toString() {
		return (accountName + " " + currentBalance + "(Overdraft limit: " + overdraftLimit + ")");
	}

	/**
	 * @return the name of the account e.g. savings
	 */
	public String getAccountName() {
		return accountName;
	}
  
	/**
	 * Method to retrieve the current balance of an account.
	 * @return currentBalance
	 */
	public Currency getBalance() {
		return currentBalance;
	}

	/**
	 *  Method to change the current balance. - Useful for transactions etc
	 * @param amount e.g. £5.00
	 */
	public void setBalance(Currency amount) {
		this.currentBalance = amount;
	}

	/**
	 * Checks if the account has an amount of funds available
	 * @param amount to check
	 * @return boolean
	 */
	public boolean hasFunds(Currency amount) {
		int availableFunds = currentBalance.getValue() + overdraftLimit.getValue();
		return amount.getValue() <= availableFunds;
	}

	/**
	 * Deposits money into an account
	 * @param amount e.g. £5.00
	 */
	public void deposit(Currency amount) {
		currentBalance.add(amount);
	}

	/**
	 * Withdraws money form the account
	 * @param amount e.g. £5.00
	 * @throws InsufficientFundsException if amount is not available
	 */
  public void withdraw(Currency amount) throws InsufficientFundsException {
  	if (!hasFunds(amount)) {
  		throw new InsufficientFundsException();
		}
		currentBalance.subtract(amount);
  }
}