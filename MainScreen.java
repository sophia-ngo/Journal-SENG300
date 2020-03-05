import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;

public class MainScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainScreen(JFrame frame, Account acc) {
		setLayout(null);
		

		JLabel lblNewLabel = new JLabel(acc.getAccountType().getAccType() +  " Account");
		lblNewLabel.setBounds(6, 6, 202, 16);
		add(lblNewLabel);

		JLabel lblWelcome = new JLabel("Welcome to the UofC\n");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("LM Mono 10", Font.BOLD, 16));
		lblWelcome.setBounds(133, 122, 184, 16);
		add(lblWelcome);
		
		JLabel lblName = new JLabel("Hello, " + acc.getUsername() + "!");
		lblName.setFont(new Font("LM Mono 10", Font.BOLD, 14));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(138, 170, 173, 16);
		add(lblName);
		
		JLabel lblUofcJournalSubmission = new JLabel("Journal Submission System!");
		lblUofcJournalSubmission.setHorizontalAlignment(SwingConstants.CENTER);
		lblUofcJournalSubmission.setFont(new Font("LM Mono 10", Font.BOLD, 16));
		lblUofcJournalSubmission.setBounds(97, 144, 256, 16);
		add(lblUofcJournalSubmission);

	}
}
