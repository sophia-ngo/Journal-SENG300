import java.util.HashMap;

/**
 * @author colinauyeung
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
	
	public String register(String username, String password, AccountType accType) {
		boolean correctuser = Account.checkUsername(username);
		boolean correctpass = Account.checkPassword(password);
		boolean notsameuser = !accounts.containsKey(username);
		if(correctuser && correctpass && notsameuser) {
			accounts.put(username, new Account(username, password, accType));
			return "Works";
		}
		if(!correctuser) {
			return "Invalid username";
		}
		if(!correctpass) {
			return "Invalid password";
		}
		if(!notsameuser) {
			return "Username taken";
		}
		return "Unknown issue"; 
	}
	
	public Account login(String username, String password) {
		if(accounts.containsKey(username)) {
			System.out.println("found users");
			if(accounts.get(username).getPassword().equals(password)) {
				return accounts.get(username);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

}