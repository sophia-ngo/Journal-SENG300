import java.util.HashMap;

public class Database {

	private HashMap<String, Submission> files = new HashMap<String, Submission>();
	
	public void dbAdd(String key, Submission s) {
		files.put(key, s);
	}
	
	public Submission dbGet(String key) {
		return files.get(key);
	}
	
}
