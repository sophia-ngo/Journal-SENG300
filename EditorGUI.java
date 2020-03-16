import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorGUI extends JPanel {
	private JTextField SelectUser;

	/**
	 * Create the panel.
	 */
	public EditorGUI() {
		
		JList list = new JList();
		add(list);
		
		SelectUser = new JTextField();
		SelectUser.setText("Select Reviewer");
		add(SelectUser);
		SelectUser.setColumns(10);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnNewButton);

	}

}
