package com.xqf.teacher;

import com.xqf.basic.MyFrame;
import com.xqf.config.Config;
import com.xqf.db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by XQF on 2016/12/8.
 * 老师的界面也是根据不同的老师来的，策略是显示出老师教过的所有学生，随意改，在退出的时候刷新数据库
 */
public class TeacherFrame extends MyFrame {

    private static final String LABEL_STRING = "您的学生的信息";
    private static final String BTN_STRING = "确定";
    private Container container;
    private String teacherNo;

    public TeacherFrame(String titleString, String teacherNo) {
        super(titleString);
        this.teacherNo = teacherNo;
        container = this.getContentPane();

        //上半部分为学生信息滑动窗口，底部为确定按钮
        container.setLayout(new BorderLayout());

//        放一个标签
        JLabel label = new JLabel(LABEL_STRING);
        container.add(label, BorderLayout.NORTH);

//内容部分放一个滑动窗口，
        JTable table = getContentTable();
        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.CENTER);

//        底部的确定按钮
        JButton btnOK = new JButton(BTN_STRING);
        container.add(btnOK, BorderLayout.SOUTH);
        // TODO: 2016/12/8 点击确定按钮后将修改后的数据更新到数据库

    }

    private JTable getContentTable() {
        Connection connection = DBHelper.getDbHelper().getConnection();
        //放三个语句，删除视图，创建视图，查询
        String sqlCreateViewString = "create view helpView as "
                + "select students.Sno,students.Sname,sc.score "
                + "from sc,course,students "
                + "where course.Cno=sc.courseNo "
                + "and sc.studentNo=students.Sno "
                + "and course.CteacherNo=" + teacherNo;
        Vector allVector = new Vector();

        Vector tablehead = new Vector();
        tablehead.add("学号");
        tablehead.add("姓名");
        tablehead.add("成绩");
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(Config.DROP_VIEW);
            stmt.execute(sqlCreateViewString);
            ResultSet resultSet = stmt.executeQuery(Config.QUERY_VIEW);
            while (resultSet.next()) {
                Vector contenVector = new Vector();
                contenVector.add(resultSet.getString("Sno"));
                contenVector.add(resultSet.getString("Sname"));
                contenVector.add(resultSet.getInt("score"));
                allVector.add(contenVector);
            }
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable table = new JTable(allVector, tablehead);
        return table;
    }


    public static void main(String[] args) {
        new TeacherFrame("ehhe", "1");
    }
}
