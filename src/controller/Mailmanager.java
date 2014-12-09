/**
 * 
 */
package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entity.Mail;

/**
 * @author philippe
 * 
 */

public class Mailmanager {

	String fileName = "mails";

	/**
	 * Save a mail in a file
	 * 
	 * @param mail
	 * @return
	 */
	public boolean save(Mail mail) {

		FileWriter fw = null;
		BufferedWriter out = null;

		try {
			fw = new FileWriter(this.fileName
					+ System.getProperty("file.separator")
					+ mail.getDestination() + this.generateId() + ".txt");
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

	/**
	 * Read the specified file and instance a new class Mail
	 * 
	 * @param file
	 * @return the mail
	 */
	public Mail openMail(File file) {
		Mail mail = new Mail();

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedReader reader = null;

		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			reader = new BufferedReader(new InputStreamReader(bis));
			String entry;

			while ((entry = reader.readLine()) != null) {
				mail.populat(entry);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				bis.close();
				reader.close();
			} catch (IOException e) {
				System.out.println("Error closing streams!");
				e.printStackTrace();
			}
		}
		return mail;

	}

	/**
	 * Get the list of all mails
	 * 
	 * @return
	 */
	public List<Mail> findAll() {

		List<Mail> res = new ArrayList<Mail>();

		File dir = new File(this.fileName);
		File[] mails = dir.listFiles();
		// No files in directory.
		if (mails == null) {
			return null;
		}
		// Initialise streams
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedReader reader = null;

		// Loop over all files in the directory.
		for (File file : mails) {
			this.openMail(file);

		}

		return res;
	}

	/**
	 * Find all mails in the repository with the specific "destinatair".
	 * 
	 * @param adress
	 * @return
	 */
	public Collection<Mail> findByAdress(String adress) {
		List<Mail> res = new ArrayList<Mail>();

		List<Mail> mails = this.findAll();

		for (Mail mail : mails) {
			if (mail.getDestination().equals(adress)) {
				res.add(mail);
			}

		}
		return res;
	}

	/**
	 * Create
	 * 
	 * @return
	 */
	public int generateId() {
		// TODO: implement this function who generate an unique id
		return 0;
	}

}
