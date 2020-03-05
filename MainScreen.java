import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainScreen(JFrame frame, Account acc) {
		setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to the UofC Journal Submission System!");
		lblWelcome.setBounds(74, 117, 310, 16);
		add(lblWelcome);
		
		JLabel lblName = new JLabel("HI! " + acc.getUsername());
		lblName.setBounds(166, 145, 132, 16);
		add(lblName);
		
		JLabel lblNewLabel = new JLabel(acc.getAccountType().getAccType() +  " Account");
		lblNewLabel.setBounds(6, 6, 202, 16);
		add(lblNewLabel);
	}
}
