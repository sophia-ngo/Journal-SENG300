import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	public AuthorGUI(Database db) {
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
				FileNameExtensionFilter docFilter = new FileNameExtensionFilter("PDF Files", "pdf");
				chooser.setFileFilter(docFilter);
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				filename = f.getAbsolutePath();
				filenameTEXT.setText(filename);
			}
		});
		btnChooseFile.setBounds(266, 194, 161, 29);
		add(btnChooseFile);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Submission s1 = new Submission();
				s1.submit(filenameTEXT.getText());
				db.dbAdd("sub1",s1);
			}
		});
		btnSubmit.setBounds(266, 235, 161, 29);
		add(btnSubmit);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Submission s1 = db.dbGet("sub1");
				s1.download();
			}
		});
		btnDownload.setBounds(280, 153, 117, 29);
		add(btnDownload);
		
		

	}
}
