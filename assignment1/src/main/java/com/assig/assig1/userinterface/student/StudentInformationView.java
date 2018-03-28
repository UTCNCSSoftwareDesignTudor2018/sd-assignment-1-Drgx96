package com.assig.assig1.userinterface.student;

import com.assig.assig1.userinterface.user.IParentForGradeView;
import com.assig.assig1.presenters.IStudentInformationView;
import com.assig.assig1.presenters.StudentPresenter;
import com.assig.assig1.userinterface.user.GradesView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class StudentInformationView extends JFrame implements IStudentInformationView, IParentForGradeView {
    private static final long serialVersionUID = 5270705953458728655L;
    private IStudentInformationPresenter presenter;
    private AvailableClassesView classesView;
    private JLabel lblNewLabel;
    private JLabel lblLastName;
    private JPanel panel;
    private JPanel panel_2;
    private JPanel panel_3;
    private JTable table;
    private JTextField textField_lastName;
    private JTextField textField_firstName;
    private JButton btnAddEnrollment;
    private JButton btnLeaveSelectedCourses;
    private JButton btnGrades;
    private JScrollPane scrollPane;
    private GradesView gradeView;

    public StudentInformationView() {
        initialize();
        classesView = new AvailableClassesView(this);
        gradeView = new GradesView(this);
    }

    public void setPresenter(IStudentInformationPresenter sip) {
        this.presenter = sip;
    }

    private void initialize() {
        setBounds(100, 100, 500, 538);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Sinu V10.0 - Student Information");
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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
        panel_2.setLayout(new GridLayout(0, 2, 0, 0));
        getContentPane().add(panel_2);

        lblLastName = new JLabel("Group:");
        lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblLastName);

        textField_lastName = new JTextField();
        textField_lastName.setEditable(false);
        textField_lastName.setColumns(10);
        panel_2.add(textField_lastName);

        scrollPane = new JScrollPane();
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "New column"
                }
        ));
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(table);

        panel_3 = new JPanel();
        getContentPane().add(panel_3);

        btnAddEnrollment = new JButton("Enroll");
        panel_3.add(btnAddEnrollment);

        btnGrades = new JButton("Grades");
        panel_3.add(btnGrades);

        btnLeaveSelectedCourses = new JButton("Leave selected courses");
        panel_3.add(btnLeaveSelectedCourses);
    }

    protected void leaveSelectedCourses() {
        presenter.leaveCoursesWithIndexes(Arrays.stream(table.getSelectedRows()).boxed().toArray(Integer[]::new));
    }

    public void setIdentificationNumber(String identificationNumber) {
        textField_firstName.setText(identificationNumber);
    }

    public void setGroup(String groupNumber) {
        textField_lastName.setText(groupNumber);
    }

    public void showEnrollments(List<String> subjects) {
        DefaultTableModel defaultModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        defaultModel.addColumn("Subject");
        for (int i = 0; i < subjects.size(); i++) {
            defaultModel.addRow(new String[]{subjects.get(i)});
        }
        table.setModel(defaultModel);
    }

    public void setPresenter(StudentPresenter studentPresenter) {
        presenter = studentPresenter;
        btnLeaveSelectedCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leaveSelectedCourses();
            }
        });
        btnGrades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGrades();
            }
        });
        btnAddEnrollment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAvailableClasses();
            }
        });
    }

    protected void showAvailableClasses() {
        List<String> classes = presenter.getAvailableClasses();
        classesView.showAvailableClasses(classes);
        classesView.setVisible(true);
    }

    protected void showGrades() {
        int row = table.getSelectionModel().getMinSelectionIndex();
        gradeView.setGrades(presenter.getGradesForClassAtIndex(row));
        gradeView.setStudent(presenter.getStudent());
        gradeView.setSubject((String) table.getModel().getValueAt(row, 0));
        gradeView.setVisible(true);
    }

    public void display() {
        setVisible(true);
    }


    public void joinCourseAtIndex(int index) {
        presenter.joinClassAtIndex(index);
    }

    @Override
    public void dontDisplay() {
        setVisible(false);
        classesView.setVisible(false);
        gradeView.setVisible(false);
    }

    @Override
    public void addGrade(String grade, String examination) {
    }
}
