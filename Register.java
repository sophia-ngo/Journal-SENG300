import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JPanel {
	private JTextField newuserTEXT;
	private JTextField newpassTEXT;
	private JTextField confirmpassTEXT;

	/**
	 * Create the panel.
	 */
	public Register(JFrame frame, Authenticator auth) {
		setLayout(null);
		
		JLabel lblRegisterForAccount = new JLabel("Register for Account");
		lblRegisterForAccount.setBounds(159, 35, 136, 16);
		add(lblRegisterForAccount);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(71, 80, 77, 16);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(71, 127, 77, 16);
		add(lblPassword);
		
		newuserTEXT = new JTextField();
		newuserTEXT.setBounds(216, 75, 130, 26);
		add(newuserTEXT);
		newuserTEXT.setColumns(10);
		
		newpassTEXT = new JTextField();
		newpassTEXT.setBounds(216, 122, 130, 26);
		add(newpassTEXT);
		newpassTEXT.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(71, 177, 120, 16);
		add(lblConfirmPassword);
		
		confirmpassTEXT = new JTextField();
		confirmpassTEXT.setBounds(216, 172, 130, 26);
		add(confirmpassTEXT);
		confirmpassTEXT.setColumns(10);
		
		JLabel lblInvalidPassword = new JLabel("Invalid Password");
		lblInvalidPassword.setBounds(173, 264, 105, 16);
		add(lblInvalidPassword);
		lblInvalidPassword.setVisible(false);
		
		JLabel lblPasswordDoesntMatch = new JLabel("Password doesn't match");
		lblPasswordDoesntMatch.setBounds(149, 264, 160, 16);
		add(lblPasswordDoesntMatch);
		lblPasswordDoesntMatch.setVisible(false);
		
		JLabel lblInvalidUsername = new JLabel("Invalid Username");
		lblInvalidUsername.setBounds(173, 264, 61, 16);
		add(lblInvalidUsername);
		lblInvalidUsername.setVisible(false);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = newuserTEXT.getText();
				String pass = newpassTEXT.getText();
				String confirm = confirmpassTEXT.getText();
				if (!pass.equals(confirm)) {
					lblPasswordDoesntMatch.setVisible(true);
					return;
				}
				String mess = auth.register(user, pass);
				if (mess == "Invalid username" || mess == "Username taken") {
					lblInvalidUsername.setVisible(true);
					return;
				}
				if (mess == "Invalid password") {
					lblInvalidPassword.setVisible(true);
					return;
				}
				Login panel = new Login(frame, auth);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnRegister.setBounds(98, 223, 117, 29);
		add(btnRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login panel = new Login(frame, auth);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnLogin.setBounds(227, 223, 117, 29);
		add(btnLogin);
		


	}

}
