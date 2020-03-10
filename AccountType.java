
/**
 * Stores different account types.
 * 
 * @author Group 2
 */
public class AccountType {
	public String[] accTypes = { "Author", "Reviewer", "Editor" };
	private String accType;

	public String getAccType() {
		return accType;
	}

	public void setAccType(int accType) {
		this.accType = accTypes[accType];
	}

}
