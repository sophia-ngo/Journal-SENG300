import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Main frame that runs the program.
 * 
 * @author Group 2
 */
public class Mainframe {
	private JFrame frame;
	private static Authenticator auth = new Authenticator();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					auth.checkAccounts();
					Mainframe window = new Mainframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Sets the first window of the program to the login screen.
	 */
	public Mainframe() {
		frame = new JFrame();
		frame.setTitle("UJournal");
		frame.setSize(1200, 800);
		frame.setLocationRelativeTo(null); // centers screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login panel = new Login(frame, auth);
		frame.setContentPane(panel);
		frame.revalidate();
	}

}
