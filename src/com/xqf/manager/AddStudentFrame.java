package com.xqf.manager;

import com.xqf.basic.MyFrame;
import com.xqf.db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by XQF on 2016/12/7.
 */
public class AddStudentFrame extends MyFrame implements ActionListener {
    public static final String STUDENT_NO = "学    号:";
    public static final String STUDENT_NAME = "姓    名:";
    public static final String STUDENT_SEX = "性    别:";
    public static final String STUDENT_BORN = "出生年月:";
    public static final String STUDENT_MAJORNO = "专业号码:";
    public static final String STUDENT_MAJOR = "专业名称:";
    public static final String STUDENT_CLASS = "班    级:";
    public static final String SURE_BTN = "确定";
    public static final String CANCEL_BTN = "取消";


    AddStudentItem studentNoItem = new AddStudentItem(STUDENT_NO);
    JPanel studentNoPanel = studentNoItem.getPanel();
    JTextField studnetNoText = studentNoItem.getTextField();

    AddStudentItem studentNameItem = new AddStudentItem(STUDENT_NAME);
    JPanel studentNamePanel = studentNameItem.getPanel();
    JTextField studnetNameText = studentNameItem.getTextField();

    AddStudentItem studentSexItem = new AddStudentItem(STUDENT_SEX);
    JPanel studentSexPanel = studentSexItem.getPanel();
    JTextField studnetSexText = studentSexItem.getTextField();

    AddStudentItem studentBornItem = new AddStudentItem(STUDENT_BORN);
    JPanel studentBornPanel = studentBornItem.getPanel();
    JTextField studnetBornText = studentBornItem.getTextField();

    AddStudentItem studentMajorNoItem = new AddStudentItem(STUDENT_MAJORNO);
    JPanel studentMajorNoPanel = studentMajorNoItem.getPanel();
    JTextField studnetMajorNoText = studentMajorNoItem.getTextField();


    AddStudentItem studentMajorItem = new AddStudentItem(STUDENT_MAJOR);
    JPanel studentMajorPanel = studentMajorItem.getPanel();
    JTextField studentMajorText = studentMajorItem.getTextField();


    AddStudentItem studentClassItem = new AddStudentItem(STUDENT_CLASS);
    JPanel studentClassPanel = studentClassItem.getPanel();
    JTextField studnetClassText = studentClassItem.getTextField();

    public AddStudentFrame(String titleString) {
        super(titleString);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(8, 1));


        container.add(studentNoPanel);
        container.add(studentNamePanel);
        container.add(studentSexPanel);
        container.add(studentBornPanel);
        container.add(studentMajorNoPanel);
        container.add(studentMajorPanel);
        container.add(studentClassPanel);

        JButton btnOk = new JButton(SURE_BTN);
        JButton btnBack = new JButton(CANCEL_BTN);
        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnOk.addActionListener(this);
        btnBack.addActionListener(this);
        btnPanel.add(btnBack);
        btnPanel.add(btnOk);
        container.add(btnPanel);

    }

    // TODO: 2016/12/8 测试我的数据是不是已经成功插入
    @Override
    public void actionPerformed(ActionEvent e) {
        String btnString = e.getActionCommand();
        if (btnString.equals(SURE_BTN)) {
//            JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.CLOSED_OPTION);
            String sqlString = "insert into students values(?,?,?,?,?,?,?)";
            Connection connection = DBHelper.getDbHelper().getConnection();
            try {
                PreparedStatement pstmt = connection.prepareStatement(sqlString);
                pstmt.setString(1, studnetNoText.getText().toString().trim());
                pstmt.setString(2, studnetNameText.getText().toString().trim());
                pstmt.setString(3, studnetSexText.getText().toString().trim());
                pstmt.setString(4, studnetBornText.getText().toString().trim());
                pstmt.setInt(5, Integer.parseInt(studnetMajorNoText.getText().toString().trim()));
                pstmt.setString(6, studentMajorText.getText().toString().trim());
                pstmt.setInt(7, Integer.parseInt(studnetClassText.getText().toString().trim()));
                int result = pstmt.executeUpdate();
                if (result != 0) {
                    JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.CLOSED_OPTION);
                }

                pstmt.close();
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddStudentFrame("hh");
    }


}
