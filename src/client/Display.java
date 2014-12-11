/**
 * 
 */
package client;

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

	public void commandInterface() {

		System.out.println("+++++++++++++++");
		System.out.println("Welcome in mail");
		System.out.println("+++++++++++++++");

		System.out.println("Enter an instruction or enter help");

		String input = "";
		Scanner in = new Scanner(System.in);

		// Enter a loop to parse all input.
		do {
			System.out.println(">");
			input = in.nextLine();

			if (input.startsWith("login")) {

				try {
					this.command.login(input.substring(6).split("\\s+"));
				} catch (StringIndexOutOfBoundsException e) {
					System.out.println("Enter login [name]");
				}
				continue;
			}

			// Write a new mail
			if (input.startsWith("send")) {

				try {
					this.command.sendMail(input.substring(5).split("\\s+"));
				} catch (StringIndexOutOfBoundsException e) {
					System.out
							.println("Enter send [destination] [title] [content]");
				}
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
