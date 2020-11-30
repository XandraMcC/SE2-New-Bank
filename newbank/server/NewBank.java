package newbank.server;

import newbank.server.commands.*;
import java.util.HashMap;

public class NewBank {
	private static final NewBank bank = new NewBank();
	private final HashMap<String, Customer> customers;
	private final HashMap<String, Command> commands;

	private NewBank() {
		customers = loadTestData();
		commands = loadCommands();
	}

	public HashMap<String, Command> getCommands() {
		return commands;
	}

	private HashMap<String, Command> loadCommands() {
		HashMap<String, Command> commandHashMap = new HashMap<>();

		addCommand(commandHashMap, new AddAccountCommand());
		addCommand(commandHashMap, new ChangePasswordCommand());
		addCommand(commandHashMap, new DepositCommand());
		addCommand(commandHashMap, new EndCommand());
		addCommand(commandHashMap, new MakePaymentCommand(customers));
		addCommand(commandHashMap, new ShowBalanceCommand());
		addCommand(commandHashMap, new ShowMyAccountsCommand());
		addCommand(commandHashMap, new ShowStatusCommand());
		addCommand(commandHashMap, new WithdrawCommand());
		addCommand(commandHashMap, new SetOverdraftCommand());

		return commandHashMap;
	}

	private void addCommand(HashMap<String, Command>commandHashMap, Command command) {
		commandHashMap.put(command.getIdentifier(), command);
	}

	private HashMap<String, Customer> loadTestData() {
		HashMap<String, Customer> customers = new HashMap<>();

		Customer bhagy = new Customer("Bhagy", "bhagy");
		bhagy.addAccount(new Account("Main", Currency.FromDouble(1000.0)));
		customers.put(bhagy.getName(), bhagy);

		Customer christina = new Customer("Christina", "christina");
		christina.addAccount(new Account("Savings", Currency.FromDouble(1500.0)));
		customers.put(christina.getName(), christina);

		Customer john = new Customer("John", "john");
		john.addAccount(new Account("Checking", Currency.FromDouble(250.0)));
		john.addAccount(new Account("Savings", Currency.FromInteger(111)));
		customers.put(john.getName(), john);

		Customer manager = new Customer("MANAGER", "manager");
		manager.addAccount(new Account("Checking", Currency.FromDouble(250.0)));
		manager.addAccount(new Account("Savings", Currency.FromInteger(111)));
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
	 * commands from the user are processed in this method
	 *
	 * @param customerID
	 * @param request
	 * @return result presented to user
	 */
	public synchronized String processRequest(CustomerID customerID, String request) {

		Customer customer = customers.get(customerID.getKey());
		if (customer == null) {
			return "FAIL Not logged in";
		}

		String[] arguments = request.split(" ", 2);
		if (arguments.length < 1) {
			return "FAIL Command not entered";
		}

		Command command = commands.get(arguments[0]);
		if (command == null) {
			return "FAIL Command not recognised";
		}
		if (arguments.length >= 2) {
			return command.process(customer, arguments[1]);
		}
		return command.process(customer, "");
	}
}

