package main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author sopi
 *
 */
public class Database implements Serializable {

	private HashMap<String, Submission> files = new HashMap<String, Submission>(); // author and submission
	private String fileName = "data.bin"; // saves data into file

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

	public String[] getSubmissions() {
		ArrayList<String> subs = new ArrayList<String>();
		for (String name : files.keySet()) {
			String key = name.toString();
			Submission sub = files.get(name);
			subs.add(sub.getPaperTitle());
		}

		String[] subsf = new String[subs.size()];
		for (int j = 0; j < subs.size(); j++) {
			subsf[j] = subs.get(j);
		}
		return subsf;
	}

}
