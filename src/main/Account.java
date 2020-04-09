package main;

import java.io.Serializable;

/**
 * Account storage of username, password, and account type.
 * 
 * @author Group 2
 */
public class Account implements Serializable {

	private String username;
	private String password;
	private AccountType accType;

	//This checks for the username to be atleast 4 digits long and at mostb 12 digits
	public static boolean checkUsername(String username) {
		if (username.length() >= 4 && username.length() <= 12) {
			return true;
		}
		return false;
	}

	//This checks the password to be atleast 8 digits
	public static boolean checkPassword(String pass) {
		if (pass.length() >= 8) {
			return true;
		}
		return false;
	}

	//This sets up the username and the password and account types that were entered 
	public Account(String user, String pass, AccountType accType) {
		this.username = user;
		this.password = pass;
		this.accType = accType;
	}

	public String getUsername() {
		return this.username;
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
