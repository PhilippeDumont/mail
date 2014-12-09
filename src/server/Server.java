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

/**
 * @author philippe
 * 
 */
public class Server {
	public static void main(String[] args) throws IOException {
		// to use arguments
//		int port = Integer.parseInt(args[0]);
		
		String serverName = "localhost";
		
		int port = 8080;


		// creation of the welcoming socket for listening on specified port
		ServerSocket welcomingSocket = new ServerSocket(port);

		// time out for incoming clients on welcomingSocket
		welcomingSocket.setSoTimeout(100000);

		try {
			// waiting for an incoming client
			// acceptance means creation of the server socket connected to the
			// client one
			System.out.println("Waiting for client on port "
					+ welcomingSocket.getLocalPort() + "...");
			Socket server = welcomingSocket.accept();
			System.out.println("Local socket :"
					+ server.getLocalSocketAddress());
			System.out.println("Just connected to "
					+ server.getRemoteSocketAddress());

			// data streams from/to client socket
			DataInputStream in = new DataInputStream(server.getInputStream());
			DataOutputStream out = new DataOutputStream(
					server.getOutputStream());
			String outMessage = "";
			String inMessage = "";
			// manipulating bytes can be useful
			// byte[] receivedBytes=new byte[256];

			while (!inMessage.equals("exit")) {

				// receiving message from client socket (using DataInputStream)
				// readUTF method allows for directly receive "UTF-8" Strings
				inMessage = in.readUTF();
				// or, you can read bytes and interpret them as "UTF-8" Strings
				// in.read(receivedBytes);
				// inMessage = new String(receivedBytes, "UTF-8");

				System.out.println("[Received message] -> " + inMessage);

				// sending message through server socket (using
				// DataOutputStream)
				// writeUTF method allows for directly send "UTF-8" Strings
				out.writeUTF(inMessage);
				// or, you can send bytes
				// out.write(receivedBytes);
				System.out.println("[Replied message] -> " + inMessage);
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
