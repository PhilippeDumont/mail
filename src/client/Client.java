/**
 * 
 */
package client;

/**
 * @author philippe
 *
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	private static String serverName = "localhost";
	private static int port = 8001;
	private boolean access = false;

	private Command command;
	private Display display;

	private String address;
	private String pseudo;

	public void run() {

		try {

			Socket client = new Socket(serverName, port);

			System.out.println("Trying connecting to..." + serverName
					+ " on port " + port);
			System.out.println("Local socket :"
					+ client.getLocalSocketAddress());
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());

			DataOutputStream out = new DataOutputStream(
					client.getOutputStream());
			DataInputStream in = new DataInputStream(client.getInputStream());

			this.command = new Command(in, out);
			this.display = new Display(command);

			display.commandInterface();

			// closing socket
			client.close();
			System.out.println("Connection closed");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
