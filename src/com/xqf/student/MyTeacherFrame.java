package com.xqf.student;

import com.xqf.basic.MyFrame;
import com.xqf.config.Config;
import com.xqf.db.DBHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


/**
 * Created by XQF on 2016/12/8.
 */
class EvenOddRenderer implements TableCellRenderer {

    public static final DefaultTableCellRenderer DEFAULT_RENDERER =
            new DefaultTableCellRenderer();

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer =
                DEFAULT_RENDERER.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
        Color foreground, background;

//        if (row == 0 && column == 2) {
//            foreground = Color.red;
//            background = Color.BLUE;
//        } else {
//            foreground = Color.BLACK;
//            background = Color.WHITE;
//        }
        if (column == 3) {
            foreground = Color.red;
            background = Color.BLUE;
        } else {
            foreground = Color.BLACK;
            background = Color.WHITE;
        }
        renderer.setForeground(foreground);
        renderer.setBackground(background);
        return renderer;
    }
}

public class MyTeacherFrame extends MyFrame {
    Container container;
    private String studentNo;

    public MyTeacherFrame(String titleString, String studentNo) {
        super(titleString);
        this.studentNo = studentNo;
        container = this.getContentPane();
        JTable table = getContentTable();
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);

        container.add(scrollPane);


    }


    public JTable getContentTable() {
        Connection connection = DBHelper.getDbHelper().getConnection();
        String sqlCreateViewString = "create view helpView as  "
                + "select sc.term,course.*,sc.score "
                + "from course,sc "
                + "where sc.studentNo=" + studentNo + " "
                + "and sc.courseNo=course.Cno "
                + "order by sc.term";

        Vector allVector = new Vector();

        Vector tablehead = new Vector();
        tablehead.add("学期");
        tablehead.add("课程号");
        tablehead.add("课程名");
        tablehead.add("老师");
        tablehead.add("学分");
        tablehead.add("分数");
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(Config.DROP_VIEW);
            stmt.execute(sqlCreateViewString);
            ResultSet result = stmt.executeQuery(Config.QUERY_VIEW);
            while (result.next()) {
                Vector contentVector = new Vector();
                contentVector.add(result.getInt("term"));
                contentVector.add(result.getInt("Cno"));
                contentVector.add(result.getString("Cname"));
                contentVector.add(result.getString("Cteacher"));
                contentVector.add(result.getInt("Ccredit"));
                contentVector.add(result.getInt("score"));
                allVector.add(contentVector);
            }
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JTable table = new JTable(allVector, tablehead);
        table.setDefaultRenderer(Object.class, new EvenOddRenderer());
        return table;
    }

    public static void main(String[] args) {
        new MyTeacherFrame("heh", "1400000");
    }
}
