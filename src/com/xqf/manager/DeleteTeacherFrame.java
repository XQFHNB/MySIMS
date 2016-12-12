package com.xqf.manager;

import com.xqf.basic.MyFrame;
import com.xqf.db.DBHelper;
import com.xqf.util.SelectTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by XQF on 2016/12/12.
 */
public class DeleteTeacherFrame extends MyFrame implements ActionListener {
    private static final String BTN_LABEL = "教师编号";
    private static final String BTN_SEARCH = "获取信息";
    private static final String BTN_DELETE = "删除老师";

    private JTextField textField;
    private SelectTool selectTool;
    private JTable table;

    public DeleteTeacherFrame(String titleString, JTable table, String textString) {
        super(titleString);
        selectTool = new SelectTool();
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
//        JLabel label = new JLabel(LABEL);
        JButton labelBtn = new JButton(BTN_LABEL);
        topPanel.add(labelBtn);

        textField = new JTextField(20);
        if (!textString.isEmpty()) {
            textField.setText(textString);
            this.table = table;
            container.add(table);
            JButton deleteBtn = new JButton(BTN_DELETE);
            deleteBtn.addActionListener(this);
            container.add(deleteBtn, BorderLayout.SOUTH);
        }
        topPanel.add(textField);

        JButton searchBtn = new JButton(BTN_SEARCH);
        topPanel.add(searchBtn);
        searchBtn.addActionListener(this);

        container.add(topPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnString = e.getActionCommand().toString();
        if (btnString.equals(BTN_SEARCH)) {
            String textString = textField.getText().toString().trim();
            if (!textString.isEmpty()) {
                String selectBy = "CteacherNo";
//            合成查询语句, 合成之后一定要打印一下进行检验

                String sqlString = "select * from course "
                        + "where " + selectBy + "=" + textField.getText().toString().trim()
                        + " order by Cno ";
                System.out.println("sqlString:" + sqlString);
                JTable table = selectTool.refresh(2, sqlString);
                this.setVisible(false);

                //只能这样传递数据了
                new DeleteTeacherFrame("heh", table, textString);
            }
        } else {
            // TODO: 2016/12/10 删除有关这个学生的所有信息

            deleteTeacher();

        }
    }

    private void deleteTeacher() {
        String sqlString1 = "delete from course where CteacherNo=" + textField.getText().toString().trim();
        Connection connection = DBHelper.getDbHelper().getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sqlString1);
            JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.CLOSED_OPTION);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DeleteTeacherFrame("ejj", null, "");
    }
}
