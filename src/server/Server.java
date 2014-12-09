/**
 * 
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import util.Protocol;
import controller.MailManager;
import entity.Mail;

/**
 * @author philippe
 * 
 */
public class Server {

	private String serverName = "localhost";
	private int port;
	private ServerSocket welcomingSocket;
	private MailManager mailManager;

	public Server(int port, String serverName) {
		this.serverName = serverName;
		this.port = port;
		this.mailManager = new MailManager();

	}

	public void run() throws IOException {

		welcomingSocket = new ServerSocket(port);

		this.welcomingSocket.setSoTimeout(100000);

		try {

			System.out.println("Waiting for client on port "
					+ welcomingSocket.getLocalPort() + "...");
			Socket server = welcomingSocket.accept();
			System.out.println("Local socket :"
					+ server.getLocalSocketAddress());
			System.out.println("Just connected to "
					+ server.getRemoteSocketAddress());

			DataInputStream in = new DataInputStream(server.getInputStream());
			DataOutputStream out = new DataOutputStream(
					server.getOutputStream());

			String outMessage = "";
			String inMessage = "";

			while (!inMessage.equals("exit")) {

				inMessage = in.readUTF();

				if (inMessage.startsWith(Protocol.ADD)) {

					Mail mail = new Mail();
					mail.populat(inMessage.substring(4).split("\\s+"));
					System.out.println(mail.toString());

					this.mailManager.save(mail);

					out.writeUTF(Protocol.ACK);

				}

			}

			// closing socket
			server.close();
			System.out.println("Connection closed");

		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
