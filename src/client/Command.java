/**
 * 
 */
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import util.Protocol;
import controller.MailManager;
import entity.Mail;

/**
 * @author philippe
 * 
 */
public class Command {

	private MailManager mailManager;
	private String login;

	private DataOutputStream out;
	private DataInputStream in;

	public Command(DataInputStream in, DataOutputStream out) {
		this.in = in;
		this.out = out;
	}

	/**
	 * Create a new mail and send on the server
	 * 
	 * @param values
	 */
	public void sendMail(String[] values) {

		if (this.login != null) {
			if (values.length > 3) {
				Mail mail = new Mail();

				mail.setSource(login);
				mail.setDestination(values[0]);
				mail.setTitle(values[1]);

				int i = 2;
				String content = "";
				while (i < values.length) {
					content += values[i] + " ";
					i++;
				}
				mail.setContent(content);

				try {
					out.writeUTF(Protocol.ADD + " " + mail.toString());
				} catch (IOException e) {
					System.out.println("Error when I send the mail");
					e.printStackTrace();
				}
				System.out.println("Send a new mail to "
						+ mail.getDestination());
			} else {
				System.out.println("There are not all the argument");
			}
		} else {
			System.out.println("Please enter your login to do this action");
		}
	}

	/**
	 * download the mail located on the server.
	 */
	public void loadMail() {

		if (this.login != null) {

			Mail mail = new Mail();
			boolean stackIsEmpty = false;
			String answer = "";

			try {

				while (!stackIsEmpty) {
					out.writeUTF(Protocol.LOAD + " " + this.login);
					answer = in.readUTF();

					if (answer.equals(Protocol.EMPTY)) {
						stackIsEmpty = true;
					} else {
						mail.populat(answer);
						String message = "-------------------------- \n From : "
								+ mail.getSource()
								+ "\n Title : "
								+ mail.getTitle()
								+ " \n Content : "
								+ mail.getContent();
						
						System.out.println(message);

						// this.mailManager.save(mail);
					}
				}

			} catch (Exception e) {
				System.out.println("Error of connection with the server");
			}
		} else {
			System.out.println("Please enter your login to do this action");
		}

	}

	/**
	 * Display a mail in the terminal
	 */
	public void ReadMail() {

	}

	public void login(String[] login) {
		this.login = login[0];
		System.out.println("Welcome " + this.login);
	}

	/**
	 * 
	 */
	public void help() {
		System.out.println("++++HELP MENU++++");
		System.out.println("login [name] => enter your login");
		System.out
				.println("send [destination] [title] [content] => send a mail on the server");
		System.out.println("load => Get all the mails on the server");

	}

}
