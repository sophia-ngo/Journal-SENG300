package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Review extends JPanel {

	private JPanel contentPane;
	private int count = 1;
	private JTextArea textarea;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public Review(JFrame frame, Account acc, Authenticator auth, Database db) {
		
		JTextArea txt = new JTextArea();
		
		txt.setText("Write your comment here:");
		System.out.println(txt);
		txt.setBounds(175, 163, 196, 22);
		String message = txt.getText();
		setLayout(null);
		add(txt);
		/*
		try {
		FileWriter pw = new FileWriter ("filename.txt");
        txt.write(pw);
       // message.write(pw);
        textarea.write(pw);
        pw.close();
		}
		catch (Exception e) {
			
		}
		*/
		try {
			FileWriter writer = new FileWriter("comment.txt", true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.write(message);		// write string into text file
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel("Comment");
		lblNewLabel.setBounds(176, 140, 45, 14);
		add(lblNewLabel);
	 
		JButton btnNewButton = new JButton("Resubmit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(404, 317, 97, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Download");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				db.dbLoad();
				int c_1 = count - 1;
				Submission s1 = db.dbGet(acc.getUsername()+c_1);
				s1.download();
				*/
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(404, 283, 97, 23);
		add(btnNewButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Minor Changes");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(404, 164, 97, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Major Changes");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(404, 192, 97, 23);
		add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Accept");
		buttonGroup_1.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(404, 220, 59, 23);
		add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Reject");
		buttonGroup_1.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(404, 246, 57, 23);
		add(rdbtnNewRadioButton_3);
	}
	
}
