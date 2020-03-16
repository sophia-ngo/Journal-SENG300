import java.io.*;
import java.util.HashMap;

public class Database implements Serializable{

	private HashMap<String, Submission> files = new HashMap<String, Submission>();
	private String fileName = "data.bin";
	
	public void dbAdd(String key, Submission s) {
		files.put(key, s);
	}
	
	public Submission dbGet(String key) {
		return files.get(key);
	}
	
	public void dbSave() {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(this.files);
			os.close();
			System.out.println("Done Writing");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dbLoad() {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			this.files = (HashMap<String, Submission>) is.readObject();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
