package com.assig.assig1.userinterface.student;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.List;

class AvailableClassesView extends JFrame {

    private static final long serialVersionUID = 2629871606324701724L;
    private JTable table;
    private StudentInformationView parentView;

    AvailableClassesView(StudentInformationView parentView) {
        this.parentView = parentView;
        setTitle("Sinu V10.0 - Classes you can join");
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setFillsViewportHeight(true);

        scrollPane.setViewportView(table);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        final JButton btnNewButton = new JButton("Enroll");
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                enrollInSelectedClasses();
            }

        });
        initialize();

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent arg0) {
                if (arg0.getFirstIndex() == arg0.getLastIndex() && arg0.getLastIndex() == 0) {
                    btnNewButton.setEnabled(false);
                } else {
                    btnNewButton.setEnabled(true);
                }
            }

        });
    }

    private void initialize() {
        setBounds(100, 100, 500, 510);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    void showAvailableClasses(List<String> classes) {
        DefaultTableModel dtp = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtp.addColumn("Subject");
        for (String c : classes) {
            dtp.addRow(new String[]{c});
        }
        table.setModel(dtp);
    }

    private void enrollInSelectedClasses() {
        setVisible(false);
        for (int index : table.getSelectedRows()) {
            parentView.joinCourseAtIndex(index);
        }
    }
}
