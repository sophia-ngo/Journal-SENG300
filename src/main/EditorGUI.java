package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditorGUI extends JPanel {

	@SuppressWarnings("resource")
	public EditorGUI(JFrame frame, Account acc, Authenticator auth, Database db) {
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

		// Label for select reviewer
		JLabel lblSelectReviewer = new JLabel("Select Reviewer:");
		lblSelectReviewer.setBounds(503, 441, 154, 26);
		lblSelectReviewer.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblSelectReviewer);

		// List of nominated reviewers
		JComboBox<String> Reviewerlist = new JComboBox<String>();
		// Checks if submission exists
		try {
			int sizeList = db.dbGet("sub1").getNomReviewers().getSize();
			DefaultListModel<String> list = db.dbGet("sub1").getNomReviewers();
			// Adds reviewers into combobox
			for (int i = sizeList; i > 0; i--) {
				Reviewerlist.addItem(list.elementAt(i));
			}
		} catch(Exception e) {
		}
		Reviewerlist.setFont(new Font("Arial", Font.PLAIN, 16));
		Reviewerlist.setBackground(Color.WHITE);
		Reviewerlist.setBorder(null);
		Reviewerlist.setBounds(660, 442, 219, 26);
		Reviewerlist.setEditable(true);
		Reviewerlist.setMaximumRowCount(100);
		add(Reviewerlist);

		// Button for assigning everything
		JButton btnAssign = new JButton("Assign");
		btnAssign.setFont(new Font("Arial", Font.BOLD, 16));
		btnAssign.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnAssign.setBackground(new Color(245, 245, 245));
		btnAssign.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAssign.setBounds(660, 508, 154, 29);
		btnAssign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Reviewer Assigned");

			}
		});
		btnAssign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel lblSelectPaper = new JLabel("Select Paper:");
		lblSelectPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSelectPaper.setBounds(503, 371, 122, 26);
		add(lblSelectPaper);

		try {
			db.dbLoad();
		}catch(Exception e) {
			
		}
		String[] Submissions = db.getSubmissions();
		JComboBox comboBoxSelectPaper = new JComboBox(Submissions);
		comboBoxSelectPaper.setBackground(Color.WHITE);
		comboBoxSelectPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxSelectPaper.setBorder(null);
		comboBoxSelectPaper.setBounds(660, 371, 219, 26);
		add(comboBoxSelectPaper);
		add(btnAssign);

		JLabel lblAccountType = new JLabel("Editor Account");
		lblAccountType.setForeground(Color.WHITE);
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(503, 175, 149, 16);
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
		lblLogout.setForeground(Color.BLACK);
		lblLogout.setFont(new Font("19ial", Font.PLAIN, 16));
		lblLogout.setBounds(128, 635, 57, 35);
		add(lblLogout);

		JLabel lblUJournal = new JLabel("UJournal");
		lblUJournal.setForeground(Color.WHITE);
		lblUJournal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUJournal.setFont(new Font("Arial", Font.BOLD, 40));
		lblUJournal.setBounds(500, 0, 199, 122);
		add(lblUJournal);

		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 122);
		add(lblRedBlock);

		JLabel lblAccountType_1 = new JLabel("Type: Editor");
		lblAccountType_1.setForeground(Color.BLACK);
		lblAccountType_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType_1.setBounds(144, 496, 117, 41);
		add(lblAccountType_1);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Author.class.getResource("/uofclogosmall.png")));
		lblLogo.setBounds(128, 222, 236, 199);
		add(lblLogo);

		JLabel lblUser = new JLabel("Hello " + acc.getUsername() + "!");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Arial", Font.BOLD, 20));
		lblUser.setBounds(144, 433, 209, 41);
		add(lblUser);

		JLabel lblYellowBlock = new JLabel("");
		lblYellowBlock.setOpaque(true);
		lblYellowBlock.setBackground(new Color(255, 217, 17));
		lblYellowBlock.setBounds(128, 485, 236, 94);
		add(lblYellowBlock);

		JLabel lblRedHorz = new JLabel("");
		lblRedHorz.setOpaque(true);
		lblRedHorz.setBackground(new Color(231, 43, 46));
		lblRedHorz.setBounds(128, 421, 236, 67);
		add(lblRedHorz);

		JLabel lblYellowVert = new JLabel("");
		lblYellowVert.setOpaque(true);
		lblYellowVert.setBackground(new Color(255, 217, 17));
		lblYellowVert.setBounds(434, 222, 12, 441);
		add(lblYellowVert);

	}

}
