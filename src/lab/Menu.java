package lab;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Menu extends CommonFrame {
    private Button bAdd, bView, bDelete;

    public Menu() {
        super("Select Option");

        Menu curr = this;

        setBounds(0, 0, 500, 300);

        bAdd = new Button("Add Record"); bView = new Button("View Records");
        bDelete = new Button("Delete Record");

        bAdd.setBounds(200, 70, 100, 40);
        bView.setBounds(200, 130, 100, 40);
        bDelete.setBounds(200, 190, 100, 40);

        add(bAdd); add(bView); add(bDelete);

        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRecordPage(curr);
                curr.setVisible(false);
            }
        });

        bView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRecords();
            }
        });

        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletePage(curr);
                curr.setVisible(false);
            }
        });

        setVisible(true);
    }

    private void viewRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lab", "root", "root"
            )) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from student");
                ResultSetMetaData meta = rs.getMetaData();

                System.out.println("Data:");
                while (rs.next()) {
                    for (int i = 0; i < meta.getColumnCount(); i++) {
                        if (i == 0)
                            System.out.println(meta.getColumnLabel(i + 1) + ": " + rs.getInt(i + 1));
                        else
                            System.out.println(meta.getColumnLabel(i + 1) + ": " + rs.getString(i + 1));
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
