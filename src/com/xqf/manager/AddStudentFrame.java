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
    public static final String STUDENT_NO = "学          号:";
    public static final String STUDENT_NAME = "姓          名:";
    public static final String STUDENT_SEX = "性          别:";
    public static final String STUDENT_BORN = "出生年月:";
    public static final String STUDENT_MAJORNO = "专业号码:";
    public static final String STUDENT_MAJOR = "专业名称:";
    public static final String STUDENT_CLASS = "班          级:";
    public static final String SURE_BTN = "确          定";
    public static final String CANCEL_BTN = "取          消";


    private AddStudentItem[] items;

    public AddStudentFrame(String titleString) {
        super(titleString);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(8, 1));
        items = new AddStudentItem[7];
        String[] labelsString = {STUDENT_NO,
                STUDENT_NAME,
                STUDENT_SEX,
                STUDENT_BORN,
                STUDENT_MAJORNO,
                STUDENT_MAJOR,
                STUDENT_CLASS};


        //把这里改了一下，看上去好很多
        for (int i = 0; i < 7; i++) {
            items[i] = new AddStudentItem(labelsString[i]);
            container.add(items[i].getPanel());
        }

        //最后底部添加两个按钮
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
            boolean isAllFiled = true;
            String[] s = new String[8];
            String temp = null;
            //判断信息填写是不是完整，不完整就弹出对话框并退出，用数组的方式就好判断每个信息点是不是完整
            for (int i = 0; i < 7; i++) {
                temp = items[i].getTextField().getText().toString().trim();
                s[i + 1] = temp;
                if (s[i + 1].isEmpty()) {
                    isAllFiled = false;
                    JOptionPane.showMessageDialog(null, "信息不完整，请检查重新填写", "提示", JOptionPane.CLOSED_OPTION);
                    break;
                }
            }
            if (isAllFiled) {
                String sqlString = "insert into students values(?,?,?,?,?,?,?)";
                Connection connection = DBHelper.getDbHelper().getConnection();
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sqlString);
                    pstmt.setString(1, s[1]);
                    pstmt.setString(2, s[2]);
                    pstmt.setString(3, s[3]);
                    pstmt.setString(4, s[4]);
                    pstmt.setInt(5, Integer.parseInt(s[5]));
                    pstmt.setString(6, s[6]);
                    pstmt.setInt(7, Integer.parseInt(s[7]));
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
        new AddStudentFrame("hh");
    }
}
