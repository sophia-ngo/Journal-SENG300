import java.io.IOException;

public class EditorChooseReviewer {
	public static String reviewers[] = { };
	
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

