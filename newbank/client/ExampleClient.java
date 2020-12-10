package newbank.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ExampleClient extends Thread{
	
	private Socket server;
	private PrintWriter bankServerOut;	
	private BufferedReader userInput;
	private Thread bankServerResponseThread;

	/**
	 * Constructs a client class, this reads input from the terminal sending it to the server
	 * and receives text from the server writing this to the terminal
	 * @param ip server address
	 * @param port server port
	 * @throws UnknownHostException if the sever connection fails
	 * @throws IOException if there is an error reading from the terminal
	 */
	public ExampleClient(String ip, int port) throws UnknownHostException, IOException {
		server = new Socket(ip,port);
		userInput = new BufferedReader(new InputStreamReader(System.in)); 
		bankServerOut = new PrintWriter(server.getOutputStream(), true);
		bankServerResponseThread = new Thread() {
			private BufferedReader bankServerIn = new BufferedReader(new InputStreamReader(server.getInputStream())); 
			public void run() {
				try {
					while(true) {
						String response = bankServerIn.readLine();
						System.out.println(response);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		bankServerResponseThread.start();
	}

	/**
	 * Reads input from the terminal and sends it to the server
	 */
	public void run() {
		while(true) {
			try {
				while(true) {
					String command = userInput.readLine();
					bankServerOut.println(command);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Main entry point for client application
	 * @param args command line arguments (not processed)
	 * @throws UnknownHostException if the sever connection fails
	 * @throws IOException if there is an error reading from the terminal
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		new ExampleClient("localhost",14002).start();
	}
}
