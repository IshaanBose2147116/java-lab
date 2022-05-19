package lab;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddRecordPage extends CommonFrame {
    private Menu prevPage;
    private Label lName, lRegNo, lCourse;
    private TextField tName, tRegNo, tCourse;
    private Button bAdd;

    public AddRecordPage(Menu parent) {
        super("Add Record");

        this.prevPage = parent;
        setBounds(0, 0, 500, 300);

        lName = new Label("Name:"); lRegNo = new Label("Reg No:"); lCourse = new Label("Course:");
        tName = new TextField(); tRegNo = new TextField(); tCourse = new TextField();
        bAdd = new Button("Add Record");

        lName.setBounds(100, 60, 60, 20); tName.setBounds(170, 60, 180, 20);
        lRegNo.setBounds(100, 100, 60, 20); tRegNo.setBounds(170, 100, 180, 20);
        lCourse.setBounds(100, 140, 60, 20); tCourse.setBounds(170, 140, 180, 20);
        bAdd.setBounds(180, 180, 100, 30);

        add(lName); add(lRegNo); add(lCourse);
        add(tName); add(tRegNo); add(tCourse);
        add(bAdd);

        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecords();
            }
            
        });

        setVisible(true);
    }

    @Override
    protected void windowClose() {
        this.prevPage.setVisible(true);
        super.windowClose();
    }

    private void addRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lab", "root", "root"
            )) {
                PreparedStatement stmt = conn.prepareStatement("insert into student values (?, ?, ?)");
                stmt.setInt(1, Integer.parseInt(tRegNo.getText()));
                stmt.setString(2, tName.getText());
                stmt.setString(3, tCourse.getText());

                stmt.execute();
            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
