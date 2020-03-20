import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Group 2
 *
 */
public class Authenticator {

	protected HashMap<String, Account> accounts = new HashMap<String, Account>();
	private boolean success; // boolean for successful registration

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
			file.createNewFile();
			FileReader reader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(reader);

			String line;
			String userAcc[];
			// Reads next line in text file until end
			while ((line = bReader.readLine()) != null) {
				userAcc = line.split(" "); 		// split current line by space
				AccountType accType = new AccountType();
				accType.setAccType(Integer.parseInt(userAcc[2])); 	// sets account type
				// adds username, password, account type to hashmap
				accounts.put(userAcc[0], new Account(userAcc[0], userAcc[1], accType));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean checkSameUser(String username) {
		if (accounts.containsKey(username)) {
			return true;
		}
		return false;
	}
	
	public String register(String username, String password, AccountType accType, boolean samePass) {
		boolean correctuser = Account.checkUsername(username);
		boolean correctpass = Account.checkPassword(password);
		boolean notsameuser = !checkSameUser(username);

		if (correctuser && correctpass && notsameuser && samePass) {
			// concatenates username, password, account type to write into file
			String userAcc = username + " " + password + " " + accType.getAccNum() + "\n";
			try {
				FileWriter writer = new FileWriter("accounts.txt", true);
				BufferedWriter bWriter = new BufferedWriter(writer);
				bWriter.write(userAcc);		// write string into text file
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			accounts.put(username, new Account(username, password, accType));
			return "Works";
		}
		return "Unknown issue";
	}

	public Account login(String username, String password) {
		if (accounts.containsKey(username)) {
			if (accounts.get(username).getPassword().equals(password)) {
				return accounts.get(username);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(boolean succ) {
		success = succ;
	}

}