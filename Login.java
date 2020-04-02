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
	 * Create the panel.
	 */
	public Login(JFrame frame, Authenticator auth, Database db) {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblSuccess = new JLabel("Successfully registered!");
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccess.setForeground(new Color(51, 204, 0));
		lblSuccess.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSuccess.setBounds(661, 221, 170, 22);
		add(lblSuccess);
		lblSuccess.setVisible(auth.getSuccess());

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(661, 267, 78, 16);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(661, 401, 87, 16);

		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblPassword);

		JLabel lblInvalidLogin = new JLabel(
				"<html>The username and password entered did not match our records. Please double-check and try again.<html>");
		lblInvalidLogin.setForeground(new Color(255, 51, 51));
		lblInvalidLogin.setBounds(661, 492, 449, 36);

		lblInvalidLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidLogin.setFont(new Font("Arial", Font.PLAIN, 16));

		add(lblInvalidLogin);
		lblInvalidLogin.setVisible(false);

		JButton btnLogin = new JButton("Log In");
		btnLogin.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnLogin.setBackground(new Color(245, 245, 245));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(661, 558, 449, 29);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				auth.setSuccess(false);
				String user = usernameTEXT.getText();
				String pass = passwordTEXT.getText();
				Account acc = auth.login(user, pass);
				if (acc == null) {
					lblInvalidLogin.setVisible(true);
				} else {
					String type = acc.getAccountType().getAccType();

					if (type.equals("Author")) {
						AuthorGUI panel = new AuthorGUI(frame, acc, auth, db);
						frame.setContentPane(panel);
						frame.revalidate();
					} else if (type.equals("Reviewer")) {
						MainScreen panel = new MainScreen(frame, acc, auth, db);
						frame.setContentPane(panel);
						frame.revalidate();
					} else if (type.equals("Editor")) {
						EditorGUI panel = new EditorGUI(frame, auth);
						frame.setContentPane(panel);
						frame.revalidate();
					}
				}
			}
		});
		add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnRegister.setBackground(new Color(245, 245, 245));
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.setBounds(661, 598, 449, 29);

		btnRegister.setFont(new Font("Arial", Font.BOLD, 16));

		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register panel = new Register(frame, auth, db);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		add(btnRegister);

		passwordTEXT = new JPasswordField();
		passwordTEXT.setBackground(new Color(245, 245, 245));
		passwordTEXT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		passwordTEXT.setBounds(661, 445, 449, 36);
		add(passwordTEXT);

		passwordTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordTEXT.setColumns(10);

		usernameTEXT = new JTextField();
		usernameTEXT.setBackground(new Color(245, 245, 245));
		usernameTEXT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		usernameTEXT.setBounds(661, 311, 449, 36);
		add(usernameTEXT);

		usernameTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameTEXT.setColumns(10);

		JLabel showPass = new JLabel("");
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		showPass.setIcon(new ImageIcon(Login.class.getResource("/images/eye.png")));
		showPass.setHorizontalAlignment(SwingConstants.CENTER);
		showPass.setFont(new Font("Arial", Font.BOLD, 22));
		showPass.setBounds(1115, 450, 39, 22);
		add(showPass);

		JLabel hidePass = new JLabel("");
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.setIcon(new ImageIcon(Login.class.getResource("/images/eyecross.png")));
		hidePass.setHorizontalAlignment(SwingConstants.CENTER);
		hidePass.setFont(new Font("Arial", Font.BOLD, 22));
		hidePass.setBounds(1115, 450, 39, 22);
		add(hidePass);

		JLabel lblRegisterToday = new JLabel("Register today.");
		lblRegisterToday.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterToday.setForeground(new Color(40, 40, 40));
		lblRegisterToday.setFont(new Font("Arial", Font.BOLD, 18));
		lblRegisterToday.setBounds(186, 411, 228, 29);
		add(lblRegisterToday);

		JLabel lblForWritersEditors = new JLabel("For authors, reviewers, editors.");
		lblForWritersEditors.setHorizontalAlignment(SwingConstants.CENTER);
		lblForWritersEditors.setForeground(Color.WHITE);
		lblForWritersEditors.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		lblForWritersEditors.setBounds(154, 356, 292, 22);
		add(lblForWritersEditors);

		JLabel lblUJournal = new JLabel("UJournal");
		lblUJournal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUJournal.setForeground(Color.WHITE);
		lblUJournal.setFont(new Font("Arial", Font.BOLD, 40));
		lblUJournal.setBounds(0, 298, 600, 42);
		add(lblUJournal);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/uofclogosmall.png")));
		lblNewLabel.setBounds(850, 638, 76, 98);
		add(lblNewLabel);
		
				JLabel lblRedBlock = new JLabel("");
				lblRedBlock.setOpaque(true);
				lblRedBlock.setBackground(new Color(231, 43, 46));
				lblRedBlock.setBounds(0, 0, 600, 400);
				add(lblRedBlock);
				
						JLabel lblYellowBlock = new JLabel("");
						lblYellowBlock.setOpaque(true);
						lblYellowBlock.setBackground(new Color(255, 217, 17));
						lblYellowBlock.setBounds(0, 400, 600, 400);
						add(lblYellowBlock);

		hidePass.setVisible(false);
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPass.setVisible(false);
				hidePass.setVisible(true);
				((JPasswordField) passwordTEXT).setEchoChar((char) 0);
			}
		});

		hidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hidePass.setVisible(false);
				showPass.setVisible(true);
				((JPasswordField) passwordTEXT).setEchoChar('�');
			}
		});

	}
}
