package main;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDayChooser;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthorGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField filenameTEXT;
	String filename = null;

	/**
	 * Create the panel.
	 */
	public AuthorGUI(JFrame frame, Account acc, Authenticator auth, Database db) {
		setBackground(Color.WHITE);
		setLayout(null);

		filenameTEXT = new JTextField();
		filenameTEXT.setBackground(new Color(245, 245, 245));
		filenameTEXT.setFont(new Font("Arial", Font.PLAIN, 12));
		filenameTEXT.setBounds(460, 377, 267, 26);
		add(filenameTEXT);
		filenameTEXT.setColumns(10);

		JButton btnChooseFile = new JButton("...");
		btnChooseFile.setBackground(new Color(245, 245, 245));
		btnChooseFile.setFont(new Font("Arial", Font.BOLD, 16));
		btnChooseFile.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnChooseFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChooseFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter docFilter = new FileNameExtensionFilter("PDF Files", "pdf");
				chooser.setFileFilter(docFilter);
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				filename = f.getAbsolutePath();
				filenameTEXT.setText(filename);
			}
		});
		btnChooseFile.setBounds(737, 374, 41, 29);
		add(btnChooseFile);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(245, 245, 245));
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));
		btnSubmit.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				db.dbLoad();
				Submission s1 = new Submission();
				s1.submit(filenameTEXT.getText());
				db.dbAdd("sub1", s1);
				db.dbSave();
			}
		});
		btnSubmit.setBounds(462, 627, 276, 28);
		add(btnSubmit);

		JButton btnDownload = new JButton("Download");
		btnDownload.setBackground(new Color(245, 245, 245));
		btnDownload.setFont(new Font("Arial", Font.BOLD, 16));
		btnDownload.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				db.dbLoad();
				Submission s1 = db.dbGet("sub1");
				s1.download();
			}
		});
		btnDownload.setBounds(561, 270, 161, 28);
		add(btnDownload);

		JLabel lblAccountType = new JLabel("Author Account");
		lblAccountType.setForeground(Color.WHITE);
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(10, 11, 117, 16);
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

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBackground(new Color(245, 245, 245));
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 16));
		dateChooser.setBounds(497, 436, 161, 26);
		add(dateChooser);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(AuthorGUI.class.getResource("/images/uofclogosmall.png")));
		lblLogo.setBounds(457, 167, 94, 110);
		add(lblLogo);

		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 122);
		add(lblRedBlock);

		JLabel lblUser = new JLabel("Hello " + acc.getUsername() + "!");
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUser.setBounds(561, 179, 131, 26);
		add(lblUser);

		JLabel lblStatus = new JLabel("Status: Accepted!");
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 16));
		lblStatus.setBounds(561, 206, 137, 26);
		add(lblStatus);

		JLabel lblPaper = new JLabel("Paper submitted: None");
		lblPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPaper.setBounds(561, 233, 171, 26);
		add(lblPaper);

		JLabel lblPublication = new JLabel("Publication deadline:");
		lblPublication.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPublication.setBounds(332, 436, 155, 26);
		add(lblPublication);

		JLabel lblSubmitAPaper = new JLabel("Submit a paper:");
		lblSubmitAPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSubmitAPaper.setBounds(331, 374, 131, 26);
		add(lblSubmitAPaper);

		JLabel lblNominate = new JLabel("Nominate reviewers:");
		lblNominate.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNominate.setBounds(331, 499, 155, 26);
		add(lblNominate);

		JComboBox<String> reviewerComboBox = new JComboBox<String>();
		// read accounts.txt
		try {
			File file = new File("accounts.txt");
			file.createNewFile();
			FileReader reader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(reader);

			String line;
			String userAcc[];
			// Reads next line in text file until end
			while ((line = bReader.readLine()) != null) {
				userAcc = line.split(" "); // split current line by space
				if (userAcc[2].equals("1")) {
					reviewerComboBox.addItem(userAcc[0]); // add to combox if reviewer
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		reviewerComboBox.setBackground(new Color(245, 245, 245));
		reviewerComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		reviewerComboBox.setBounds(496, 499, 146, 26);
		add(reviewerComboBox);

		JButton btnAdd = new JButton(">>");
		DefaultListModel<String> listModel = new DefaultListModel<String>(); // list model to add to Jlist
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = (String) reviewerComboBox.getSelectedItem(); // gets current selected item
				if (!listModel.contains(selected)) {
					listModel.addElement(selected);
				}

			}
		});
		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		btnAdd.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnAdd.setBackground(new Color(245, 245, 245));
		btnAdd.setBounds(652, 498, 69, 29);
		add(btnAdd);
		
		JList reviewerList = new JList(listModel);
		reviewerList.setBorder(new LineBorder(Color.LIGHT_GRAY));
		reviewerList.setBounds(733, 499, 131, 67);
		reviewerList.setFont(new Font("Arial", Font.PLAIN, 16));
		add(reviewerList);

		JButton btnRemove = new JButton("<<");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = (String) reviewerList.getSelectedValue();
				listModel.removeElement(selected);
			}
		});
		btnRemove.setFont(new Font("Arial", Font.BOLD, 16));
		btnRemove.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnRemove.setBackground(new Color(245, 245, 245));
		btnRemove.setBounds(652, 536, 69, 29);
		add(btnRemove);

	}
}
