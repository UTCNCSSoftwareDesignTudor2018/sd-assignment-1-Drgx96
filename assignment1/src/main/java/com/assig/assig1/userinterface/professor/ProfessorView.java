package com.assig.assig1.userinterface.professor;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.assig.assig1.IProfessorView;

public class ProfessorView extends JFrame implements IProfessorView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3253044117768006799L;
	private IProfessorPresenter presenter;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfessorView window = new ProfessorView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ProfessorView() {
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sinu V10.0 - Professor");
		getContentPane().setLayout(new GridLayout(5, 1, 0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel);
		
		lblNewLabel = new JLabel("You are logged in as: ");
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("<USERACCOUNT>");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setText(presenter.getUsername());
		
		btnNewButton = new JButton("Account Information");
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				presenter.presentAccountInformation();
	            }
		});
		
		btnNewButton_1 = new JButton("Edit Student Information");
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				presenter.presentStudentsInformation();
	            }
		});
		
		btnNewButton_2 = new JButton("Make a report");
		getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				presenter.presentReportsInformation();
	            }
		});
		
		btnNewButton_3 = new JButton("Log Out");
		getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				presenter.logOut();
	            }
		});
	}
	
	public void setPresenter(IProfessorPresenter p) {
		presenter = p;
	}

	public void showStudents() {
		// TODO Auto-generated method stub
		
	}
}
