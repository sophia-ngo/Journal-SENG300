package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reviewer extends JPanel {

	private int count = 1;
	private int p = 0;
	private int b = 0;
	private ButtonGroup category = new ButtonGroup(); // button group for category

	/**
	 * Create the frame.
	 */
	// connects the class to the authenticator and the database
	public Reviewer(JFrame frame, Account acc, Authenticator auth, Database db) {

		// Sets background and layout
		setBackground(Color.WHITE);
		setLayout(null);

		// Fills combobox with papers
		String[] papers = db.getReviewerPapers(acc.getUsername());
		JComboBox comboBoxPapers = new JComboBox(papers);
		comboBoxPapers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxPapers.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxPapers.setBounds(619, 223, 268, 26);
		add(comboBoxPapers);

		JTextArea txtComments = new JTextArea("Write your comments here.");
		txtComments.setLineWrap(true);
		txtComments.setFont(new Font("Arial", Font.PLAIN, 14));
		txtComments.setBackground(new Color(245, 245, 245));
		System.out.println(txtComments);
		txtComments.setBounds(501, 366, 396, 160);
		setLayout(null);
		add(txtComments);

		// Label for success of submission
		JLabel lblSuccess = new JLabel("Successfully submitted!");
		lblSuccess.setForeground(new Color(51, 204, 0));
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccess.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSuccess.setBounds(705, 598, 182, 26);
		add(lblSuccess);
		lblSuccess.setVisible(false);

		// Label for error of no paper selected
		JLabel lblNoPaper = new JLabel("No paper selected.");
		lblNoPaper.setForeground(new Color(255, 51, 51));
		lblNoPaper.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNoPaper.setBounds(705, 598, 182, 26);
		add(lblNoPaper);
		lblNoPaper.setVisible(false);

		// Label for error of no category selected
		JLabel lblNoCategory = new JLabel("No category selected.");
		lblNoCategory.setForeground(new Color(255, 51, 51));
		lblNoCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoCategory.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNoCategory.setBounds(705, 598, 182, 26);
		add(lblNoCategory);
		lblNoCategory.setVisible(false);

//		try {
//			FileWriter writer = new FileWriter("comment.txt", true);
//			BufferedWriter bWriter = new BufferedWriter(writer);
//			bWriter.write(message); // write string into text file
//			bWriter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		JLabel lblComments = new JLabel("Comments");
		lblComments.setFont(new Font("Arial", Font.BOLD, 20));
		lblComments.setBounds(501, 319, 534, 36);
		add(lblComments);

		// Informs of minor changes
		JRadioButton rdbtnMinor = new JRadioButton("Minor changes");
		rdbtnMinor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnMinor.setBackground(Color.WHITE);
		rdbtnMinor.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnMinor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (rdbtnMinor.isSelected()) {
					p = 0;
					System.out.println(p);
				}
			}

		});
		rdbtnMinor.setBounds(917, 427, 147, 29);
		category.add(rdbtnMinor);
		add(rdbtnMinor);

		// Informs of major changes
		JRadioButton rdbtnMajor = new JRadioButton("Major changes");
		rdbtnMajor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnMajor.setBackground(Color.WHITE);
		rdbtnMajor.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnMajor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (rdbtnMajor.isSelected()) {
					p = 1;
					System.out.println(p);
				}
			}

		});
		rdbtnMajor.setBounds(917, 395, 147, 29);
		category.add(rdbtnMajor);
		add(rdbtnMajor);

		// Informs of the paper being accepted
		JRadioButton rdbtnAccept = new JRadioButton("Accept");
		rdbtnAccept.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnAccept.setBackground(Color.WHITE);
		rdbtnAccept.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (rdbtnAccept.isSelected()) {
					b = 1;
					System.out.println(b);
				}
			}
		});
		rdbtnAccept.setBounds(917, 459, 147, 29);
		category.add(rdbtnAccept);
		add(rdbtnAccept);

		// Informs of the paper being rejected
		JRadioButton rdbtnReject = new JRadioButton("Reject");
		rdbtnReject.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnReject.setBackground(Color.WHITE);
		rdbtnReject.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnReject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnReject.isSelected()) {
					b = 0;
					System.out.println(b);
				}
			}

		});
		rdbtnReject.setBounds(917, 491, 147, 29);
		category.add(rdbtnReject);
		add(rdbtnReject);

		// Label for title
		JLabel lblUJournal = new JLabel("UJournal");
		lblUJournal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUJournal.setForeground(Color.WHITE);
		lblUJournal.setFont(new Font("Arial", Font.BOLD, 40));
		lblUJournal.setBounds(501, 0, 199, 122);
		add(lblUJournal);

		// Logo icon
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Author.class.getResource("/uofclogosmall.png")));
		lblLogo.setBounds(128, 222, 236, 199);
		add(lblLogo);

		// Label for greeting user
		JLabel lblUser = new JLabel("Hello " + acc.getUsername() + "!");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Arial", Font.BOLD, 20));
		lblUser.setBounds(144, 433, 209, 41);
		add(lblUser);

		// Label for account type
		JLabel lblAccountType = new JLabel("Type: Reviewer");
		lblAccountType.setForeground(Color.BLACK);
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(144, 496, 117, 41);
		add(lblAccountType);

		// Label for logout, acts as a button
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
		lblLogout.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLogout.setBounds(128, 635, 57, 35);
		add(lblLogout);

		// Decorative yellow long block
		JLabel lblYellowVert = new JLabel("");
		lblYellowVert.setOpaque(true);
		lblYellowVert.setBackground((new Color(255, 217, 17)));
		lblYellowVert.setBounds(434, 222, 12, 441);
		add(lblYellowVert);

		// Decorative red header block
		JLabel lblRedHorz = new JLabel("");
		lblRedHorz.setOpaque(true);
		lblRedHorz.setBackground(new Color(231, 43, 46));
		lblRedHorz.setBounds(0, 0, 1200, 122);
		add(lblRedHorz);

		// Decorative yellow block
		JLabel lblYellowBlock = new JLabel("");
		lblYellowBlock.setOpaque(true);
		lblYellowBlock.setBackground(new Color(255, 217, 17));
		lblYellowBlock.setBounds(128, 485, 236, 94);
		add(lblYellowBlock);

		// Decorative red block
		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(128, 421, 236, 67);
		add(lblRedBlock);

		// Button for download
		JButton btnDownload = new JButton("Download");
		btnDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.dbLoad();
				Submission s1 = db.dbGet((String) comboBoxPapers.getSelectedItem()); // get selected paper
				s1.download();
			}
		});
		btnDownload.setBackground(new Color(245, 245, 245));
		btnDownload.setFont(new Font("Arial", Font.BOLD, 16));
		btnDownload.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnDownload.setBounds(917, 222, 136, 28);
		add(btnDownload);

		JLabel lblSelectPaper = new JLabel("Select Paper:");
		lblSelectPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSelectPaper.setBounds(501, 223, 108, 26);
		add(lblSelectPaper);

		// Button for submitting all information
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setBackground(new Color(245, 245, 245));
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));
		btnSubmit.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Reset success/error messages
				lblSuccess.setVisible(false);
				lblNoPaper.setVisible(false);
				lblNoCategory.setVisible(false);

				String comboBoxItem = (String) comboBoxPapers.getSelectedItem(); // get item from combobox
				Submission paper = db.dbGet(comboBoxItem); // get current paper

				// Checks if paper and button is selected			
				if (!comboBoxItem.equals("null") && (rdbtnMajor.isSelected() || rdbtnMinor.isSelected()
						|| rdbtnAccept.isSelected() || rdbtnReject.isSelected())) {
					if (p > 0) {
						paper.setNotification("Major change");
					} else {
						paper.setNotification("Minor change");
					}
					if (b > 0) {
						paper.setNotification("Accept");
					} else {
						paper.setNotification("Reject");
					}

					String textBox = txtComments.getText();
					paper.setComments(textBox); // sets comments for paper
					lblSuccess.setVisible(true);
				}

				// if no paper selected, show error message
				if (comboBoxItem.equals("null")) { 
					lblNoPaper.setVisible(true);

				}

				// if no category selected, show error message
				if (!(rdbtnMajor.isSelected() || rdbtnMinor.isSelected() || rdbtnAccept.isSelected()
						|| rdbtnReject.isSelected())) { 
					lblNoCategory.setVisible(true);
				}

//						Response = change + " " + decision;
//						try {
//							FileWriter writer = new FileWriter("changes.txt", false);
//							BufferedWriter bWriter = new BufferedWriter(writer);
//							bWriter.write(Response); // write string into text file
//							bWriter.close();
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
			}
		});
		btnSubmit.setBounds(654, 635, 276, 28);
		add(btnSubmit);

		JLabel lblNewLabel = new JLabel("Select category:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(917, 366, 147, 22);
		add(lblNewLabel);

	}
}