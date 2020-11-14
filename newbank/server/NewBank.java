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
		Customer bhagy = new Customer("Bhagy", "bhagy");
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put(bhagy.getName(), bhagy);
		
		Customer christina = new Customer("Christina", "christina");
		christina.addAccount(new Account("Savings", 1500.0));
		customers.put(christina.getName(), christina);
		
		Customer john = new Customer("John", "john");
		john.addAccount(new Account("Checking", 250.0));
		customers.put(john.getName(), john);
	}
	
	public static NewBank getBank() {
		return bank;
	}
	
	public synchronized CustomerID checkLogInDetails(String userName, String password) {
		if(customers.containsKey(userName)) {
			if (customers.get(userName).getPassword().equals(password)) {
				return new CustomerID(userName);
			}
		}
		return null;
	}

	// commands from the NewBank customer are processed in this method
	public synchronized String processRequest(CustomerID customer, String request) {
		if (!customers.containsKey(customer.getKey())) {
			return "FAIL";
		}
		String[] arguments = request.split(" ");
		if (arguments.length >= 1) {
			switch (arguments[0]) {
				case "SHOWMYACCOUNTS":
					return showMyAccounts(customer);
				case "CHANGEPASSWORD":
					if (arguments.length >= 2) {
						return changePassword(customer, arguments[1]);
					} else {
						return "FAIL New password not specified";
					}

			}
		}
		return "FAIL";
	}

	/**
	 * Updates a customers password
	 *
	 * @param customer the customerID to be updated
	 * @param newPassword must be longer than 4 characters
	 * @return a status message for display to the user
	 */
	private String changePassword(CustomerID customer, String newPassword) {
		try {
			customers.get(customer.getKey()).updatePassword(newPassword);
			return "Password updated";
		} catch (Exception e) {
			return "FAIL password not updated";
		}
	}

	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

}
