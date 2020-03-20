import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditorGUI extends JPanel {

	public EditorGUI() {
		// create token1
		String token1 = "";

		// for-each loop for calculating heat index of May - October

		// create Scanner inFile1
		Scanner inFile1 = null;
		try {
			inFile1 = new Scanner(new File("accounts.txt")).useDelimiter(",\\s*");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Original answer used LinkedList, but probably preferable to use ArrayList in
		// most cases
		// List<String> = new LinkedList<String>();
		List<String> temps = new ArrayList<String>();

		// while loop
		while (inFile1.hasNext()) {
			// find next line
			token1 = inFile1.next();
			temps.add(token1);
		}
		inFile1.close();

		String[] Reviewers = temps.toArray(new String[0]);

		/*
		 * try { FileReader fr=new FileReader("accounts.txt"); //reads the file
		 * BufferedReader br=new BufferedReader(fr); //creates a buffering character
		 * input stream StringBuffer sb=new StringBuffer(); //constructs a string buffer
		 * with no characters String line; while((line=br.readLine())!=null) {
		 * sb.append(line); //appends line to string buffer sb.append("\n"); //line feed
		 * } fr.close(); //closes the stream and release the resources
		 * System.out.println("Contents of File: "); System.out.println(sb.toString());
		 * //returns a string that textually represents the object } catch(IOException
		 * e) { e.printStackTrace(); } }
		 */

		JLabel SelectReviewer = new JLabel("Select Reviewer");
		add(SelectReviewer);
		JComboBox Reviewerlist = new JComboBox(Reviewers);
		Reviewerlist.setEditable(true);
		Reviewerlist.setMaximumRowCount(100);
		add(Reviewerlist);

		JList list = new JList();
		add(list);

		JButton btnNewButton = new JButton("Assign");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Reviewer Assigned");

			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel lblNewLabel = new JLabel("Select Paper");
		add(lblNewLabel);

		String[] Submissions = {};
		JComboBox comboBox = new JComboBox();
		add(comboBox);
		add(btnNewButton);

	}

}
