package main;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

/**
 * Main screen of the program after logging in.
 * 
 * @author Group 2
 */
public class MainScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainScreen(JFrame frame, Account acc, Authenticator auth, Database db) {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblAccountType = new JLabel(acc.getAccountType().getAccType() + " Account");
		lblAccountType.setForeground(Color.WHITE);
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(10, 11, 149, 16);
		add(lblAccountType);

		JLabel lblUJournal = new JLabel("UJournal");
		lblUJournal.setForeground(Color.WHITE);
		lblUJournal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUJournal.setFont(new Font("Arial", Font.BOLD, 40));
		lblUJournal.setBounds(500, 48, 199, 42);
		add(lblUJournal);

		JLabel lblName = new JLabel("Hello, " + acc.getUsername() + "!");
		lblName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(513, 148, 173, 19);
		add(lblName);

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
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLogout.setBounds(1119, 11, 57, 17);
		add(lblLogout);

		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 122);
		add(lblRedBlock);

	}
}
