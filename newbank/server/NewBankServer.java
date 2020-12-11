package newbank.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NewBankServer extends Thread {
	
	private final ServerSocket server;

	/**
	 * Constructor sets up a NewBankServer
	 * @param port to listen for connections on
	 * @throws IOException if there is a network error
	 */
	public NewBankServer(int port) throws IOException {
		server = new ServerSocket(port);
	}

	/**
	 * Starts a new NewBankServer thread on port 14002
	 * @param args not used
	 * @throws IOException if there is an error reading data
	 */
	public static void main(String[] args) throws IOException {
		new NewBankServer(14002).start();
	}

	/**
	 * Main loop which starts up a new client handler thread to receive incoming
	 * connections and process requests
	 */
	public void run() {
		System.out.println("New Bank Server listening on " + server.getLocalPort());
		try {
			while(true) {
				Socket s = server.accept();
				NewBankClientHandler clientHandler = new NewBankClientHandler(s);
				clientHandler.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
}
