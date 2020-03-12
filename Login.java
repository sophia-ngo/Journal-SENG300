import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

/**
 * Login panel that has text fields for username and password. Also includes,
 * buttons for logging in and registering.
 * 
 * @author Group 2
 */
public class Login extends JPanel {
	private JTextField usernameTEXT;
	private JTextField passwordTEXT;

	/**
	 * Create the panel.
	 */
	public Login(JFrame frame, Authenticator auth) {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome to UJournal");

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("LM Mono 10", Font.BOLD, 16));
		lblNewLabel.setBounds(106, 28, 239, 16);
		add(lblNewLabel);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("LM Mono 10", Font.BOLD, 16));

		lblUsername.setBounds(99, 82, 87, 16);
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");

		lblPassword.setFont(new Font("LM Mono 10", Font.BOLD, 16));

		lblPassword.setBounds(99, 137, 73, 16);
		add(lblPassword);

		usernameTEXT = new JTextField();

		usernameTEXT.setFont(new Font("LM Mono 10", Font.PLAIN, 12));

		usernameTEXT.setBounds(206, 77, 130, 26);
		add(usernameTEXT);
		usernameTEXT.setColumns(10);

		passwordTEXT = new JTextField();

		passwordTEXT.setFont(new Font("LM Mono 10", Font.PLAIN, 12));

		passwordTEXT.setBounds(206, 132, 130, 26);
		add(passwordTEXT);
		passwordTEXT.setColumns(10);

		JLabel lblInvalidLogin = new JLabel("Invalid Login");

		lblInvalidLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidLogin.setFont(new Font("LM Mono 10", Font.BOLD, 16));
		lblInvalidLogin.setBounds(142, 235, 165, 16);

		add(lblInvalidLogin);
		lblInvalidLogin.setVisible(false);

		JButton btnLogin = new JButton("Login");

		btnLogin.setFont(new Font("LM Mono 10", Font.BOLD, 16));

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

		btnLogin.setBounds(99, 194, 117, 29);
		add(btnLogin);

		JButton btnRegister = new JButton("Register");

		btnRegister.setFont(new Font("LM Mono 10", Font.BOLD, 16));

		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register panel = new Register(frame, auth);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnRegister.setBounds(228, 194, 117, 29);
		add(btnRegister);

	}
}
