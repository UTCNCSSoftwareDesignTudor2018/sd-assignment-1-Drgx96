package com.assig.assig1.userinterface.user;

import com.assig.assig1.presenters.ILoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInView extends JFrame implements ILoginView {

    private static final long serialVersionUID = 3265856705678846591L;
    private JTextField txtParolastandard_1;
    private JTextField txtParolastandard;
    private JLabel lblUsername;
    private JLabel lblPleaseInputYour;
    private JPanel panel;
    private JLabel lblNewLabel_1;
    private JPanel panel_1;
    private ILoginPresenter presenter;

    public LogInView() {
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogInView window = new LogInView();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        setBounds(100, 100, 500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
        setTitle("Sinu V10.0");

        lblNewLabel_1 = new JLabel("Welcome to a better SINU!");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel_1);

        panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(2, 0, 0, 0));

        lblPleaseInputYour = new JLabel("Please input your credentials:");
        lblPleaseInputYour.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblPleaseInputYour);

        panel_1 = new JPanel();
        panel.add(panel_1);

        lblUsername = new JLabel("Username:");
        panel_1.add(lblUsername);

        txtParolastandard = new JTextField();
        txtParolastandard.setText("Dulsea");
        panel_1.add(txtParolastandard);
        txtParolastandard.setColumns(10);

        JLabel lblNewLabel = new JLabel("Password:");
        panel_1.add(lblNewLabel);

        txtParolastandard_1 = new JTextField();
        txtParolastandard_1.setText("parolastandard");
        panel_1.add(txtParolastandard_1);
        txtParolastandard_1.setColumns(10);

        JButton btnNewButton = new JButton("Log In");
        panel_1.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInActionPerformed(evt);
            }
        });
    }

    private void logInActionPerformed(ActionEvent event) {
        presenter.logIn(txtParolastandard.getText(), txtParolastandard_1.getText());
    }

    public void showInvalidPassword() {
        JOptionPane.showMessageDialog(this, "The entered password is invalid.");
    }

    public void showInvalidUsername() {
        JOptionPane.showMessageDialog(this, "The entered username is invalid.");
    }

    public void setPresenter(ILoginPresenter lp) {
        this.presenter = lp;
    }

    public void display() {
        this.setVisible(true);
    }

    public void showInvalidCredentials() {
        // TODO Auto-generated method stub

    }

    public void dontDisplay() {
        this.setVisible(false);
    }
}
