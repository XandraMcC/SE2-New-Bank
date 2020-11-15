package newbank.server;

import java.io.*;
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
		john.addAccount(new Account("Savings", 111));
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

	// commands from the NewBank customer are processed in this method
	public synchronized String processRequest(CustomerID customer, String request, BufferedReader in, PrintWriter out) {
		if (!customers.containsKey(customer.getKey())) {
			return "FAIL";
		}

		String[] UserInputs = request.split(" "); //split by spaces
		if (UserInputs.length < 1) {
			return "FAIL";
		}

		switch(UserInputs[0]){
			// options available
			case "SHOWMYACCOUNTS" :
				return showMyAccounts(customer);

			case "WITHDRAW" :
				return withdrawTransaction(customer,UserInputs[1],UserInputs[2]);

			case "deposit" :
				return withdrawTransaction(customer,UserInputs[1],UserInputs[2]);

				default : return "FAIL";
		}
	}

	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

	String account;

	private String showCurrentStatus(CustomerID customer) {
		return (customers.get(customer.getKey())).currentBalance(account);
	}

	private String withdrawTransaction(CustomerID customer,String accType ,String amount){

		return (customers.get(customer.getKey()).Withdraw(accType, amount));
	}

	private double depositTransaction(CustomerID customer,BufferedReader in ,PrintWriter out) {
		return 0;
	}
}
