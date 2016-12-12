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

import static com.xqf.manager.AddStudentFrame.CANCEL_BTN;
import static com.xqf.manager.AddStudentFrame.SURE_BTN;

/**
 * Created by XQF on 2016/12/10.
 */
public class AddTeacherFrame extends MyFrame implements ActionListener {
    public static final String COURSE_NO = "课    程   号:";
    public static final String TEACHER_SUB = "科          目:";
    public static final String TEACHER_NO = "编          号:";
    public static final String TEACHER_NAME = "姓          名:";
    public static final String TEACHER_CREDIT = "学         分:";

    private AddStudentItem[] items;

    public AddTeacherFrame(String titleString) {
        super(titleString);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(6, 1));

//复用添加学生的代码
        items = new AddStudentItem[5];
        String[] labelStrings = new String[]{
                COURSE_NO, TEACHER_SUB, TEACHER_NO, TEACHER_NAME, TEACHER_CREDIT
        };
        for (int i = 0; i < 5; i++) {
            items[i] = new AddStudentItem(labelStrings[i]);
            container.add(items[i].getPanel());
        }
        JButton btnOk = new JButton(SURE_BTN);
        JButton btnBack = new JButton(CANCEL_BTN);
        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnOk.addActionListener(this);
        btnBack.addActionListener(this);
        btnPanel.add(btnBack);
        btnPanel.add(btnOk);
        container.add(btnPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnString = e.getActionCommand();
        if (btnString.equals(SURE_BTN)) {
            boolean isAllFiled = true;
            String[] s = new String[6];
            String temp;
            for (int i = 0; i < 5; i++) {
                temp = items[i].getTextField().getText().toString().trim();
                if (temp.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "信息不完整，请检查重新填写", "提示", JOptionPane.CLOSED_OPTION);
                    isAllFiled = false;
                    break;
                }
                s[i + 1] = temp;
            }
            if (isAllFiled) {
                Connection connection = DBHelper.getDbHelper().getConnection();
                try {
                    PreparedStatement pstmt = connection.prepareStatement("insert into course values(?,?,?,?,?)");
                    pstmt.setInt(1, Integer.parseInt(s[1]));
                    pstmt.setString(2, s[2]);
                    pstmt.setString(3, s[3]);
                    pstmt.setInt(4, Integer.parseInt(s[4]));
                    pstmt.setInt(5, Integer.parseInt(s[5]));
                    int result = pstmt.executeUpdate();
                    if (result != 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.CLOSED_OPTION);
                    }
                    pstmt.close();
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }


        } else {//点击另一个按钮
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddTeacherFrame("hh");
    }
}
