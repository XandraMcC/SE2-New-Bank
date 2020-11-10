package newbank.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NewBankClientHandler extends Thread{
	
	private NewBank bank;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Constructs a NewBankClientHandler class to handle the connection from a
	 * client
	 *
	 * @param s the network socket of the connection
	 * @throws IOException
	 */
	public NewBankClientHandler(Socket s) throws IOException {
		bank = NewBank.getBank();
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
	}

	/**
	 * Writes output to the client, reads and processes responses
	 */
	public void run() {
		try {
			CustomerID customer = null;
			while (true) {
				// keep getting requests from the client and processing them
				if (customer == null) {
					customer = handleLogin();
				} else if (customer != null) {
					// if the user is authenticated then get requests from the user and process them
					out.println("What do you want to do?");
					String request = in.readLine();
					System.out.println("Request from " + customer.getKey());
					String response = bank.processRequest(customer, request);
					out.println(response);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * Presents the login UI asking for username and password
	 *
	 * @return the customerID of the logged in user or null if the login failed
	 * e.g. incorrect username or password
	 *
	 * TODO usernames should be case insensitive
	 *
	 * TODO limit login attempts per session to mitigate brute force attacks
	 */
	private CustomerID handleLogin() throws IOException {
		// ask for user name
		out.println("Enter Username");
		String userName = in.readLine();
		// ask for password
		out.println("Enter Password");
		String password = in.readLine();
		out.println("Checking Details...");
		// authenticate user and get customer ID token from bank for use in
		// subsequent requests
		CustomerID customer = bank.checkLogInDetails(userName, password);
		if (customer == null) {
			out.println("Log In Failed");
			return null;
		}
		out.println("Log In Successful.");
		return customer;
	}

}
