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
		if(customers.containsKey(customer.getKey())) {
			switch(request.toLowerCase()) {
				case "show my accounts" : return showMyAccounts(customer);
				case "withdraw" : return "" +  withdrawTransaction(customer,in,out);
				//case "deposit" : return depositTransaction();


				/*case "deposit" : {out.println("Please select account to which you would like to deposit: "
				);
					out.println(showMyAccounts(customer));
					try {
						String response = in.readLine();
						return "" + depositTransaction(customers.get(customer.getKey()), 1200) + " Please enter deposit amount";
					} catch (IOException e){
						out.println("error");
					}
				} */
				default : return "FAIL";
			}
		}
		return "FAIL";
	}

	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}
	private double withdrawTransaction(CustomerID customer,BufferedReader in ,PrintWriter out){
		out.println("Please select account from which you would like to withdraw: "
		);
			out.println(showMyAccounts(customer));
			try {
				String account = in.readLine();
			out.println("How much would you like to withdraw: ");
			double withdrawlAmount = Double.parseDouble(in.readLine());

			Customer customer_obj = customers.get(customer.getKey());
			Account account_obj =  customer_obj.getTransactionAccount(account);

			account_obj.setCurrentBalance(account_obj.getCurrentBalance() - withdrawlAmount);
			customer_obj.updateAccount(account_obj);
				return  account_obj.getCurrentBalance();

			} catch (IOException e){
				out.println("error");
			}
		return 0;
	}

	/*private double depositTransaction(Customer customer, double amount){
		return amount;
	}*/
}
