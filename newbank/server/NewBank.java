package newbank.server;

import java.util.HashMap;

public class NewBank {
	
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;
	
	private NewBank() {
		customers = new HashMap<>();
		addTestData();
	}
	
	private void addTestData() {
		Customer bhagy = new Customer();
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put("Bhagy", bhagy);
		
		Customer christina = new Customer();
		christina.addAccount(new Account("Savings", 1500.0));
		customers.put("Christina", christina);
		
		Customer john = new Customer();
		john.addAccount(new Account("Checking", 250.0));
		john.addAccount(new Account("Main", 1250.0));
		john.addAccount(new Account("Savings", 50.0));
		customers.put("John", john);
	}
	
	public static NewBank getBank() {
		return bank;
	}
	
	public synchronized CustomerID checkLogInDetails(String userName, String password) {
		if(customers.containsKey(userName)) {
			return new CustomerID(userName);
		}
		return null;
	}
	public String AccountType; //New string to help with type of account typed in by user - useful for currentbalance and possibly more.

	// commands from the NewBank customer are processed in this method
	public synchronized String processRequest(CustomerID customer, String request) {
		if(customers.containsKey(customer.getKey())) {
			switch(request) {
			case "SHOWMYACCOUNTS" : return showMyAccounts(customer);


			//The Next three cases deal with getting the current balance for a specific account type.
			case "Show Current Balance for Main" :
				AccountType = "Main";
				return ShowMyBal(customer, AccountType);

			case "Show Current Balance for Checking" :
				AccountType = "Checking";
				return ShowMyBal(customer, AccountType);

			case "Show Current Balance for Savings" :
				AccountType = "Savings";
				return ShowMyBal(customer, AccountType);

			default : return "FAIL";
			}


		}
		return "FAIL";
	}
	
	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

	//Handles retrieving the current balance for specific type of account.
	private String ShowMyBal(CustomerID customer, String A){return (customers.get(customer.getKey())).CurrentBalanceToString(A);}

}
