package main;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

/**
 * Registration window where user will enter username, password, and select a
 * role. After registering the account will be stored to be used when logging
 * in.
 * 
 * @author Group 2
 */
public class Register extends JPanel {

	private JTextField newuserTEXT;
	private JTextField newPassTEXT;
	private JTextField confirmPassTEXT;

	/**
	 * Registration GUI allows for registering an account and shows errors on
	 * screen.
	 * 
	 * @param frame
	 * @param auth
	 * @param db
	 */
	public Register(JFrame frame, Authenticator auth, Database db) {

		// Sets background and layout
		setBackground(Color.WHITE);
		setLayout(null);

		// Title of registration screen
		JLabel lblRegisterForAccount = new JLabel("Registration");
		lblRegisterForAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterForAccount.setFont(new Font("Arial", Font.BOLD, 20));
		lblRegisterForAccount.setBounds(494, 127, 212, 26);
		add(lblRegisterForAccount);

		// Label for username
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUsername.setBounds(348, 191, 77, 37);
		add(lblUsername);

		// Label for password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPassword.setBounds(348, 297, 77, 37);
		add(lblPassword);

		// Field for username
		newuserTEXT = new JTextField();
		newuserTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		newuserTEXT.setColumns(10);
		newuserTEXT.setBorder(new MatteBorder(0, 0, 2, 0, new Color(180, 180, 180)));
		newuserTEXT.setBackground(new Color(245, 245, 245));
		newuserTEXT.setBounds(348, 227, 504, 34);
		add(newuserTEXT);
		newuserTEXT.setColumns(10);

		// First field for password
		newPassTEXT = new JPasswordField();
		newPassTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		newPassTEXT.setColumns(10);
		newPassTEXT.setBorder(new MatteBorder(0, 0, 2, 0, new Color(180, 180, 180)));
		newPassTEXT.setBackground(new Color(245, 245, 245));
		newPassTEXT.setBounds(348, 335, 504, 34);
		add(newPassTEXT);
		newPassTEXT.setColumns(10);

		// Second field for password
		confirmPassTEXT = new JPasswordField();
		confirmPassTEXT.setDisabledTextColor(Color.LIGHT_GRAY);
		confirmPassTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		confirmPassTEXT.setColumns(10);
		confirmPassTEXT.setBorder(new MatteBorder(0, 0, 2, 0, new Color(180, 180, 180)));
		confirmPassTEXT.setBackground(new Color(245, 245, 245));
		confirmPassTEXT.setBounds(348, 433, 504, 34);
		add(confirmPassTEXT);
		confirmPassTEXT.setColumns(10);

		// Label that shows password must be minimum of 8 characters
		JLabel lblInvalidPassword = new JLabel("Password must be minimum of 8 characters.");
		lblInvalidPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblInvalidPassword.setForeground(new Color(255, 51, 51));
		lblInvalidPassword.setBounds(348, 369, 313, 26);
		add(lblInvalidPassword);
		lblInvalidPassword.setVisible(false);

		// Label that shows if passwords do not match
		JLabel lblPasswordDoesntMatch = new JLabel("Passwords do not match.");
		lblPasswordDoesntMatch.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPasswordDoesntMatch.setForeground(new Color(255, 51, 51));
		lblPasswordDoesntMatch.setBounds(349, 465, 192, 26);
		add(lblPasswordDoesntMatch);
		lblPasswordDoesntMatch.setVisible(false);

		// Label that shows if username is not between 4-12 characters
		JLabel lblInvalidUsername = new JLabel("Username must be between 4-12 characters.");
		lblInvalidUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lblInvalidUsername.setForeground(new Color(255, 51, 51));
		lblInvalidUsername.setBounds(348, 260, 319, 26);
		add(lblInvalidUsername);
		lblInvalidUsername.setVisible(false);

		// Label that shows if username exists already
		JLabel lblUsernameTaken = new JLabel("Username already exists.");
		lblUsernameTaken.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUsernameTaken.setForeground(new Color(255, 51, 51));
		lblUsernameTaken.setBounds(348, 260, 186, 26);
		add(lblUsernameTaken);
		lblUsernameTaken.setVisible(false);

		// Dropdown menu for account type
		String[] Choices = { "Author", "Reviewer", "Editor" };
		JComboBox aType = new JComboBox(Choices);
		aType.setBackground(Color.WHITE);
		aType.setBorder(null);
		aType.setFont(new Font("Arial", Font.PLAIN, 16));
		aType.setBounds(722, 507, 130, 27);
		add(aType);

		// Button to confirm registration
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(245, 245, 245));
		btnRegister.setFont(new Font("Arial", Font.BOLD, 16));
		btnRegister.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets all error messages false
				lblPasswordDoesntMatch.setVisible(false);
				lblInvalidUsername.setVisible(false);
				lblInvalidPassword.setVisible(false);

