package com.assig.assig1.userinterface.student;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.assig.assig1.IStudentInformationView;
import com.assig.assig1.presenters.StudentPresenter;

import javax.swing.JTable;

public class StudentInformationView extends JFrame implements IStudentInformationView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5270705953458728655L;
	private IStudentInformationPresenter presenter;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblLastName;
	private JTextField textField_lastName;
	private JTextField textField_firstName;
	private JPanel panel_1;
	private JLabel lblAccountInformation;
	private JPanel panel_5;
	private JPanel panel_3;
	private JButton btnAddEnrollment;
	private JButton btnLeaveSelectedCourses;
	private JTable table;
	private LinkedList<String> selectedCourses;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInformationView window = new StudentInformationView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public StudentInformationView() {
		selectedCourses = new LinkedList<String>();
		initialize();
	}
	
	public void setPresenter(IStudentInformationPresenter sip)
	{
		this.presenter =sip;
	}
	
	private void initialize() {
		setBounds(100, 100, 500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sinu V10.0");
		getContentPane().setLayout(new GridLayout(5, 1, 0, 0));
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		lblAccountInformation = new JLabel("Student information");
		panel_1.add(lblAccountInformation);
		
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel = new JLabel("Identification number:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		textField_firstName = new JTextField();
		textField_firstName.setEditable(false);
		panel.add(textField_firstName);
		textField_firstName.setColumns(10);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblLastName = new JLabel("Group:");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblLastName);
		
		textField_lastName = new JTextField();
		textField_lastName.setEditable(false);
		panel_2.add(textField_lastName);
		textField_lastName.setColumns(10);
		
		panel_5 = new JPanel();
		getContentPane().add(panel_5);
		
		table = new JTable();
		panel_5.add(table);
		
		panel_3 = new JPanel();
		getContentPane().add(panel_3);
		
		btnAddEnrollment = new JButton("Enroll");
		btnAddEnrollment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_3.add(btnAddEnrollment);
		
		btnLeaveSelectedCourses = new JButton("Leave selected courses");
		btnLeaveSelectedCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leaveSelectedCourses();
			}
		});
		panel_3.add(btnLeaveSelectedCourses);
	}

	protected void leaveSelectedCourses() {
		for(String courseId: selectedCourses)
		{
			presenter.leaveCourse(courseId);
		}
	}

	public void setIdentificationNumber(String identificationNumber) {
		textField_firstName.setText(identificationNumber);
	}

	public void setGroup(String groupNumber) {
		textField_lastName.setText(groupNumber);
	}

	public void setEnrollments(List<String> enrollments) {
		// TODO Auto-generated method stub
		
	}

	public void setPresenter(StudentPresenter studentPresenter) {
		presenter = studentPresenter;
	}

	public void display() {
		// TODO Auto-generated method stub
		
	}
}
