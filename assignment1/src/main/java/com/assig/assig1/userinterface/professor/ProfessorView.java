package com.assig.assig1.userinterface.professor;

import com.assig.assig1.presenters.IProfessorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfessorView extends JFrame implements IProfessorView {

    private static final long serialVersionUID = 3253044117768006799L;
    private ChooseStudentView chooseStudentView;
    private IProfessorPresenter presenter;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JLabel lblNewLabel;
    private JPanel panel;
    private JLabel lblNewLabel_1;
    private JButton btnNewButton_3;

    public ProfessorView() {
        chooseStudentView = new ChooseStudentView(this);
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

        btnNewButton = new JButton("Account Information");
        getContentPane().add(btnNewButton);

        btnNewButton_1 = new JButton("Edit Student Information");
        getContentPane().add(btnNewButton_1);

        btnNewButton_2 = new JButton("Make a report");
        getContentPane().add(btnNewButton_2);

        btnNewButton_3 = new JButton("Log Out");
        getContentPane().add(btnNewButton_3);
    }

    public void setPresenter(IProfessorPresenter p) {
        presenter = p;
        lblNewLabel_1.setText(presenter.getUsername());
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presenter.presentAccountInformation();
            }
        });
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showChooseStudentView();
            }
        });
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presenter.presentReportsInformation();
            }
        });
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presenter.logOut();
            }
        });
    }

    protected void showChooseStudentView() {
        chooseStudentView.setStudents(presenter.getStudents());
        chooseStudentView.setVisible(true);
    }

    public void dontDisplay() {
        chooseStudentView.setVisible(false);
        setVisible(false);
    }

    public void display() {
        setVisible(true);
    }

    public void showInfoForUserAtIndex(int row) {
        presenter.presentInfoForStudentAtIndex(row);
        chooseStudentView.setVisible(false);
    }
}
