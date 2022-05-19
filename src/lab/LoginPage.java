package lab;

import java.awt.*;
import java.awt.event.*;

public class LoginPage extends CommonFrame {
    private TextField tUsername, tPassword;
    private Label lUsername, lPassword;
    private Button bLogin;

    public LoginPage() {
        super("Login");

        bLogin = new Button("Login");
        lUsername = new Label("Username:"); lPassword = new Label("Password:");
        tUsername = new TextField(); tPassword = new TextField();

        setBounds(0, 0, 500, 300);

        lUsername.setBounds(100, 100, 60, 20); lPassword.setBounds(100, 140, 60, 20);
        tUsername.setBounds(220, 100, 120, 20); tPassword.setBounds(220, 140, 120, 20);
        bLogin.setBounds(160, 180, 100, 20);
        
        add(lUsername); add(lPassword);
        add(tUsername); add(tPassword);
        add(bLogin);

        setVisible(true);

        LoginPage curr = this;

        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tUsername.getText().equals("admin") && tPassword.getText().equals("password123")) {
                    new Menu();
                    curr.dispose();
                } else {
                    System.out.println("no");
                }
            }
        });
    }
}
