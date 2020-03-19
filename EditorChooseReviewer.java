import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class EditorChooseReviewer {
	


		public static String reviewers[] = { };
		
	/*
	public static void main(String[] args)
	{
		ReadFile rf = new ReadFile();
		
		String filename = "accounts.txt";
		
		try
		{
			reviewers = rf.readLines(filename);
			

		}
		catch(IOException e)
		{
			
		}
	}
}
*/
		public static void main(String[] args) throws Exception {
			List<String> reviewers = Collections.emptyList(); 
			try 
			{ 
				reviewers = Files.readAllLines(Paths.get("accounts.txt"), StandardCharsets.UTF_8); 
			} catch 
				(IOException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
				} 
			System.out.println("Content of List:"); 
			System.out.println(reviewers);
		}
	}

