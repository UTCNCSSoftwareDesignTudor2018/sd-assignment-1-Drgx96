package com.assig.assig1.userinterface.user;

import com.assig.assig1.userinterface.professor.StudentInformationForProfessorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GradesView extends JFrame {

    private IParentForGradeView parentView;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private String subject;
    private String student;

    public GradesView(IParentForGradeView parentView) {
        this.parentView = parentView;
        this.student="";
        this.subject="";
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 500, 550);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel lblGrades = new JLabel("Grades");
        panel.add(lblGrades);

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);

        if (parentView instanceof StudentInformationForProfessorView) {
            JPanel panel_1 = new JPanel();
            getContentPane().add(panel_1);

            JLabel lblGrade = new JLabel("Grade");
            panel_1.add(lblGrade);

            textField = new JTextField();
            panel_1.add(textField);
            textField.setColumns(10);

            JLabel lblNewLabel = new JLabel("Examination Type");
            panel_1.add(lblNewLabel);

            textField_1 = new JTextField();
            panel_1.add(textField_1);
            textField_1.setColumns(10);

            JButton btnAddGrade = new JButton("Add Grade");
            panel_1.add(btnAddGrade);
            btnAddGrade.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addGrade();
                }
            });
        }
    }

    protected void addGrade() {
        parentView.addGrade(textField.getText(), textField_1.getText());
    }

    public void setSubject(String subject) {
        this.subject = subject;
        setTitle("Grades of " + student + " at the subject " + subject);
    }

    public void setStudent(String student) {
        this.student = student;
        setTitle("Grades of " + student + " at the subject " + subject);
    }

    public void setGrades(List<String[]> grades) {
        DefaultTableModel defaultTableModel = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        defaultTableModel.addColumn("Grade");
        defaultTableModel.addColumn("Examination type");
        for (String[] g : grades) {
            defaultTableModel.addRow(g);
        }
        table.setModel(defaultTableModel);
    }
}
