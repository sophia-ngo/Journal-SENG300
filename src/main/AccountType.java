package main;

import java.io.Serializable;

/**
 * 
 * 
 * @author Group 2
 */
public class AccountType implements Serializable{
  
	public String[] accTypes = { "Author", "Reviewer", "Editor" };
	private String accType;
	private int accNum;
	
	public String getAccType() {
		return accType;
	}
	
	/**
	 * Gets account number where
	 * 0 = Author
	 * 1 = Reviewer
	 * 2 = Editor
	 * 
	 * @return
	 */
	public int getAccNum() {
		if (accType.equals("Author")) {
			return 0;
		} else if (accType.equals("Reviewer")) {
			return 1;
		} else if (accType.equals("Editor")) {
			return 2;
		}
		return 3;
	}

	public void setAccType(int accType) {
		this.accType = accTypes[accType];
	}

}
