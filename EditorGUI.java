import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import java.awt.Font;

public class EditorGUI extends JPanel {

	@SuppressWarnings("resource")
	public EditorGUI(JFrame frame, Authenticator auth) {
		setBackground(Color.WHITE);
		// create token1
		String[] token1;

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
			token1 = inFile1.next().split(" ");
			if (token1[2].equals("1")) {
				temps.add(token1[0]);
			}
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
		setLayout(null);

		JLabel lblSelectReviewer = new JLabel("Select Reviewer");
		lblSelectReviewer.setBounds(399, 250, 154, 20);
		lblSelectReviewer.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblSelectReviewer);

		JComboBox Reviewerlist = new JComboBox(Reviewers);
		Reviewerlist.setFont(new Font("Arial", Font.PLAIN, 16));
		Reviewerlist.setBackground(Color.WHITE);
		Reviewerlist.setBorder(null);
		Reviewerlist.setBounds(628, 250, 154, 20);
		Reviewerlist.setEditable(true);
		Reviewerlist.setMaximumRowCount(100);
		add(Reviewerlist);

		JButton btnAssign = new JButton("Assign");
		btnAssign.setFont(new Font("Arial", Font.BOLD, 16));
		btnAssign.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnAssign.setBackground(new Color(245, 245, 245));
		btnAssign.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAssign.setBounds(531, 429, 154, 29);
		btnAssign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Reviewer Assigned");

			}
		});
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel lblSelectPaper = new JLabel("Select Paper");
		lblSelectPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSelectPaper.setBounds(399, 313, 122, 20);
		add(lblSelectPaper);

		String[] Submissions = {};
		JComboBox comboBoxSelectPaper = new JComboBox();
		comboBoxSelectPaper.setBackground(Color.WHITE);
		comboBoxSelectPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxSelectPaper.setBorder(null);
		comboBoxSelectPaper.setBounds(628, 313, 154, 20);
		add(comboBoxSelectPaper);
		add(btnAssign);

		JLabel lblAccountType = new JLabel("Editor Account");
		lblAccountType.setForeground(Color.WHITE);
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(10, 11, 149, 16);
		add(lblAccountType);

		JLabel lblLogout = new JLabel("<HTML><U>Logout</U></HTML>");
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login panel = new Login(frame, auth, null);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("19ial", Font.PLAIN, 16));
		lblLogout.setBounds(1119, 11, 57, 17);
		add(lblLogout);

		JLabel lblUJournal = new JLabel("UJournal");
		lblUJournal.setForeground(Color.WHITE);
		lblUJournal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUJournal.setFont(new Font("Arial", Font.BOLD, 40));
		lblUJournal.setBounds(500, 48, 199, 42);
		add(lblUJournal);

		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 122);
		add(lblRedBlock);

	}

}
