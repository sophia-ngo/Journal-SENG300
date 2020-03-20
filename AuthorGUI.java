import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class AuthorGUI extends JPanel {
	private JTextField filenameTEXT;
	String filename = null;

	/**
	 * Create the panel.
	 */
	public AuthorGUI(JFrame frame, Authenticator auth, Database db) {
		setBackground(Color.WHITE);
		setLayout(null);
		db.dbLoad();

		filenameTEXT = new JTextField();
		filenameTEXT.setFont(new Font("Arial", Font.PLAIN, 12));
		filenameTEXT.setBounds(352, 332, 267, 26);
		add(filenameTEXT);
		filenameTEXT.setColumns(10);

		JButton btnChooseFile = new JButton("Choose file...");
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
		btnChooseFile.setBounds(648, 329, 161, 29);
		add(btnChooseFile);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(245, 245, 245));
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));
		btnSubmit.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Submission s1 = new Submission();
				s1.submit(filenameTEXT.getText());
				db.dbAdd("sub1", s1);
				db.dbSave();
			}
		});
		btnSubmit.setBounds(648, 369, 161, 28);
		add(btnSubmit);

		JButton btnDownload = new JButton("Download");
		btnDownload.setBackground(new Color(245, 245, 245));
		btnDownload.setFont(new Font("Arial", Font.BOLD, 16));
		btnDownload.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Submission s1 = db.dbGet("sub1");
				s1.download();
			}
		});
		btnDownload.setBounds(648, 290, 161, 28);
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

		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 122);
		add(lblRedBlock);

	}
}