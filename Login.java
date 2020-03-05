import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {
	private JTextField usernameTEXT;
	private JTextField passwordTEXT;

	/**
	 * Create the panel.
	 */
	public Login(JFrame frame, Authenticator auth) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to U of C");
		lblNewLabel.setBounds(168, 20, 121, 16);
		add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(99, 82, 87, 16);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(99, 137, 73, 16);
		add(lblPassword);
		
		usernameTEXT = new JTextField();
		usernameTEXT.setBounds(206, 77, 130, 26);
		add(usernameTEXT);
		usernameTEXT.setColumns(10);
		
		passwordTEXT = new JTextField();
		passwordTEXT.setBounds(206, 132, 130, 26);
		add(passwordTEXT);
		passwordTEXT.setColumns(10);
		
		JLabel lblInvalidLogin = new JLabel("Invalid Login");
		lblInvalidLogin.setBounds(179, 235, 87, 16);
		add(lblInvalidLogin);
		lblInvalidLogin.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = usernameTEXT.getText();
				String pass = passwordTEXT.getText();
				Account acc = auth.login(user, pass);
				if (acc == null) {
					lblInvalidLogin.setVisible(true);
				}
			}
		});
		
		

		btnLogin.setBounds(99, 194, 117, 29);
		add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
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
