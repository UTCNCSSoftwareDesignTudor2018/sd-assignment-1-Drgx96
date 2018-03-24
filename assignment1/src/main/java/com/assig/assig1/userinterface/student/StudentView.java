package com.assig.assig1.userinterface.student;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.assig.assig1.IStudentView;

public class StudentView extends JFrame implements IStudentView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2584637196715006064L;
	private IStudentPresenter presenter;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentView window = new StudentView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public StudentView() {
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sinu V10.0 - Student");
		getContentPane().setLayout(new GridLayout(4, 1, 0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel);
		
		lblNewLabel = new JLabel("You are logged in as: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("<USERACCOUNT>");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		btnNewButton = new JButton("Account information");
		getContentPane().add(btnNewButton);
		
		
		btnNewButton_1 = new JButton("Student Infromation");
		getContentPane().add(btnNewButton_1);
		
		
		btnNewButton_3 = new JButton("Log Out");
		getContentPane().add(btnNewButton_3);
		
	}
	
	public void setPresenter(IStudentPresenter sp)
	{
		this.presenter =sp;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				presenter.presentAccountInformation();
	            }
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				presenter.presentStudentInformation();
	            }
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				presenter.logOut();
	            }
		});
	}

	public void display() {
		setVisible(true);
	}
}