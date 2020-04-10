package main;

import java.io.*;
import java.nio.file.Files;

import javax.swing.DefaultListModel;

/**
 * Holds information that comes along with a pdf submission.
 * 
 * @author Group 2
 */
public class Submission implements Serializable {

	private byte[] bytesFromFile;
	private Account authorAccount;
	private String paperTitle; // title of paper
	private DefaultListModel<String> nomReviewers; // nominated reviewers for a submission
	// user of submission
	private String notification; // feedback: major, critical, accept
	private String reviewerUser;
	private String comments;

	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getReviewerUser() {
		return reviewerUser;
	}
	
	/**
	 * @param user
	 */
	public void setReviewerUser(String user) {
		this.reviewerUser = user;
	}
	
	//This will get the papertitle and return it
	public String getPaperTitle() {
		return paperTitle;
	}

	//This will get the name of the nominated reviewer
	public String[] getNomReviewers() {
		Object[] x = this.nomReviewers.toArray();
		String[] list = new String[x.length];
		System.arraycopy(x, 0, list, 0, x.length);
		return list;
	}

	public String getNotification() {
		return notification;
	}
	
	public void setNotification(String notification) {
		this.notification = notification;
	}

	/**
	 * Submits the pdf by reading the path name.
	 * 
	 * @param path of the pdf file.
	 * @param acc  is current account of submission.
	 * @param list of nominated reviewers.
	 * @return boolean that shows if file was successfully submitted.
	 */
	public boolean submit(String path, Account acc, DefaultListModel list) {
		try {
			this.authorAccount = acc;
			File f = new File(path);
			this.bytesFromFile = Files.readAllBytes(f.toPath());
			System.out.println(bytesFromFile.length);
			String[] splitPath = path.split("/"); // splits path name by "/"
			paperTitle = splitPath[splitPath.length - 1]; // takes last index of split
			nomReviewers = list;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Downloads the selected pdf in current directory of where it was run.
	 * 
	 * @return boolean that shows success of download.
	 */
	public boolean download() {
		File destination = new File(authorAccount.getUsername() + ".pdf");
		try {
			Files.write(destination.toPath(), bytesFromFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
