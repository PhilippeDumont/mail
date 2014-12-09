/**
 * 
 */
package client;

import java.io.IOException;

/**
 * @author philippe
 * 
 */
public class MainClient {

	public static void main(String[] args) throws IOException {

		Client client = new Client(8001, "localhost");
		client.run();

	}
}
