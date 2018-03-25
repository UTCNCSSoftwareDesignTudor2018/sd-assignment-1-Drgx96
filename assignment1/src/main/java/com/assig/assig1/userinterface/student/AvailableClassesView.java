package com.assig.assig1.userinterface.student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AvailableClassesView extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 2629871606324701724L;
    private JTable table;
    private StudentInformationView parentView;

    public AvailableClassesView(StudentInformationView parentView) {
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

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AvailableClassesView window = new AvailableClassesView(new StudentInformationView());
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        setBounds(100, 100, 500, 489);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showAvailableClasses(List<String> classes) {
        DefaultTableModel dtp = new DefaultTableModel();
        dtp.addColumn("Subject");
        for (String c : classes) {
            dtp.addRow(new String[]{c});
        }
        table.setModel(dtp);
    }

    public void enrollInSelectedClasses() {
        setVisible(false);
        for (int index : table.getSelectedRows()) {
            parentView.joinCourseAtIndex(index);
        }
    }
}
