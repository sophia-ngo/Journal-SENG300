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
	private int p = 0;
	private int b = 0;
	private String change;
	private String decision;
	private String Response;

	/**
	 * Create the frame.
	 */
	//connects the class to the authenticator and the database
	public Review(JFrame frame, Account acc, Authenticator auth, Database db) {
		
		JTextArea txt = new JTextArea();
		
		txt.setText("Write your comment here:");
		System.out.println(txt);
		txt.setBounds(141, 163, 245, 164);
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
		lblNewLabel.setBounds(214, 118, 97, 29);
		add(lblNewLabel);
	 
		JButton btnNewButton = new JButton("Submit Comment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(241, 363, 123, 23);
		add(btnNewButton);
		
		//button for download
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
		btnNewButton_1.setBounds(492, 475, 136, 29);
		add(btnNewButton_1);
		
		//Informs of minor changes
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Minor Changes");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (rdbtnNewRadioButton.isSelected()) {
					p = 0;		
					System.out.println(p);
				}
			}
			
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(404, 164, 147, 23);
		add(rdbtnNewRadioButton);
		
		//Informs of major changes
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Major Changes");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (rdbtnNewRadioButton_1.isSelected()) {
					p = 1;		
					System.out.println(p);
				}
			}
			
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(404, 192, 147, 23);
		add(rdbtnNewRadioButton_1);
		
		//Informs of the paper being accepted
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Accept");
		rdbtnNewRadioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (rdbtnNewRadioButton_2.isSelected()) {
					b = 1;		
					System.out.println(b);
				}
			}
		});
		
		buttonGroup_1.add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_2.setBounds(603, 207, 106, 23);
		add(rdbtnNewRadioButton_2);
		
		//Informs of the paper being rejected
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Reject");
		rdbtnNewRadioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnNewRadioButton_3.isSelected()) {
					b = 0;		
					System.out.println(b);
				}
			}
			
		});

		buttonGroup_1.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(603, 162, 106, 23);
		add(rdbtnNewRadioButton_3);
		
		JButton btnNewButton_2 = new JButton("Submit Response");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (p > 0 ) {
					change = "Major Change";
				}
				else {
					change = "Minor Change";
				}
				if (b > 0 ) {
					decision = "Accept";				
				}
				else {
					decision = "Reject";
				}
				Response = change + " " + decision ;
				try {
					FileWriter writer = new FileWriter("changes.txt", false);
					BufferedWriter bWriter = new BufferedWriter(writer);
					bWriter.write(Response);		// write string into text file
					bWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnNewButton_2.setBounds(508, 330, 160, 29);
		add(btnNewButton_2);
	}
}