package com.assig.assig1.userinterface.professor;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

public class ChooseStudentView extends JFrame {

    private static final long serialVersionUID = 6172540968549250545L;
    private ProfessorView parentView;
    private JTable table;

    public ChooseStudentView(ProfessorView parentView) {
        this.parentView = parentView;
        setTitle("Choose a student");
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 485, 475);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel lblAddress = new JLabel("Double click a students information");
        panel.add(lblAddress);

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2) {
                    showInfoForUserAtIndex(row);
                }
            }
        });
    }

    protected void showInfoForUserAtIndex(int row) {
        parentView.showInfoForUserAtIndex(row);
    }

    public void setStudents(List<String[]> students) {
        DefaultTableModel dtm = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtm.addColumn("First Name");
        dtm.addColumn("Last Name");
        dtm.addColumn("Identification number");
        dtm.addColumn("Group Number");
        dtm.addColumn("Phone");
        dtm.addColumn("Email");
        for (String[] s : students) {
            dtm.addRow(s);
        }
        table.setModel(dtm);
    }

}
