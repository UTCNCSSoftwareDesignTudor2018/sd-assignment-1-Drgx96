package com.assig.assig1.userinterface.professor;

import com.assig.assig1.presenters.IStudentInfoForProfessorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class StudentInformationForProfessorView extends JFrame implements IStudentInfoForProfessorView {
    private static final long serialVersionUID = 2134869034430296379L;
    private JTextField studentName;
    private JTextField group;
    private JTextField identificationNumber;
    private JTable table;
    private IProfessorPresenter presenter;

    public StudentInformationForProfessorView() {
        setTitle("Student information - ");
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentInformationForProfessorView window = new StudentInformationForProfessorView();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        setBounds(100, 100, 465, 529);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel lblStudent = new JLabel("Student: ");
        panel.add(lblStudent);

        studentName = new JTextField();
        studentName.setEditable(false);
        panel.add(studentName);
        studentName.setColumns(10);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1);

        JLabel lblNewLabel = new JLabel("Group");
        panel_1.add(lblNewLabel);

        group = new JTextField();
        panel_1.add(group);
        group.setColumns(10);

        JPanel panel_2 = new JPanel();
        getContentPane().add(panel_2);

        JLabel identificatioNumner = new JLabel("Identification number");
        panel_2.add(identificatioNumner);

        identificationNumber = new JTextField();
        panel_2.add(identificationNumber);
        identificationNumber.setColumns(10);

        JPanel panel_3 = new JPanel();
        getContentPane().add(panel_3);
        panel_3.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_1 = new JLabel("Classes the student attends");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(lblNewLabel_1, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        panel_3.add(scrollPane, BorderLayout.SOUTH);

        table = new JTable();
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2) {
                    showGradesForClassAtIndex(row);
                }
            }
        });
        
        JPanel panel_4 = new JPanel();
        getContentPane().add(panel_4);
    }

    protected void showGradesForClassAtIndex(int row) {
    	presenter.showGradesForClassAtIndex( row);
	}

	@Override
    public void setPresenter(IProfessorPresenter professorPresenter) {
        presenter = professorPresenter;
    }

    @Override
    public void setStudentName(String s) {
        studentName.setText(s);
        setTitle("Student information - "+s);
    }

    @Override
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber.setText(identificationNumber);
    }

    @Override
    public void setGroup(String groupNumber) {
        this.group.setText(groupNumber);
    }

    @Override
    public void display() {
        setVisible(true);
    }

    @Override
    public void dontDisplay() {
        setVisible(false);
    }

    @Override
    public void setJoinedCourses(List<String> courses) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Subject");
        for (String s : courses) {
            dtm.addRow(new String[]{s});
        }
        table.setModel(dtm);
    }
}
