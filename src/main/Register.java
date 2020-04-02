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
	 * Create the panel.
	 */

	public Register(JFrame frame, Authenticator auth, Database db) {
    
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblRegisterForAccount = new JLabel("Registration");
		lblRegisterForAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterForAccount.setFont(new Font("Arial", Font.BOLD, 20));
		lblRegisterForAccount.setBounds(494, 127, 212, 26);
		add(lblRegisterForAccount);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUsername.setBounds(348, 191, 77, 37);
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPassword.setBounds(348, 297, 77, 37);
		add(lblPassword);

		newuserTEXT = new JTextField();
		newuserTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		newuserTEXT.setColumns(10);
		newuserTEXT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		newuserTEXT.setBackground(new Color(245, 245, 245));
		newuserTEXT.setBounds(348, 227, 504, 34);
		add(newuserTEXT);
		newuserTEXT.setColumns(10);

		newPassTEXT = new JPasswordField();
		newPassTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		newPassTEXT.setColumns(10);
		newPassTEXT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		newPassTEXT.setBackground(new Color(245, 245, 245));
		newPassTEXT.setBounds(348, 335, 504, 34);
		add(newPassTEXT);
		newPassTEXT.setColumns(10);

		confirmPassTEXT = new JPasswordField();
		confirmPassTEXT.setDisabledTextColor(Color.LIGHT_GRAY);
		confirmPassTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		confirmPassTEXT.setColumns(10);
		confirmPassTEXT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		confirmPassTEXT.setBackground(new Color(245, 245, 245));
		confirmPassTEXT.setBounds(348, 433, 504, 34);
		add(confirmPassTEXT);
		confirmPassTEXT.setColumns(10);

		JLabel lblInvalidPassword = new JLabel("Password must be minimum of 8 characters.");
		lblInvalidPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblInvalidPassword.setForeground(new Color(255, 51, 51));
		lblInvalidPassword.setBounds(348, 369, 313, 26);
		add(lblInvalidPassword);
		lblInvalidPassword.setVisible(false);

		JLabel lblPasswordDoesntMatch = new JLabel("Passwords do not match.");
		lblPasswordDoesntMatch.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPasswordDoesntMatch.setForeground(new Color(255, 51, 51));
		lblPasswordDoesntMatch.setBounds(349, 465, 192, 26);
		add(lblPasswordDoesntMatch);
		lblPasswordDoesntMatch.setVisible(false);

		JLabel lblInvalidUsername = new JLabel("Username must be minimum of 4 characters.");
		lblInvalidUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lblInvalidUsername.setForeground(new Color(255, 51, 51));
		lblInvalidUsername.setBounds(348, 260, 319, 26);
		add(lblInvalidUsername);
		lblInvalidUsername.setVisible(false);

		JLabel lblUsernameTaken = new JLabel("Username already exists.");
		lblUsernameTaken.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUsernameTaken.setForeground(new Color(255, 51, 51));
		lblUsernameTaken.setBounds(348, 260, 186, 26);
		add(lblUsernameTaken);
		lblUsernameTaken.setVisible(false);

		String[] Choices = { "Author", "Reviewer", "Editor" };
		JComboBox aType = new JComboBox(Choices);
		aType.setBackground(Color.WHITE);
		aType.setBorder(null);
		aType.setFont(new Font("Arial", Font.PLAIN, 16));
		aType.setBounds(722, 507, 130, 27);
		add(aType);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(245, 245, 245));
		btnRegister.setFont(new Font("Arial", Font.BOLD, 16));
		btnRegister.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblPasswordDoesntMatch.setVisible(false);
				lblInvalidUsername.setVisible(false);
				lblInvalidPassword.setVisible(false);

				String user = newuserTEXT.getText();
				String pass = newPassTEXT.getText();
				AccountType accType = new AccountType();
				accType.setAccType(aType.getSelectedIndex());

				Boolean check = true;
				Boolean same = true; // same password check
				String confirm = confirmPassTEXT.getText();

				if (!pass.equals(confirm)) {
					lblPasswordDoesntMatch.setVisible(true);
					check = false;
					same = false;
				}

				if (!Account.checkUsername(user)) {
					lblInvalidUsername.setVisible(true);
					check = false;
				}

				if (auth.checkSameUser(user)) {
					lblUsernameTaken.setVisible(true);
					check = false;
				}

				if (!Account.checkPassword(pass)) {
					lblInvalidPassword.setVisible(true);
					check = false;
				}

				auth.register(user, pass, accType, same);
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

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(348, 396, 141, 37);
		add(lblConfirmPassword);

		JLabel lblAccountType = new JLabel("Account Type");
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAccountType.setBounds(348, 502, 105, 37);
		add(lblAccountType);
		
		JLabel showPass = new JLabel("");
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPass.setIcon(new ImageIcon(Register.class.getResource("/eye.png")));
		showPass.setHorizontalAlignment(SwingConstants.CENTER);
		showPass.setFont(new Font("Arial", Font.BOLD, 22));
		showPass.setBounds(858, 340, 39, 22);
		add(showPass);
		
		JLabel hidePass = new JLabel("");
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.setIcon(new ImageIcon(Register.class.getResource("/eyecross.png")));
		hidePass.setHorizontalAlignment(SwingConstants.CENTER);
		hidePass.setFont(new Font("Arial", Font.BOLD, 22));
		hidePass.setBounds(858, 340, 39, 22);
		add(hidePass);
		
		JLabel lblYellowBlock = new JLabel("");
		lblYellowBlock.setOpaque(true);
		lblYellowBlock.setBackground(new Color(255, 217, 17));
		lblYellowBlock.setBounds(0, 748, 1200, 52);
		add(lblYellowBlock);
		
		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 52);
		add(lblRedBlock);
		
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
