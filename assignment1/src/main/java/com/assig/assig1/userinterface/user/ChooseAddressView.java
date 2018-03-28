package com.assig.assig1.userinterface.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseAddressView extends JFrame {

    private static final long serialVersionUID = 6172540968549250545L;
    private UserAccountInformationView parentView;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;

    public ChooseAddressView(UserAccountInformationView parentView) {
        this.parentView = parentView;
        setTitle("Set address");
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 463, 315);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(9, 1, 0, 0));

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel lblAddress = new JLabel("Address");
        panel.add(lblAddress);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1);
        panel_1.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblNewLabel = new JLabel("Country");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(lblNewLabel);

        textField = new JTextField();
        panel_1.add(textField);
        textField.setColumns(10);

        JPanel panel_2 = new JPanel();
        getContentPane().add(panel_2);
        panel_2.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblCity = new JLabel("City");
        lblCity.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblCity);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        panel_2.add(textField_1);

        JPanel panel_3 = new JPanel();
        getContentPane().add(panel_3);
        panel_3.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblStrre = new JLabel("Street");
        lblStrre.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(lblStrre);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        panel_3.add(textField_2);

        JPanel panel_4 = new JPanel();
        getContentPane().add(panel_4);
        panel_4.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblNumber = new JLabel("Number");
        lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
        panel_4.add(lblNumber);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        panel_4.add(textField_3);

        JPanel panel_5 = new JPanel();
        getContentPane().add(panel_5);
        panel_5.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblBuildingNumber = new JLabel("Building Number");
        lblBuildingNumber.setHorizontalAlignment(SwingConstants.CENTER);
        panel_5.add(lblBuildingNumber);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        panel_5.add(textField_4);

        JPanel panel_6 = new JPanel();
        getContentPane().add(panel_6);
        panel_6.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblFloor = new JLabel("Floor");
        lblFloor.setHorizontalAlignment(SwingConstants.CENTER);
        panel_6.add(lblFloor);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        panel_6.add(textField_5);

        JPanel panel_7 = new JPanel();
        getContentPane().add(panel_7);
        panel_7.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblDoorNumber = new JLabel("Door Number");
        lblDoorNumber.setHorizontalAlignment(SwingConstants.CENTER);
        panel_7.add(lblDoorNumber);

        textField_6 = new JTextField();
        textField_6.setColumns(10);
        panel_7.add(textField_6);

        JPanel panel_8 = new JPanel();
        getContentPane().add(panel_8);

        JButton btnSave = new JButton("Set");
        panel_8.add(btnSave);
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                parentView.setAddress(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText(), textField_6.getText());
                setVisible(false);
            }

        });
    }

}
