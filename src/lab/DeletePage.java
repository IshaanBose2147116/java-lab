package lab;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeletePage extends CommonFrame {
    private Label lRegNo;
    private TextField tRegNo;
    private Button bAddToBatch, bExecuteBatch;
    private Menu prevPage;
    private Connection conn;
    private Statement stmt;

    public DeletePage(Menu parent) {
        super("Delete Records");
        this.prevPage = parent;
        setBounds(0, 0, 500, 300);

        lRegNo = new Label("Reg No:");
        tRegNo = new TextField();
        bAddToBatch = new Button("Add to Batch"); bExecuteBatch = new Button("Execute Batch");

        lRegNo.setBounds(100, 100, 60, 20); tRegNo.setBounds(170, 100, 180, 20);
        bAddToBatch.setBounds(180, 140, 100, 30); bExecuteBatch.setBounds(180, 180, 100, 30);

        add(lRegNo); add(tRegNo);
        add(bAddToBatch); add(bExecuteBatch);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab", "root", "root");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }

        bAddToBatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt.addBatch("delete from student where regno=" + tRegNo.getText());
                    System.out.println(tRegNo.getText() + " added to batch!");
                } catch (SQLException err) {
                    System.out.println(err);
                }
            };
        });

        bExecuteBatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt.executeBatch();
                    conn.commit();
                    System.out.println("Batch executed!");
                } catch (SQLException err) {
                    System.out.println(err);
                }
            };
        });

        setVisible(true);
    }

    @Override
    protected void windowClose() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        this.prevPage.setVisible(true);
        super.windowClose();
    }
}
