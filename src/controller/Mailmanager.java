/**
 * 
 */
package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import entity.Mail;

/**
 * @author philippe
 * 
 */

public class Mailmanager {

	public boolean save(Mail mail) {

		FileWriter fw = null;
		BufferedWriter out = null;

		try {
			fw = new FileWriter("mail" + this.generateId() + ".txt");
			out = new BufferedWriter(fw);

			out.write(mail.convertInString());
			out.flush();

		} catch (Exception e) {
			System.out.println("Error writing to file!");
			e.printStackTrace();
		} finally {
			try {
				fw.close();
				out.close();
			} catch (IOException e) {
				System.out.println("Error closing streams!");
				e.printStackTrace();
			}
		}

		return false;
	}


	public Collection<Mail> findAll() {
		return null;
	}

	public Collection<Mail> findByAdress(String adress) {
		return null;
	}

	public int generateId() {
		// TODO: implement this function who generate an unique id
		return 0;
	}

}
