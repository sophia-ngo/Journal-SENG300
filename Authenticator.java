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

			String line;
			String userAcc[];
			AccountType accType = new AccountType();
			// Reads next line in text file until end
			while ((line = bReader.readLine()) != null) {
				userAcc = line.split(" "); 		// split current line by space
				accType.setAccType(Integer.parseInt(userAcc[2])); 	// sets account type
				// adds username, password, account type to hashmap
				accounts.put(userAcc[0], new Account(userAcc[0], userAcc[1], accType));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String register(String username, String password, AccountType accType) {
		boolean correctuser = Account.checkUsername(username);
		boolean correctpass = Account.checkPassword(password);
		boolean notsameuser = !accounts.containsKey(username);
		
		if (correctuser && correctpass && notsameuser) {
			String userAcc = username + " " + accType.getAccNum() + " " + "\n";
			try {
				File file = new File("usernames.txt"); 
				FileWriter writer = new FileWriter("usernames.txt", true);
				BufferedWriter bWriter = new BufferedWriter(writer);
				bWriter.write(userAcc);		// write string into text file
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			accounts.put(username, new Account(username, password, accType));
			return "Works";
		}
		
		if (correctuser && correctpass && notsameuser) {
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