/**
 * 
 */
package client;

import java.io.File;
import java.util.Scanner;

/**
 * @author philippe
 *
 */
public class Display {

	public void Start() {
		System.out.println("Welcome in mail");
		String input = "";
		Scanner in = new Scanner(System.in);

		File dir = new File("events");
		if (!dir.exists()) {
			dir.mkdir();
		}

		// Enter a loop to parse all input.
		do {
			System.out.println(">");
			input = in.nextLine();

			// Write a new mail
			if (input.equals("new")) {
				this.newMail();
				continue;
			}

			if (input.equals("load")) {
				this.loadMail();
			}

			// Error
			System.out.println("Illegal command: " + input);

		} while (!input.equals("quit"));
		// Cleanup.
		System.out.println("EventManager is terminating...");
		in.close();
	}

	public void newMail() {
	}

	public void loadMail() {
	}

}
