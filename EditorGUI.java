import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;

public class EditorGUI extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public EditorGUI() {
		String[] Reviewers= (EditorChooseReviewer.reviewers.clone());
		
		JLabel SelectReviewer = new JLabel("Select Reviewer");
		add(SelectReviewer);
		JComboBox Reviewerlist = new JComboBox(Reviewers);
		Reviewerlist.setEditable(true);
		Reviewerlist.setMaximumRowCount(100);
		add(Reviewerlist);
		
		JButton btnNewButton = new JButton("Assign");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Reviewer Assigned");
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		textField = new JTextField(Arrays.toString(Reviewers));
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Select Paper");
		add(lblNewLabel);
		
		String[] Submissions = { };
		JComboBox comboBox = new JComboBox();
		add(comboBox);
		add(btnNewButton);

	}

}
