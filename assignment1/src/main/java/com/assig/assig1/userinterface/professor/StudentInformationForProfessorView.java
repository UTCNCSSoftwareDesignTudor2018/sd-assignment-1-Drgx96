package com.assig.assig1.userinterface.professor;

import com.assig.assig1.presenters.IStudentInfoForProfessorView;
import com.assig.assig1.userinterface.user.GradesView;
import com.assig.assig1.userinterface.user.IParentForGradeView;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

public class StudentInformationForProfessorView extends JFrame implements IStudentInfoForProfessorView, IParentForGradeView {
    private static final long serialVersionUID = 2134869034430296379L;
    private GradesView gradeView;
    private JTextField studentName;
    private JTextField group;
    private JTextField identificationNumber;
    private JTable table;
    private IProfessorPresenter presenter;

    public StudentInformationForProfessorView() {
        setTitle("Student information - ");
        this.gradeView = new GradesView(this);
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 465, 529);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

    public void showGradesForClassAtIndex(int row) {
        gradeView.setGrades(presenter.getGradesForClassAtIndex(row));
        gradeView.setStudent(studentName.getText());
        gradeView.setSubject((String) table.getModel().getValueAt(row,0));
        gradeView.setVisible(true);
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
        gradeView.setVisible(false);
        setVisible(false);
    }

    @Override
    public void setJoinedCourses(List<String> courses) {
        DefaultTableModel dtm = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtm.addColumn("Subject");
        for (String s : courses) {
            dtm.addRow(new String[]{s});
        }
        table.setModel(dtm);
    }

    @Override
    public void addGrade(String grade, String examination) {
        presenter.addGrade(grade, examination);
    }
}
