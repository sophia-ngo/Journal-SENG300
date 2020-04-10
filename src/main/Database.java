package main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Group 2
 *
 */
public class Database implements Serializable {

	private HashMap<String, Submission> files = new HashMap<String, Submission>(); // author and submission
	private String fileName = "data.bin"; // saves data into file
	private static final long serialVersionUID = -1387287068956938420L;

	/**
	 * Adds a submission into files hashmap.
	 * 
	 * @param key
	 * @param s
	 */
	public void dbAdd(String key, Submission s) {
		files.put(key, s);
	}

	public Submission dbGet(String key) {
		return files.get(key);
	}

	/**
	 * Saves the database.
	 */
	public void dbSave() {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(this.files);
			os.close();
			System.out.println("Done Writing");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the database.
	 */
	@SuppressWarnings("unchecked")
	public void dbLoad() {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			this.files = (HashMap<String, Submission>) is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This gets the submission that was submitted
	 */
	public String[] getSubmissions() {
		ArrayList<String> subs = new ArrayList<String>();
		for (String name : files.keySet()) {
			Submission sub = files.get(name);
			subs.add(sub.getPaperTitle());
		}

		String[] subsf = new String[subs.size()];
		for (int j = 0; j < subs.size(); j++) {
			subsf[j] = subs.get(j);
		}
		return subsf;
	}

	/**
	 * Get all submissions.
	 * 
	 * @return
	 */
	public String[] getKeys() {
		ArrayList<String> subs = new ArrayList<String>();
		for (String name : files.keySet()) {
			String key = name.toString();
			subs.add(key);
		}

		String[] subsf = new String[subs.size()];
		for (int j = 0; j < subs.size(); j++) {
			subsf[j] = subs.get(j);
		}
		return subsf;

	}

	/**
	 * @param currentReviewer is the one that is currently logged in.
	 * @return
	 */
	public String[] getReviewerPapers(String currentReviewer) {
		ArrayList<String> tempPapers = new ArrayList<String>();
		String paperTitle;
		String reviewer;
		for (String key : files.keySet()) {
			reviewer = files.get(key).getReviewerUser(); // gets reviewer of paper
			paperTitle = files.get(key).getPaperTitle(); // gets paper title
			if (reviewer.equals(currentReviewer)) {
				tempPapers.add(paperTitle); // add paper title to arraylist
			}
		}

		// Array to return papers
		String[] papers = new String[tempPapers.size()];
		for (int i = 0; i < tempPapers.size(); i++) {
			papers[i] = tempPapers.get(i);
		}
		return papers;
	}

	public Submission getSubmission(String key) {
		return files.get(key);
	}

}
