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

	Command command;

	public Display(Command command) {
		super();
		this.command = command;
	}

	public void start() {
		System.out.println("Welcome in mail");
		String input = "";
		Scanner in = new Scanner(System.in);

		File dir = new File("mails");
		if (!dir.exists()) {
			dir.mkdir();
		}

		// Enter a loop to parse all input.
		do {
			System.out.println(">");
			input = in.nextLine();

			// Login
			if (input.equals("login")) {
				this.command.login();
				continue;
				}


			// Error
			System.out.println("Illegal command: " + input);

		} while (!input.equals("quit"));
		// Cleanup.
		System.out.println("EventManager is terminating...");
		in.close();
	}

	public void commandInterface() {
		System.out.println("Welcome in mail");
		String input = "";
		Scanner in = new Scanner(System.in);

		// Enter a loop to parse all input.
		do {
			System.out.println(">");
			input = in.nextLine();

			// Write a new mail
			if (input.equals("new")) {
				this.command.newMail();
				continue;
			}

			if (input.equals("load")) {
				this.command.loadMail();
			}

			// Error
			System.out.println("Illegal command: " + input);

		} while (!input.equals("quit"));
		// Cleanup.
		System.out.println("EventManager is terminating...");
		in.close();

	}



}
