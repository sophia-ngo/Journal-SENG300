import java.awt.EventQueue;

import javax.swing.JFrame;

public class Mainframe {
	private JFrame frame;
	private Authenticator auth = new Authenticator();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe window = new Mainframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public Mainframe() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AccountType at = new AccountType();
		Account aAcc = new Account("Ishan", "123456789123456789", at);
		Database db = new Database();
		Login panel = new Login(frame, auth, db);
		Author paneltest = new Author(aAcc, db);
		frame.setContentPane(paneltest);
		frame.revalidate();
	}

}
