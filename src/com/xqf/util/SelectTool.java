package com.xqf.util;

import com.xqf.db.DBHelper;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by XQF on 2016/12/9.
 */
public class SelectTool {
    public JTable refresh(int Num, String sqlString) {
        Connection connection = DBHelper.getDbHelper().getConnection();
        Vector allVector = new Vector();
        Vector tablehead = new Vector();
        if (Num == 1) {
            //表头

            tablehead.add("学号");
            tablehead.add("姓名");
            tablehead.add("性别");
            tablehead.add("出生年月");
            tablehead.add("专业号");
            tablehead.add("专业名称");
            tablehead.add("班级");

            //表的内容

            try {
                Statement stmt = connection.createStatement();
                ResultSet result = stmt.executeQuery(sqlString);

                while (result.next()) {
                    Vector contentVector = new Vector();

                    contentVector.add(result.getString("Sno"));
                    contentVector.add(result.getString("Sname"));
                    contentVector.add(result.getString("Ssex"));
                    contentVector.add(result.getString("Sborn_age"));
                    contentVector.add(result.getInt("Smajor_no"));
                    contentVector.add(result.getString("Smajor"));
                    contentVector.add(result.getInt("Sclass"));
                    allVector.add(contentVector);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // TODO: 2016/12/7 查询老师的有关逻辑
            tablehead.add("课程号");
            tablehead.add("课程名");
            tablehead.add("老师号");
            tablehead.add("老师");
            tablehead.add("学分");


            try {
                Statement stmt1 = connection.createStatement();
                ResultSet result1 = stmt1.executeQuery(sqlString);
                while (result1.next()) {
                    Vector contentVector = new Vector();

                    contentVector.add(result1.getInt("Cno"));
                    contentVector.add(result1.getString("Cname"));
                    contentVector.add(result1.getString("CteacherNo"));
                    contentVector.add(result1.getString("Cteacher"));
                    contentVector.add(result1.getInt("Ccredit"));
                    allVector.add(contentVector);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JTable table = new JTable(allVector, tablehead);
        return table;
    }

}
