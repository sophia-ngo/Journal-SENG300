import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class EditorGUI extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public EditorGUI(JFrame frame, Authenticator auth) {
		setLayout(null);
		
		String[] Reviewers= (EditorChooseReviewer.reviewers.clone());
		
		
		
		JComboBox comboBox = new JComboBox(Reviewers);
		comboBox.setBounds(159, 16, 36, 26);
		add(comboBox);
		
		JLabel Reviewer1 = new JLabel("Select Reviewer 1");
		Reviewer1.setBounds(15, 19, 141, 20);
		add(Reviewer1);
		
		JComboBox comboBox_1 = new JComboBox(Reviewers);
		comboBox_1.setBounds(159, 80, 36, 26);
		add(comboBox_1);
		
		JLabel Reviewer2 = new JLabel("Select Reviewer 2");
		Reviewer2.setBounds(15, 83, 129, 20);
		add(Reviewer2);
		
		JComboBox comboBox_2 = new JComboBox(Reviewers);
		comboBox_2.setBounds(159, 146, 36, 26);
		add(comboBox_2);
		
		JLabel reviewer3 = new JLabel("Select Reviewer 3");
		reviewer3.setBounds(15, 149, 129, 20);
		add(reviewer3);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(361, 16, 36, 26);
		add(comboBox_3);
		
		String[] Submissions = { };
		JLabel lblNewLabel = new JLabel("Submission");
		lblNewLabel.setBounds(242, 19, 104, 20);
		add(lblNewLabel);
		
		JButton editorAssign = new JButton("Assign");
		editorAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editorAssign.setBounds(282, 145, 115, 29);
		add(editorAssign);

	}
}
