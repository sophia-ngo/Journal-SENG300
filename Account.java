/**
 * Account storage of username, password, and account type.
 * 
 * @author Group 2
 */
public class Account {

	private String username;
	private String password;
	private AccountType accType;
	
	public static boolean checkUsername(String username) {
		if (username.length() <= 16) {
			return true;
		}
		return false;
	}

	public static boolean checkPassword(String pass) {
		if (pass.length() >= 16) {
			return true;
		}
		return false;
	}

	public Account(String user, String pass, AccountType accType) {
		this.username = user;
		this.password = pass;
		this.accType = accType;
	}

	protected String getUsername() {
		return username;
	}

	/**
	 * Sets the username for given account.
	 * 
	 * @param username is inputed by the user via textbox.
	 * @return true or false if the username meets the requirements.
	 */
	protected boolean setUsername(String username) {
		if (Account.checkUsername(username)) {
			this.username = username;
			return true;
		}
		return false;
	}

	protected String getPassword() {
		return password;
	}

	/**
	 * Sets the password for given account.
	 * 
	 * @param password is inputed by the user via textbox.
	 * @return true or false if the password meets the requirements.
	 */
	protected boolean setPassword(String password) {
		if (Account.checkPassword(password)) {
			this.password = password;
			return true;
		}
		return false;
	}

	protected AccountType getAccountType() {
		return this.accType;
	}

}
