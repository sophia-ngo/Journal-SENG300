import java.io.Serializable;

public class Account implements Serializable{
	
	private String username;
	
	private String password;
	
	private AccountType accType;

	public static boolean checkUsername(String username) {
		if(username.length() <= 16) {
			return true;
		}
		return false; 
	}
	
	public static boolean checkPassword(String pass) {
		if(pass.length() >= 16) {
			return true;
		}
		return false; 
	}
	
	public Account(String user, String pass, AccountType accType) {
		this.username = user;
		this.password = pass;
		this.accType = accType;
	}

	public String getUsername() {
		return this.username;
	}

	protected boolean setUsername(String username) {
		if(Account.checkUsername(username)) {
			this.username = username;
			return true;
		}
		return false;
	}

	protected String getPassword() {
		return password;
	}

	protected boolean setPassword(String password) {
		if(Account.checkPassword(password)) {
			this.password = password;
			return true;
		}
		return false;
	}
	
	protected AccountType getAccountType()
	{
		return this.accType;
	}

}