				// Get text from the text fields
				String user = newuserTEXT.getText();
				String pass = newPassTEXT.getText();
				AccountType accType = new AccountType();
				accType.setAccType(aType.getSelectedIndex());

				Boolean check = true; // overall check for errors
				Boolean same = true; // same password check
				String confirm = confirmPassTEXT.getText();

				// Checks if passwords are the same
				if (!pass.equals(confirm)) {
					lblPasswordDoesntMatch.setVisible(true);
					check = false;
					same = false;
				}

				// Checks if username is valid
				if (!Account.checkUsername(user)) {
					lblInvalidUsername.setVisible(true);
					check = false;
				}

				// Checks if account already exists
				if (auth.checkSameUser(user)) {
					lblUsernameTaken.setVisible(true);
					check = false;
				}

				// Checks if passworld is valid
				if (!Account.checkPassword(pass)) {
					lblInvalidPassword.setVisible(true);
					check = false;
				}

				auth.register(user, pass, accType, same); // registers account
				// Sends back to login screen if all information is correct
				if (check) {
					auth.setSuccess(true);
					Login panel = new Login(frame, auth, db);
					frame.setContentPane(panel);
					frame.revalidate();
				}
			}
		});
		btnRegister.setBounds(348, 610, 504, 29);
		add(btnRegister);

		// Button to go back to login screen
		JLabel btnBack = new JLabel("\u2190");
		btnBack.setForeground(Color.WHITE);
		btnBack.setHorizontalAlignment(SwingConstants.CENTER);
		btnBack.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBack.setFont(new Font("Arial", Font.BOLD, 30));
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				auth.setSuccess(false);
				Login panel = new Login(frame, auth, db);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnBack.setBounds(10, 11, 47, 26);
		add(btnBack);

		// Label for confirm password
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(348, 396, 141, 37);
		add(lblConfirmPassword);

		// Label for account type
		JLabel lblAccountType = new JLabel("Account Type");
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(348, 502, 105, 37);
		add(lblAccountType);

		// Eye picture
		JLabel showPass = new JLabel("");
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPass.setIcon(new ImageIcon(Register.class.getResource("/eye.png")));
		showPass.setHorizontalAlignment(SwingConstants.CENTER);
		showPass.setFont(new Font("Arial", Font.BOLD, 22));
		showPass.setBounds(858, 340, 39, 22);
		add(showPass);

		// Crossed eye picture
		JLabel hidePass = new JLabel("");
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.setIcon(new ImageIcon(Register.class.getResource("/eyecross.png")));
		hidePass.setHorizontalAlignment(SwingConstants.CENTER);
		hidePass.setFont(new Font("Arial", Font.BOLD, 22));
		hidePass.setBounds(858, 340, 39, 22);
		add(hidePass);

		// Decorative yellow block
		JLabel lblYellowBlock = new JLabel("");
		lblYellowBlock.setOpaque(true);
		lblYellowBlock.setBackground(new Color(255, 217, 17));
		lblYellowBlock.setBounds(0, 748, 1200, 52);
		add(lblYellowBlock);

		// Decorative red block
		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 52);
		add(lblRedBlock);

		// Shows password when eye is clicked
		hidePass.setVisible(false);
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPass.setVisible(false);
				hidePass.setVisible(true);
				((JPasswordField) newPassTEXT).setEchoChar((char) 0);
				((JPasswordField) confirmPassTEXT).setEchoChar((char) 0);
			}
		});

		// Hides password when crossed eye is clicked
		hidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hidePass.setVisible(false);
				showPass.setVisible(true);
				((JPasswordField) newPassTEXT).setEchoChar('•');
				((JPasswordField) confirmPassTEXT).setEchoChar('•');
			}
		});

	}
}
