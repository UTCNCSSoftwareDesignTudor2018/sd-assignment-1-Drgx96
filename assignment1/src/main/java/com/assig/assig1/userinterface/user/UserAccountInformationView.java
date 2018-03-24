package com.assig.assig1.userinterface.user;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.assig.assig1.IUserAccountInformationView;

public class UserAccountInformationView extends JFrame implements IUserAccountInformationView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7101040186039152277L;
	private IUserAccountInformationPresenter presenter;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblLastName;
	private JPanel panel_3;
	private JLabel lblIdentityCardNumber;
	private JPanel panel_4;
	private JTextField textField_lastName;
	private JTextField textField_firstName;
	private JTextField textField_identityCardNumber;
	private JTextField textField_address;
	private JPanel panel_1;
	private JLabel lblAccountInformation;
	private JPanel panel_5;
	private JButton btnSave;
	private JLabel lblAddress;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAccountInformationView window = new UserAccountInformationView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UserAccountInformationView() {
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sinu V10.0");
		getContentPane().setLayout(new GridLayout(6, 1, 0, 0));
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		lblAccountInformation = new JLabel("Account information");
		panel_1.add(lblAccountInformation);
		
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel = new JLabel("First name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		textField_firstName = new JTextField();
		panel.add(textField_firstName);
		textField_firstName.setColumns(10);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblLastName = new JLabel("Last name:");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblLastName);
		
		textField_lastName = new JTextField();
		panel_2.add(textField_lastName);
		textField_lastName.setColumns(10);
		
		panel_3 = new JPanel();
		getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblIdentityCardNumber = new JLabel("Identity card number:");
		lblIdentityCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblIdentityCardNumber);
		
		textField_identityCardNumber = new JTextField();
		panel_3.add(textField_identityCardNumber);
		textField_identityCardNumber.setColumns(10);
		
		panel_4 = new JPanel();
		getContentPane().add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblAddress);
		
		textField_address = new JTextField();
		panel_4.add(textField_address);
		textField_address.setColumns(10);
		
		panel_5 = new JPanel();
		getContentPane().add(panel_5);
		
		btnSave = new JButton("Save");
		panel_5.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			 public void actionPerformed(java.awt.event.ActionEvent evt) {
				 save(evt);
	            }
		});
	}

	private void save(ActionEvent evt) {
		presenter.saveAccountInformation(textField_firstName.getText(), textField_lastName.getText(), textField_identityCardNumber.getText(), textField_address.getText());
	}
	
	public void setPresenter(IUserAccountInformationPresenter p)
	{
		presenter = p;
	}

	public void setFirstName(String firstName) {
		textField_firstName.setText(firstName);
	}

	public void setLastName(String lastName) {
		textField_lastName.setText(lastName);
	}

	public void setICN(String icn) {
		textField_identityCardNumber.setText(icn);
	}

	public void setAddress(String address) {
		textField_address.setText(address);
	}
}
