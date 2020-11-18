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
	 * Method to return the current balance
	 * @param accountType
	 * @return
	 */
	public String currentBalance(String accountType) {
		if (accountType.equals("ALL")){
			String allBAL = "";
			for (Account allACC : accounts){
				allBAL += allACC.getAccountName() + ": " + allACC.getCurrentBalance() + "\n";
			}
			return allBAL;
		}
		else {
			for (Account a : accounts) {
				if (a.getAccountName().equals(accountType)) {
					String s = a.getAccountName() + ": " + a.getCurrentBalance();
					return s;
				}
			}
			return "Error Account type not found.";
		}
	}
	public String Withdraw(String accType, String amount){
		for (Account a : accounts) {
			if (a.getAccountName().equals(accType)) {
				String s = a.getAccountName() + ": " + (a.getCurrentBalance() - Double.parseDouble(amount));
				return s;
			}
		}
		return null;
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
}