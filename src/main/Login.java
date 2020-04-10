package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Cursor;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

/**
 * Login panel that has text fields for username and password. Also includes,
 * buttons for logging in and registering.
 *
 * @author colinauyeung, Group 2
 */
public class Login extends JPanel {

	private JTextField usernameTEXT;
	private JPasswordField passwordTEXT;

	/**
	 * Login screen, redirects to main screen or registration.
	 * 
	 * @param frame
	 * @param auth
	 * @param db
	 */
	public Login(JFrame frame, Authenticator auth, Database db) {

		// Sets background and layout
		setBackground(Color.WHITE);
		setLayout(null);

		// Label for success
		JLabel lblSuccess = new JLabel("Successfully registered!");
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccess.setForeground(new Color(51, 204, 0));
		lblSuccess.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSuccess.setBounds(661, 221, 170, 22);
		add(lblSuccess);
		lblSuccess.setVisible(auth.getSuccess());

		// Label for username
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(661, 267, 78, 16);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblUsername);

		// Label for password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(661, 401, 87, 16);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblPassword);

		// Invalid label when something is not valid
		JLabel lblInvalidLogin = new JLabel(
				"<html>The username and password entered did not match our records. Please double-check and try again.<html>");
		lblInvalidLogin.setForeground(new Color(255, 51, 51));
		lblInvalidLogin.setBounds(661, 492, 449, 36);
		lblInvalidLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidLogin.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblInvalidLogin);
		lblInvalidLogin.setVisible(false);

		// Login button
		JButton btnLogin = new JButton("Log In");
		btnLogin.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnLogin.setBackground(new Color(245, 245, 245));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(661, 558, 449, 29);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				auth.setSuccess(false); // register success not shown
				// Gets text from textfields
				String user = usernameTEXT.getText();
				String pass = passwordTEXT.getText();
				Account acc = auth.login(user, pass);
				if (acc == null) {
					lblInvalidLogin.setVisible(true);
				} else {
					String type = acc.getAccountType().getAccType(); // gets account type

					// Sets to author panel
					if (type.equals("Author")) {
						Author panel = new Author(frame, acc, auth, db);
						frame.setContentPane(panel);
						frame.revalidate();
						// Sets to reviewer panel
					} else if (type.equals("Reviewer")) {
						Reviewer panel = new Reviewer(frame, acc, auth, db);
						frame.setContentPane(panel);
						frame.revalidate();
						// Sets to editor panel
					} else if (type.equals("Editor")) {
						Editor panel = new Editor(frame, acc, auth, db);
						frame.setContentPane(panel);
						frame.revalidate();
					}
				}
			}
		});
		add(btnLogin);

		// Register button
		JButton btnRegister = new JButton("Register");
		btnRegister.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnRegister.setBackground(new Color(245, 245, 245));
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.setBounds(661, 598, 449, 29);

		btnRegister.setFont(new Font("Arial", Font.BOLD, 16));

		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			// Changes to registration panel
			public void mouseClicked(MouseEvent e) {
				Register panel = new Register(frame, auth, db);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		add(btnRegister);

		// Text field for password
		passwordTEXT = new JPasswordField();
		passwordTEXT.setBackground(new Color(245, 245, 245));
		passwordTEXT.setBorder(new MatteBorder(0, 0, 2, 0, new Color(180, 180, 180)));
		passwordTEXT.setBounds(661, 445, 449, 36);
		passwordTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordTEXT.setColumns(10);
		add(passwordTEXT);

		// Text field for username
		usernameTEXT = new JTextField();
		usernameTEXT.setBackground(new Color(245, 245, 245));
		usernameTEXT.setBorder(new MatteBorder(0, 0, 2, 0, new Color(180, 180, 180)));
		usernameTEXT.setBounds(661, 311, 449, 36);
		usernameTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameTEXT.setColumns(10);
		add(usernameTEXT);

		// Eye icon
		JLabel showPass = new JLabel("");
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPass.setIcon(new ImageIcon(Login.class.getResource("/eye.png")));
		showPass.setHorizontalAlignment(SwingConstants.CENTER);
		showPass.setFont(new Font("Arial", Font.BOLD, 22));
		showPass.setBounds(1115, 450, 39, 22);
		add(showPass);

		// Crossed eye icon
		JLabel hidePass = new JLabel("");
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.setIcon(new ImageIcon(Login.class.getResource("/eyecross.png")));
		hidePass.setHorizontalAlignment(SwingConstants.CENTER);
		hidePass.setFont(new Font("Arial", Font.BOLD, 22));
		hidePass.setBounds(1115, 450, 39, 22);
		add(hidePass);

		// Label for register
		JLabel lblRegisterToday = new JLabel("Register today.");
		lblRegisterToday.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterToday.setForeground(new Color(40, 40, 40));
		lblRegisterToday.setFont(new Font("Arial", Font.BOLD, 18));
		lblRegisterToday.setBounds(186, 411, 228, 29);
		add(lblRegisterToday);

		// Label for slogan
		JLabel lblForWritersEditors = new JLabel("For authors, reviewers, editors.");
		lblForWritersEditors.setHorizontalAlignment(SwingConstants.CENTER);
		lblForWritersEditors.setForeground(Color.WHITE);
		lblForWritersEditors.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		lblForWritersEditors.setBounds(154, 356, 292, 22);
		add(lblForWritersEditors);

		// Label for title
		JLabel lblUJournal = new JLabel("UJournal");
		lblUJournal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUJournal.setForeground(Color.WHITE);
		lblUJournal.setFont(new Font("Arial", Font.BOLD, 40));
		lblUJournal.setBounds(0, 298, 600, 42);
		add(lblUJournal);

		// UofC logo
		JLabel lblUOfC = new JLabel("");
		lblUOfC.setIcon(new ImageIcon(Login.class.getResource("/uofclogosmall.png")));
		lblUOfC.setBounds(829, 63, 154, 157);
		add(lblUOfC);

		// Decorative red block
		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 600, 400);
		add(lblRedBlock);

		// Decorative yellow block
		JLabel lblYellowBlock = new JLabel("");
		lblYellowBlock.setOpaque(true);
		lblYellowBlock.setBackground(new Color(255, 217, 17));
		lblYellowBlock.setBounds(0, 400, 600, 400);
		add(lblYellowBlock);

		// Shows password when eye is clicked
		hidePass.setVisible(false);
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPass.setVisible(false);
				hidePass.setVisible(true);
				passwordTEXT.setEchoChar((char) 0);
			}
		});

		// Hides password when crossed eye is clicked
		hidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hidePass.setVisible(false);
				showPass.setVisible(true);
				passwordTEXT.setEchoChar('•');
			}
		});

	}
}
