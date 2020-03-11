import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Group 2
 *
 */
public class Authenticator {

	private HashMap<String, Account> accounts = new HashMap<String, Account>();

	/**
	 * 
	 */
	public Authenticator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Reads the accounts.txt file and adds to HashMap.
	 */
	public void checkAccounts() {
		try {
			File file = new File("accounts.txt");
			FileReader reader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(reader);
			StringBuffer stringBuff = new StringBuffer();
			String line;
			String userPass[];
			// Reads next line in text file until end
			while ((line = bReader.readLine()) != null) {
				userPass = line.split(" ");		// split current line by space
				// add username and password to hashmap
				
			}
			reader.close();
		} catch {IOException e} {
			e.printStackTrace();
		}
	}

	public String register(String username, String password, AccountType accType) {
		boolean correctuser = Account.checkUsername(username);
		boolean correctpass = Account.checkPassword(password);
		boolean notsameuser = !accounts.containsKey(username);
		if (correctuser && correctpass && notsameuser) {
			accounts.put(username, new Account(username, password, accType));
			return "Works";
		}
		if (!correctuser) {
			return "Invalid username";
		}
		if (!correctpass) {
			return "Invalid password";
		}
		if (!notsameuser) {
			return "Username taken";
		}
		return "Unknown issue";
	}

	public Account login(String username, String password) {
		if (accounts.containsKey(username)) {
			System.out.println("found users");
			if (accounts.get(username).getPassword().equals(password)) {
				return accounts.get(username);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}