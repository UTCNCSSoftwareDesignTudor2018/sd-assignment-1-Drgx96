package com.assig.assig1.userinterface.user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.assig.assig1.ILoginView;

public class LogInView extends JFrame implements ILoginView{

	private JTextField textField_password;
	private JTextField textField_username;
	private JLabel lblUsername;
	private JLabel lblPleaseInputYour;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private ILoginPresenter presenter;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInView window = new LogInView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LogInView() {
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		setTitle("Sinu V10.0");
		
		lblNewLabel_1 = new JLabel("Welcome to a better SINU!");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1);
		
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblPleaseInputYour = new JLabel("Please input your credentials:");
		lblPleaseInputYour.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPleaseInputYour);
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		
		lblUsername = new JLabel("Username:");
		panel_1.add(lblUsername);
		
		textField_username = new JTextField();
		panel_1.add(textField_username);
		textField_username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password:");
		panel_1.add(lblNewLabel);
		
		textField_password = new JTextField();
		panel_1.add(textField_password);
		textField_password.setColumns(10);
		
		JButton btnNewButton = new JButton("Log In");
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(java.awt.event.ActionEvent evt) {
				 logInActionPerformed(evt);
	            }
		});
	}
	
	private void logInActionPerformed(ActionEvent event)
	{
		presenter.logIn(textField_username.getText(), textField_password.getText());
	}

	public void showInvalidPassword() {
		JOptionPane.showMessageDialog(this, "The entered password is invalid.");
	}

	public void showInvalidUsername() {
		JOptionPane.showMessageDialog(this, "The entered username is invalid.");
	}
	
	public void setPresenter(ILoginPresenter lp)
	{
		this.presenter = lp;
	}

	public void display() {
		this.setVisible(true);
	}

	public void showInvalidCredentials() {
		// TODO Auto-generated method stub
		
	}

	public void dontDisplay() {
		this.setVisible(false);
	}
}
