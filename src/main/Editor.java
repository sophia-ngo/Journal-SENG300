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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Editor extends JPanel implements Serializable {

	@SuppressWarnings("resource")
	public Editor(JFrame frame, Account acc, Authenticator auth, Database db) {
		db.dbLoad();

		setBackground(Color.WHITE);
		String[] token1; // create token1

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
		lblSelectReviewer.setBounds(503, 439, 154, 26);
		lblSelectReviewer.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblSelectReviewer);

		// Combobox to select an author/submission
		db.dbLoad();
		String[] authors = db.getKeys(); // array of submissions
		JComboBox comboBoxSelectAuthor = new JComboBox(authors);
		comboBoxSelectAuthor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxSelectAuthor.setBackground(new Color(245, 245, 245));
		comboBoxSelectAuthor.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxSelectAuthor.setBounds(660, 305, 218, 27);
		add(comboBoxSelectAuthor);

		// Combobox to select a reviewer
		JComboBox<String> comboBoxSelectReviewer = new JComboBox<String>();
		comboBoxSelectReviewer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxSelectReviewer.setBackground(new Color(245, 245, 245));
		comboBoxSelectReviewer.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxSelectReviewer.setBounds(660, 441, 218, 27);
		add(comboBoxSelectReviewer);

		JLabel lblRa = new JLabel("Reviewer assigned!");
		lblRa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRa.setForeground(new Color(51, 204, 0));
		lblRa.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRa.setBounds(660, 545, 196, 28);
		lblRa.setVisible(false);
		add(lblRa);
		
		// Button for assigning everything
		JButton btnAssign = new JButton("Assign");
		btnAssign.setFont(new Font("Arial", Font.BOLD, 16));
		btnAssign.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnAssign.setBackground(new Color(245, 245, 245));
		btnAssign.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAssign.setBounds(664, 584, 192, 29);
		btnAssign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String x = comboBoxSelectAuthor.getSelectedItem().toString(); // get author selected
				String name = comboBoxSelectReviewer.getSelectedItem().toString(); // get reviewer selected
				db.getSubmission(x).setReviewerUser(name); // sets the reviewer depending on submission
				System.out.println("Reviewer: " + name + " Assigned");
				lblRa.setVisible(true);
				db.printDB();
				db.dbSave();
			}
		});
		btnAssign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		try {
			db.dbLoad();
		} catch (Exception e) {

		}
		add(btnAssign);

		// Logout button
		JLabel lblLogout = new JLabel("<HTML><U>Logout</U></HTML>");
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login panel = new Login(frame, auth, db);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		lblLogout.setForeground(Color.BLACK);
		lblLogout.setFont(new Font("19ial", Font.PLAIN, 16));
		lblLogout.setBounds(128, 635, 57, 35);
		add(lblLogout);

		// Label for UJournal title
		JLabel lblUJournal = new JLabel("UJournal");
		lblUJournal.setForeground(Color.WHITE);
		lblUJournal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUJournal.setFont(new Font("Arial", Font.BOLD, 40));
		lblUJournal.setBounds(500, 0, 199, 122);
		add(lblUJournal);

		// Decorative red block
		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 122);
		add(lblRedBlock);

		// Label for account type
		JLabel lblAccountType = new JLabel("Type: Editor");
		lblAccountType.setForeground(Color.BLACK);
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(144, 496, 117, 41);
		add(lblAccountType);

		// UOfC logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Author.class.getResource("/uofclogosmall.png")));
		lblLogo.setBounds(128, 222, 236, 199);
		add(lblLogo);

		// Label for current username
		JLabel lblUser = new JLabel("Hello " + acc.getUsername() + "!");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Arial", Font.BOLD, 20));
		lblUser.setBounds(144, 433, 209, 41);
		add(lblUser);

		// Decorative yellow block
		JLabel lblYellowBlock = new JLabel("");
		lblYellowBlock.setOpaque(true);
		lblYellowBlock.setBackground(new Color(255, 217, 17));
		lblYellowBlock.setBounds(128, 485, 236, 94);
		add(lblYellowBlock);

		// Decorative red header block
		JLabel lblRedHorz = new JLabel("");
		lblRedHorz.setOpaque(true);
		lblRedHorz.setBackground(new Color(231, 43, 46));
		lblRedHorz.setBounds(128, 421, 236, 67);
		add(lblRedHorz);

		// Decorative yellow block
		JLabel lblYellowVert = new JLabel("");
		lblYellowVert.setOpaque(true);
		lblYellowVert.setBackground(new Color(255, 217, 17));
		lblYellowVert.setBounds(434, 222, 12, 441);
		add(lblYellowVert);

		// Label for select author
		JLabel lblSelectAuthor = new JLabel("Author:");
		lblSelectAuthor.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSelectAuthor.setBounds(503, 306, 122, 24);
		add(lblSelectAuthor);

		// Label for select paper
		JLabel lblSelectPaper = new JLabel("Paper Selected:");
		lblSelectPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSelectPaper.setBounds(503, 369, 375, 26);
		add(lblSelectPaper);

		// Button for selecting author
		JButton btnSelect = new JButton("Select");
		btnSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelect.setFont(new Font("Arial", Font.BOLD, 16));
		btnSelect.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnSelect.setBackground(new Color(245, 245, 245));
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblRa.setVisible(false);
				String x = comboBoxSelectAuthor.getSelectedItem().toString(); // gets the selected author
				String paper = db.getSubmission(x).getPaperTitle(); // gets paper title
				lblSelectPaper.setText("Paper Selected: " + paper);
				String[] list = db.getSubmission(x).getNomReviewers(); // gets list of nominated reviewers
				comboBoxSelectReviewer.removeAllItems();
				// Adds reviewers to combobox to select reviewer
				for (int i = 0; i < list.length; i++) {
					comboBoxSelectReviewer.addItem(list[i]);
				}
				frame.revalidate();
			}
		});
		btnSelect.setBounds(900, 303, 108, 29);
		add(btnSelect);
		
		

	}
}
