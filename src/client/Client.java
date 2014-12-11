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

	private static String serverName;
	private static int port;

	private Command command;
	private Display display;

	public Client(int port, String serverName) {
		this.serverName = serverName;
		this.port = port;

	}

	public void run() {

		try {

			Socket client = new Socket(serverName, port);

			DataOutputStream out = new DataOutputStream(
					client.getOutputStream());
			DataInputStream in = new DataInputStream(client.getInputStream());

			this.command = new Command(in, out);
			this.display = new Display(command);
			display.commandInterface();

			// closing socket
			client.close();
			System.out.println("End of the application");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
