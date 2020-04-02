package main;

import java.io.*;
import java.nio.file.Files;


public class Submission implements Serializable{
	
	private byte[] bytesFromFile;
	private String authorUser;
	private String reviewerUser;
	private AccountType acc = new AccountType();
	private Account authorAccount;
	// user of submission
	// nominated reviewer for submission, updated by editor
	// feedback: major, critical, accept
	
	/*
	public static byte[] getBytes(File f) throws FileNotFoundException, IOException{
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(f);
		int read;
		while((read = fis.read(buffer)) != -1) {
			os.write(buffer, 0, read);
		}
		fis.close();
		os.close();
		return os.toByteArray();
	}*/
	
	public boolean submit(String path, Account acc){
		try {
			this.authorAccount = acc;
			File f = new File(path);
			this.bytesFromFile = Files.readAllBytes(f.toPath());
			System.out.println(bytesFromFile.length);
			this.authorUser = acc.getUsername();
			this.acc = acc.getAccountType();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean download() {
		File destination = new File(authorAccount.getUsername() + ".pdf");
		try {
			Files.write(destination.toPath(), bytesFromFile);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
