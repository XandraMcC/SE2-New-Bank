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


	/**
	 * commands from the NewBank customer are processed in this method
	 *
	 */
	public synchronized String processRequest(CustomerID customer, String request) {
		if (!customers.containsKey(customer.getKey())) {
			return "FAIL";
		}

		String [] UserInputs = request.split(" "); //Using spaces to split a user inputted line.
		if (UserInputs.length < 1){ // If user doesn't enter anything.
			return "No Input";
		}

		switch(UserInputs[0]) { //Takes the first index of UserInputs, it is the action requested from user.
		case "SHOWMYACCOUNTS" : return showMyAccounts(customer);

		case "SHOWCURRENTBALANCE":
			if (UserInputs.length == 2){
				return ShowMyBal(customer, UserInputs[1]); //Passes the account type to ShowMyBal to get curr bal.
			}
			return "Incorrect Usage"; // Handling if SHOWCURRENTBALANCE does not have just account type after.

		default : return "FAIL";
		}


	}
	
	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

	//Handles retrieving the current balance for specific type of account.
	private String ShowMyBal(CustomerID customer, String AccType){return (customers.get(customer.getKey())).CurrentBalanceToString(AccType);}

}
