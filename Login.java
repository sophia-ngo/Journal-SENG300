import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Cursor;
import java.awt.TextArea;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

/**
 * Login panel that has text fields for username and password. Also includes,
 * buttons for logging in and registering.
 *
 * @author colinauyeung, Group 2
 */
public class Login extends JPanel {
	private JTextField usernameTEXT;
	private JTextField passwordTEXT;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Login(JFrame frame, Authenticator auth) {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(350, 326, 78, 16);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblPassword.setBounds(350, 401, 87, 16);

		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		add(lblPassword);

		JLabel lblInvalidLogin = new JLabel(
				"<html>The username and password entered did not match our records. Please double-check and try again.<html>");
		lblInvalidLogin.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblInvalidLogin.setForeground(new Color(255, 51, 51));
		lblInvalidLogin.setBounds(350, 461, 500, 36);

		lblInvalidLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidLogin.setFont(new Font("Arial", Font.PLAIN, 16));

		add(lblInvalidLogin);
		lblInvalidLogin.setVisible(false);

		JButton btnLogin = new JButton("Log In");
		btnLogin.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnLogin.setContentAreaFilled(false);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(472, 526, 117, 29);

		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = usernameTEXT.getText();
				String pass = passwordTEXT.getText();
				Account acc = auth.login(user, pass);
				if (acc == null) {
					lblInvalidLogin.setVisible(true);
				} else {
					MainScreen panel = new MainScreen(frame, acc);
					frame.setContentPane(panel);
					frame.revalidate();
				}
			}
		});
		add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRegister.setContentAreaFilled(false);
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.setBounds(603, 526, 117, 29);

		btnRegister.setFont(new Font("Arial", Font.BOLD, 16));

		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register panel = new Register(frame, auth);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		add(btnRegister);

		passwordTEXT = new JPasswordField();
		passwordTEXT.setBackground(new Color(245, 245, 245));
		passwordTEXT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		passwordTEXT.setBounds(350, 428, 500, 22);
		add(passwordTEXT);

		passwordTEXT.setFont(new Font("Dialog", Font.PLAIN, 16));
		passwordTEXT.setColumns(10);

		usernameTEXT = new JTextField();
		usernameTEXT.setBackground(new Color(245, 245, 245));
		usernameTEXT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		usernameTEXT.setBounds(350, 353, 500, 22);
		add(usernameTEXT);

		usernameTEXT.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameTEXT.setColumns(10);

		JLabel lblNewLabel = new JLabel("UJournal");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblNewLabel.setBounds(541, 266, 117, 29);
		add(lblNewLabel);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));

		JLabel showPass = new JLabel("");
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		showPass.setIcon(new ImageIcon(Login.class.getResource("/images/eye.png")));
		showPass.setHorizontalAlignment(SwingConstants.CENTER);
		showPass.setFont(new Font("Arial", Font.BOLD, 22));
		showPass.setBounds(849, 428, 39, 22);
		add(showPass);

		JLabel hidePass = new JLabel("");
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.setIcon(new ImageIcon(Login.class.getResource("/images/eyecross.png")));
		hidePass.setHorizontalAlignment(SwingConstants.CENTER);
		hidePass.setFont(new Font("Arial", Font.BOLD, 22));
		hidePass.setBounds(849, 428, 39, 22);
		add(hidePass);
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
				((JPasswordField) passwordTEXT).setEchoChar('•');
			}
		});

	}
}
