package newbank.server;

import newbank.server.commands.*;
import java.util.HashMap;

public class NewBank {

	private static final NewBank bank = new NewBank();
	private final HashMap<String, Customer> customers;
	private final HashMap<String, Command> commands;
	public LoanMarket market = new LoanMarket();

	/**
	 * Constructs NewBank loading initial data
	 */
	private NewBank() {
		customers = loadTestData();
		commands = loadCommands();
	}

	/**
	 * @return the commands implemented by NewBank
	 */
	public HashMap<String, Command> getCommands() {
		return commands;
	}

	/**
	 * @return loads all of the commands which can be called
	 */
	private HashMap<String, Command> loadCommands() {
		HashMap<String, Command> commandHashMap = new HashMap<>();

		addCommand(commandHashMap, new AddAccountCommand());
		addCommand(commandHashMap, new ChangePasswordCommand());
		addCommand(commandHashMap, new DepositCommand());
		addCommand(commandHashMap, new EndCommand());
		addCommand(commandHashMap, new MakePaymentCommand(customers));
		addCommand(commandHashMap, new OfferLoanMarketCommand(customers));
		addCommand(commandHashMap, new SetOverdraftCommand());
		addCommand(commandHashMap, new ShowBalanceCommand());
		addCommand(commandHashMap, new ShowMyAccountsCommand());
		addCommand(commandHashMap, new ShowStatusCommand());
		addCommand(commandHashMap, new ShowTransactionHistory());
		addCommand(commandHashMap, new WithdrawCommand());
		addCommand(commandHashMap, new ViewOffersCommand());
		return commandHashMap;
	}

	/**
	 * Adds a command to the hash map of commands
	 * @param commandHashMap the hashmap to which the command is added
	 * @param command the command to add
	 */
	private void addCommand(HashMap<String, Command>commandHashMap, Command command) {
		commandHashMap.put(command.getIdentifier(), command);
	}

	/**
	 * @return the test data which has been loaded
	 */
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
		customers.put(john.getName(), john);

		Customer manager = new Customer("MANAGER", "manager");
		manager.addAccount(new Account("Checking", Currency.FromDouble(2000250.0)));
		customers.put(manager.getName(), manager);

		Customer test = new Customer("Test", "test");
		test.addAccount(new Account("Checking", Currency.FromDouble(20250.0)));
		test.addAccount(new Account("Savings", Currency.FromInteger(10000)));
		test.addAccount(new Account("Main", Currency.FromInteger(60000)));
		customers.put(test.getName(), test);

		return customers;
	}

	/**
	 * @return the singleton instance of this NewBank class
	 */
	public static NewBank getBank() {
		return bank;
	}

	/**
	 * @param userName the login name (case sensitive)
	 * @param password the login password (case sensitive)
	 * @return the CustomerID associated with username and password, or null
	 */
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
	 * @param customerID the logged in customer
	 * @param request the text to be processed
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

