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
import java.util.List;

import entity.Mail;

/**
 * @author philippe
 * 
 */

public class MailManager {

	String mailDirectory = "mails";
	String configDirectory = "config";

	public MailManager() {

		File file = new File(this.mailDirectory);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Creation of mails directory");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		
		File config = new File(this.configDirectory);
		if (!config.exists()) {
			if (config.mkdir()) {
				System.out.println("Creation of config directory");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
	}

	/**
	 * Save a mail in a file
	 * 
	 * @param mail
	 * @return
	 */
	public boolean save(Mail mail) {

		int id = 0;

		// Creation of unique id if needed
		if (!mail.getId().equals("null")) {
			id = Integer.parseInt(mail.getId());
		} else {
			id = this.generateId();
			mail.setId(Integer.toString(id));
		}

		FileWriter fw = null;
		BufferedWriter out = null;

		try {
			fw = new FileWriter(this.mailDirectory
					+ System.getProperty("file.separator") + id + ".mail");
			out = new BufferedWriter(fw);

			out.write(mail.toString());
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

		File dir = new File(this.mailDirectory);
		File[] mails = dir.listFiles();
		// No files in directory.
		if (mails == null) {
			return null;
		}

		// Loop over all files in the directory.
		for (File file : mails) {
			res.add(this.openMail(file));
		}

		return res;
	}

	/**
	 * Find all mails in the repository with the specific "destinatair".
	 * 
	 * @param adress
	 * @return
	 */
	public List<Mail> findByAdress(String adress) {
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
	 * Generate an unique id for the mail in the local directory
	 * 
	 * @return
	 */
	public int generateId() {
		int res = 0;

		File dir = new File(this.configDirectory);
		File[] configs = dir.listFiles();
		if (configs == null) {
			return 0;
		}

		for (File file : configs) {

			if (file.getName().equals("config.server")) {

				FileInputStream fis = null;
				BufferedInputStream bis = null;
				BufferedReader reader = null;

				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					reader = new BufferedReader(new InputStreamReader(bis));
					String entry;

					while ((entry = reader.readLine()) != null) {
						res = Integer.parseInt(entry);
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

			}
		}

		FileWriter fw = null;
		BufferedWriter out = null;

		try {
			fw = new FileWriter(this.configDirectory
					+ System.getProperty("file.separator") + "config.server");
			out = new BufferedWriter(fw);
			
			int temp = res + 1;

			out.write(Integer.toString(temp));
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

		return res + 1;
	}

	/**
	 * @param mail
	 */
	public void remove(Mail mail) {
		File dir = new File(this.mailDirectory);
		File[] mails = dir.listFiles();
		if (mails == null) {
			return;
		}

		for (File file : mails) {
			if (file.getName().equals(mail.getId() + ".mail")) {
				file.delete();
				return;
			}
		}
	}

}
