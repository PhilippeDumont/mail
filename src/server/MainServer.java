/**
 * 
 */
package server;

import java.io.IOException;

/**
 * @author philippe
 * 
 */
public class MainServer {

	public static void main(String[] args) {

		Server server = new Server(8001, "localhost");

		try {
			server.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
