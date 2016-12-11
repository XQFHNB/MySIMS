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
 * Created by XQF on 2016/12/10.，删除就只根据学号删
 */
public class DeleteStudentFrame extends MyFrame implements ActionListener {

    private static final String LABEL = "学号";
    private static final String BTN_SEARCH = "获取信息";
    private static final String BTN_DELETE = "删除学生";

    private JTextField textField;
    private SelectTool selectTool;
    private JTable table;

    public DeleteStudentFrame(String titleString, JTable table, String textString) {
        super(titleString);
        selectTool = new SelectTool();
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel(LABEL);
        topPanel.add(label);
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
                String selectBy = "Sno";
//            合成查询语句, 合成之后一定要打印一下进行检验

                String sqlString = "select * from students "
                        + "where " + selectBy + "=" + textField.getText().toString().trim()
                        + " order by Sno ";
                System.out.println("sqlString:" + sqlString);
                JTable table = selectTool.refresh(1, sqlString);
                this.setVisible(false);

                //只能这样传递数据了
                new DeleteStudentFrame("heh", table, textString);
            }
        } else {
            // TODO: 2016/12/10 删除有关这个学生的所有信息

            deleteStudent();

        }
    }

    private void deleteStudent() {
        String sqlString1 = "delete from students where Sno=" + textField.getText().toString().trim();
        String sqlString2 = "delete from sc  where Sno=" + textField.getText().toString().trim();
        Connection connection = DBHelper.getDbHelper().getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sqlString1);
            stmt.execute(sqlString2);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DeleteStudentFrame("ehh", null, "");
    }


}
