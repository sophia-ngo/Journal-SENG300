package main;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
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
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Author extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField filenameTEXT;
	String filename = null;
	private int count = 1;

	/**
	 * Create the panel.
	 */
	public Author(JFrame frame, Account acc, Authenticator auth, Database db) {
		setBackground(Color.WHITE);
		setLayout(null);

		filenameTEXT = new JTextField();
		filenameTEXT.setBackground(new Color(245, 245, 245));
		filenameTEXT.setFont(new Font("Arial", Font.PLAIN, 12));
		filenameTEXT.setBounds(634, 381, 267, 26);
		add(filenameTEXT);
		filenameTEXT.setColumns(10);
		
		JLabel lblSuccess = new JLabel("Successfully submitted!");
		lblSuccess.setForeground(new Color(51, 204, 0));
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccess.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSuccess.setBounds(646, 598, 182, 26);
		add(lblSuccess);
		lblSuccess.setVisible(false);
		
		// Sets paper name if there is one submitted
		JLabel lblPaper = new JLabel("Paper submitted: ");
		try {
			lblPaper.setText("Paper submitted: " + db.dbGet("sub1").getPaperTitle());
		} catch(Exception e) {
			lblPaper.setText("Paper submitted: None");
		}
		lblPaper.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPaper.setBounds(468, 222, 534, 26);
		add(lblPaper);

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
		btnChooseFile.setBounds(911, 378, 41, 29);
		add(btnChooseFile);

		JButton btnDownload = new JButton("Download");
		btnDownload.setBackground(new Color(245, 245, 245));
		btnDownload.setFont(new Font("Arial", Font.BOLD, 16));
		btnDownload.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				db.dbLoad();
				int c_1 = count - 1;
				Submission s1 = db.dbGet(acc.getUsername()+c_1);
				s1.download();
			}
		});
		btnDownload.setBounds(468, 259, 161, 28);
		add(btnDownload);

		JLabel lblAccountType = new JLabel("Type: Author");
		lblAccountType.setForeground(Color.BLACK);
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(144, 496, 117, 41);
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
		lblUJournal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUJournal.setForeground(Color.WHITE);
		lblUJournal.setFont(new Font("Arial", Font.BOLD, 40));
		lblUJournal.setBounds(501, 0, 199, 122);
		add(lblUJournal);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBackground(new Color(245, 245, 245));
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 16));
		dateChooser.setBounds(633, 433, 161, 26);
		add(dateChooser);

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

		JLabel lblStatus = new JLabel("Status: ");
		
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 16));
		lblStatus.setBounds(144, 530, 137, 41);
		add(lblStatus);

		JLabel lblPublication = new JLabel("Publication Deadline:");
		lblPublication.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPublication.setBounds(468, 433, 155, 26);
		add(lblPublication);

		JLabel lblSubmitAPaper = new JLabel("Submit a Paper");
		lblSubmitAPaper.setFont(new Font("Arial", Font.BOLD, 20));
		lblSubmitAPaper.setBounds(468, 322, 534, 26);
		add(lblSubmitAPaper);

		JLabel lblNominate = new JLabel("Nominate Reviewers:");
		lblNominate.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNominate.setBounds(468, 493, 155, 26);
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
		reviewerComboBox.setBounds(634, 493, 146, 26);
		add(reviewerComboBox);

		JButton btnAdd = new JButton(">>");
		DefaultListModel<String> listModel = new DefaultListModel<String>(); // list model to add to Jlist
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selected = (String) reviewerComboBox.getSelectedItem(); // gets current selected item
				if (!listModel.contains(selected) && listModel.getSize() < 3) {
					listModel.addElement(selected);
				}

			}
		});
		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		btnAdd.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnAdd.setBackground(new Color(245, 245, 245));
		btnAdd.setBounds(790, 492, 69, 29);
		add(btnAdd);
		
		JList reviewerList = new JList(listModel);
		reviewerList.setVisibleRowCount(3);
		reviewerList.setBackground(new Color(245, 245, 245));
		reviewerList.setBorder(new LineBorder(Color.LIGHT_GRAY));
		reviewerList.setBounds(871, 493, 131, 67);
		reviewerList.setFont(new Font("Arial", Font.PLAIN, 16));
		add(reviewerList);

		JButton btnRemove = new JButton("<<");
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) reviewerList.getSelectedValue();
				listModel.removeElement(selected);
			}
		});
		btnRemove.setFont(new Font("Arial", Font.BOLD, 16));
		btnRemove.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnRemove.setBackground(new Color(245, 245, 245));
		btnRemove.setBounds(790, 530, 69, 29);
		add(btnRemove);
		
		JLabel lblYellowBlock = new JLabel("");
		lblYellowBlock.setOpaque(true);
		lblYellowBlock.setBackground(new Color(255, 217, 17));
		lblYellowBlock.setBounds(128, 485, 236, 94);
		add(lblYellowBlock);
		
		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(128, 421, 236, 67);
		add(lblRedBlock);
		
		JLabel lblYellowVert = new JLabel("");
		lblYellowVert.setOpaque(true);
		lblYellowVert.setBackground((new Color(255, 217, 17)));
		lblYellowVert.setBounds(434, 222, 12, 441);
		add(lblYellowVert);
		
		JLabel lblFile = new JLabel("Upload:");
		lblFile.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFile.setBounds(468, 381, 155, 26);
		add(lblFile);
		
		JLabel lblRedHorz = new JLabel("");
		lblRedHorz.setOpaque(true);
		lblRedHorz.setBackground(new Color(231, 43, 46));
		lblRedHorz.setBounds(0, 0, 1200, 122);
		add(lblRedHorz);
		
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
				s1.submit(filenameTEXT.getText(), acc, listModel);
				db.dbAdd(acc.getUsername()+count, s1);
				count++;
				db.dbSave();
				lblSuccess.setVisible(true);
				lblPaper.setText("Paper submitted: " + s1.getPaperTitle());
			}
		});
		btnSubmit.setBounds(599, 635, 276, 28);
		add(btnSubmit);

	}
}
