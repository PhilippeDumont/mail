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
		System.out.println("+++++++++++++++");
		System.out.println("Welcome in mail");
		System.out.println("+++++++++++++++");

		boolean correctLogin = false;
		String input = "";
		Scanner in = new Scanner(System.in);

		File dir = new File("mails");
		if (!dir.exists()) {
			dir.mkdir();
		}

		// Enter a loop to parse all input.
		do {
			System.out.println("Enter your login :");
			input = in.nextLine();

			// Login
			if (!input.equals("quit")) {
				this.command.login(input);
				correctLogin = true;
				continue;
			}


		} while (!input.equals("quit") || correctLogin);

		in.close();
	}

	public void commandInterface() {

		System.out.println("Enter an instruction or enter help");


		String input = "";
		Scanner in = new Scanner(System.in);

		// Enter a loop to parse all input.
		do {
			System.out.println(">");
			input = in.nextLine();

			// Write a new mail
			if (input.startsWith("new")) {
				this.command.newMail(input.substring(4).split("\\s+"));
				continue;
			}

			if (input.equals("load")) {
				this.command.loadMail();
				continue;
			}

			if (input.equals("help")) {
				this.command.help();
				continue;
			}

			// Error
			System.out.println("Illegal command: " + input);

		} while (!input.equals("quit"));
		// Cleanup.
		System.out.println("EventManager is terminating...");
		in.close();

	}

}
