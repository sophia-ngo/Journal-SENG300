import java.io.Serializable;

public class AccountType implements Serializable{
	public String[] accTypes = {"Author", "Reviewer", "Editor"};
	private String accType;
	
	public String getAccType() {
		return accType;
	}
	
	public void setAccType(int accType) {
		this.accType = accTypes[accType];
	}
	
	
	
	
}
