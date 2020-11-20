package newbank.server;

import newbank.server.commands.*;
import java.util.HashMap;

public class NewBank {
	private static final NewBank bank = new NewBank();
	private HashMap<String, Customer> customers;
	private HashMap<String, Command> commands;

	private NewBank() {
		customers = loadTestData();
		commands = loadCommands();
	}

	private HashMap<String, Command> loadCommands() {
		HashMap<String, Command> commandHashMap = new HashMap<>();

		addCommand(commandHashMap, new AddAccountCommand());
		addCommand(commandHashMap, new ChangePasswordCommand());
		addCommand(commandHashMap, new DepositCommand());
		addCommand(commandHashMap, new MakePaymentCommand());
		addCommand(commandHashMap, new ShowBalanceCommand());
		addCommand(commandHashMap, new ShowStatusCommand());
		addCommand(commandHashMap, new WithdrawCommand());

		return commandHashMap;
	}

	private void addCommand(HashMap<String, Command>commandHashMap, Command command) {
		commandHashMap.put(command.getIdentifier(), command);
	}

	private HashMap<String, Customer> loadTestData() {
		HashMap<String, Customer> customers = new HashMap<>();

		Customer bhagy = new Customer("Bhagy", "bhagy");
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put(bhagy.getName(), bhagy);

		Customer christina = new Customer("Christina", "christina");
		christina.addAccount(new Account("Savings", 1500.0));
		customers.put(christina.getName(), christina);

		Customer john = new Customer("John", "john");
		john.addAccount(new Account("Checking", 250.0));
		john.addAccount(new Account("Savings", 111));
		customers.put(john.getName(), john);

		Customer manager = new Customer("MANAGER", "manager");
		manager.addAccount(new Account("Checking", 250.0));
		manager.addAccount(new Account("Savings", 111));
		customers.put(manager.getName(), manager);

		return customers;
	}

	public static NewBank getBank() {
		return bank;
	}

	public synchronized CustomerID checkLogInDetails(String userName, String password) {
		if (customers.containsKey(userName)) {
			if (customers.get(userName).getPassword().equals(password)) {
				return new CustomerID(userName);
			}
		}
		return null;
	}

	/**
	 * commands from the NewBank customer are processed in this method
	 *
	 * @param customerID
	 * @param request
	 * @return
	 */
	public synchronized String processRequest(CustomerID customerID, String request) {

		Customer customer = customers.get(customerID);
		if (customer == null) {
			return "FAIL";
		}

		String[] arguments = request.split(" ", 1);
		if (arguments.length < 1) {
			return "FAIL";
		}

		Command command = commands.get(arguments[0]);
		if (command == null) {
			return "FAIL";
		}
		if (arguments.length >= 1) {
			return command.process(customer, arguments[1]);
		}
		return command.process(customer, "");
	}

	/**
	 * Handles retrieving the current balance for specific type of account.
	 * @param customer
	 * @param accType
	 * @return
	 */
	private String ShowMyBal(CustomerID customer, String accType) {
   		return (customers.get(customer.getKey())).currentBalance(accType);
	}

	/**
	 * method to deposit money, takes account type and amount to deposit
	 * accesses the Deposit method in Customer which returns correct format for this function
	 * @param customer
	 * @param accType
	 * @param amount
	 * @return
	 */
	private String depositTransaction(CustomerID customer, String accType, String amount) {
		return (customers.get(customer.getKey()).Deposit(accType, amount));
	}

  	private String showCurrentStatus(CustomerID customer, String accType) {
		return (customers.get(customer.getKey())).currentBalance(accType);
	}
	/* * method to withdraw money, takes account type and amount to deposit
	accesses Withdraw function.
	 */
	private String withdrawTransaction(CustomerID customer, String accType, String amount){
		return (customers.get(customer.getKey()).Withdraw(accType, amount));
	}
	private String makePAY(CustomerID customer, String accType, String payee, String payeeACC, String Amount){
		if(customers.get(customer.getKey()).hasACC(accType)){
			if(customers.containsKey(payee)){
				if(customers.get(payee).hasACC(payeeACC)){
					return customer.getKey() + " " + customers.get(customer.getKey()).Withdraw(accType, Amount) + "\n" + customers.get(payee).Deposit(payeeACC, Amount) + " to " + payee;
				}
				return "Payee Account not found!";
			}
			return "Payee Not Found!";
		}
		return "Your Account Not found!";
	}
	private String addACC(CustomerID customer, String AccType, String openBAL){
		return customers.get(customer.getKey()).newACC(AccType, Double.parseDouble(openBAL));
	}
}

