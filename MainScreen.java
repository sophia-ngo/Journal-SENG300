import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * Main screen of the program after logging in.
 * 
 * @author Group 2
 */
public class MainScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainScreen(JFrame frame, Account acc) {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblNewLabel = new JLabel(acc.getAccountType().getAccType() + " Account");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 149, 16);
		add(lblNewLabel);

		JLabel lblWelcome = new JLabel("UJournal");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 40));
		lblWelcome.setBounds(500, 48, 199, 42);
		add(lblWelcome);

		JLabel lblName = new JLabel("Hello, " + acc.getUsername() + "!");
		lblName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(513, 148, 173, 16);
		add(lblName);
		
		JLabel lblRedBlock = new JLabel("");
		lblRedBlock.setOpaque(true);
		lblRedBlock.setBackground(new Color(231, 43, 46));
		lblRedBlock.setBounds(0, 0, 1200, 122);
		add(lblRedBlock);

	}
}
