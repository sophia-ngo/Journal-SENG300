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

public class Review extends JFrame {

	private JPanel contentPane;
	private int count = 1;
	private JTextArea textarea;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Review frame = new Review();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Review() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txt = new JTextArea();
		
		txt.setText("Write your comment here:");
		System.out.println(txt);
		txt.setBounds(15, 60, 250, 157);
		String message = txt.getText();
		contentPane.add(txt);
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
		lblNewLabel.setBounds(91, 30, 69, 20);
		contentPane.add(lblNewLabel);
	 
		JButton btnNewButton = new JButton("Resubmit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(280, 188, 115, 29);
		contentPane.add(btnNewButton);
		
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
		btnNewButton_1.setBounds(280, 155, 115, 29);
		contentPane.add(btnNewButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Minor Changes");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(273, 19, 155, 29);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Major Changes");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(273, 52, 155, 29);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Accept");
		buttonGroup_1.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(273, 85, 155, 29);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Reject");
		buttonGroup_1.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(273, 118, 155, 29);
		contentPane.add(rdbtnNewRadioButton_3);
	}
	
}
