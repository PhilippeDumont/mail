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
	public void newMail(String[] values) {
		Mail mail = new Mail();
		mail.populat(values);

		try {
			out.writeUTF(Protocol.ADD + " " + mail.toString());
		} catch (IOException e) {
			System.out.println("Error when I send the mail");
			e.printStackTrace();
		}
	}

	/**
	 * download the mail located on the server.
	 */
	public void loadMail() {

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
					this.mailManager.save(mail);
				}
			}

		} catch (Exception e) {
			System.out.println("Error of connection with the server");
		}

	}

	/**
	 * Display a mail in the terminal
	 */
	public void ReadMail() {

	}

	public void login(String login) {
		this.login = login;
	}

	/**
	 * 
	 */
	public void help() {
		System.out.println("HEP MENU");
		System.out
				.println("new [source] [destination] [title] [content] => send a mail on the server");
		System.out.println("load => Get all the mail on the server");

	}

}
