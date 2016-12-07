package com.xqf.basic;

import com.xqf.db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by XQF on 2016/12/7.
 */
public class Super extends MyFrame implements ActionListener {
    private static final String ADD_STUDENT_BTN = "添加学生";
    private static final String ADD_TEACHER_BTN = "添加老师";
    private static final String STUDENT_INFO_LABEL = "学生信息";
    private static final String TEACHER_INFO_LABEL = "老师信息";
    private static final String DELETE_STUDENT = "删除学生'";
    private static final String DELETE_TEACHER = "删除老师";
    private static final String SEARCH_STUDENT = "查询学生";
    private static final String SEARCH_TEACHER = "查询老师";


    private static final String REFRESH = "刷新";


    public Super(String titleString) {
        super(titleString);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 1));
        JPanel studentPanel = newPanel(1, ADD_STUDENT_BTN, STUDENT_INFO_LABEL, DELETE_STUDENT, SEARCH_STUDENT);
        JPanel teacherPanel = newPanel(2, ADD_TEACHER_BTN, TEACHER_INFO_LABEL, DELETE_TEACHER, SEARCH_TEACHER);
        container.add(studentPanel);
        container.add(teacherPanel);

    }

    private JPanel newPanel(int Num, String addBtnString, String labelString, String deleteBtnString, String searchBtnString) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel labelAndInfoPanel = new JPanel(new BorderLayout());

        JLabel labelInfo = new JLabel(labelString);
        labelAndInfoPanel.add(labelInfo, BorderLayout.NORTH);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel panelInfo = new JPanel(new BorderLayout());
        String infoContent = refresh(Num);
        JTextArea info = new JTextArea(infoContent);
        info.setEnabled(false);
        // TODO: 2016/12/7 解决info的数据信息填充
        panelInfo.add(info);
        scrollPane1.setViewportView(panelInfo);


        JPanel btnPanel = new JPanel(new GridLayout(4, 1));

        JButton addBtn = new JButton(addBtnString);
        JButton refreshBtn = new JButton(REFRESH);
        JButton deleteBtn = new JButton(deleteBtnString);
        JButton searchBtn = new JButton(searchBtnString);
        addBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(refreshBtn);

        labelAndInfoPanel.add(scrollPane1, BorderLayout.CENTER);
        panel.add(labelAndInfoPanel, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.EAST);
        return panel;
    }

    private String refresh(int Num) {
        String resultString = null;
        StringBuffer sb = new StringBuffer();
        Connection connection = DBHelper.getDbHelper().getConnection();
        if (Num == 1) {

            String sqlQueryStudentsString = "select * from students";
            try {
                Statement stmt = connection.createStatement();
                ResultSet result = stmt.executeQuery(sqlQueryStudentsString);

                while (result.next()) {
                    sb.append(result.getString("Sno") + " "
                            + result.getString("Sname") + " "
                            + result.getString("Ssex") + " "
                            + result.getString("Sborn_age") + " "
                            + result.getInt("Smajor_no") + " "
                            + result.getString("Smajor") + " "
                            + result.getInt("Sclass") + "\n");
                }
                System.out.println(sb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultString = sb.toString();
        } else {
            // TODO: 2016/12/7 查询老师的有关逻辑
            String sqlQueryTeachersString = "select * from course";
            try {
                Statement stmt1 = connection.createStatement();
                ResultSet result1 = stmt1.executeQuery(sqlQueryTeachersString);
                while (result1.next()) {
                    sb.append(result1.getInt("Cmajor") + " "
                            + result1.getInt("Cno") + " "
                            + result1.getString("Cname") + " "
                            + result1.getString("CteacherName") + " "
                            + result1.getInt("Ccredit") + " "
                            + result1.getInt("Cnature") + "\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultString = sb.toString();
        }
        return resultString;
    }

    public static void main(String[] args) {
        new Super("heh");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnString = e.getActionCommand();
        if (btnString.equals(ADD_STUDENT_BTN)) {
            new AddStudentFrame("添加学生");
        }
        if (btnString.equals(SEARCH_STUDENT)) {
            new SearchStudentFrame("查询学生");
        }
        if (btnString.equals(DELETE_STUDENT)){
            // TODO: 2016/12/7 删除一个学生不只是从这里的基本信息表中删除，而是还要删除其他表中的内容，这就涉及到一些查询语句了 
        }
        if(btnString.equals(ADD_TEACHER_BTN)){
            // TODO: 2016/12/7 添加老师也是添加课程
        }
        if(btnString.equals(DELETE_TEACHER)){
            
        }
        if (btnString.equals(SEARCH_TEACHER)){
            
        }
        if (btnString.equals(REFRESH)) {
            // TODO: 2016/12/7 点击刷新按钮会刷新两个界面的内容，因此两个调用refresh方法
            refresh(1);
            refresh(2);
        }
    }
}