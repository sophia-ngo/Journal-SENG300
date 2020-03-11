import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class AuthorGUI extends JPanel {
	private JTextField filenameTEXT;
	String filename = null;

	/**
	 * Create the panel.
	 */
	public AuthorGUI() {
		setLayout(null);
		
		filenameTEXT = new JTextField();
		filenameTEXT.setBounds(35, 194, 230, 26);
		add(filenameTEXT);
		filenameTEXT.setColumns(10);
		
		JButton btnChooseFile = new JButton("chooseFile");
		btnChooseFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				filename = f.getAbsolutePath();
				filenameTEXT.setText(filename);
			}
		});
		btnChooseFile.setBounds(266, 194, 161, 29);
		add(btnChooseFile);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(266, 235, 161, 29);
		add(btnSubmit);
		
		

	}
}
