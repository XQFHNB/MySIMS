package com.xqf.manager;

import com.xqf.basic.MyFrame;
import com.xqf.util.SelectTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by XQF on 2016/12/12.
 */
public class SearchTeacherFrame extends MyFrame implements ActionListener {
    private static final String BY_NUMBER = "按编号";
    private static final String BY_NAME = "按姓名";
    private static final String BY_COURSENAME = "按课程";
    private static final String BY_COURSENO = "按课程号";
    private static final String SEARCH = "查询";


    private JComboBox jcb;
    private JTextField textField;
    private JButton searchBtn;
    private SelectTool selectTool;

    public SearchTeacherFrame(String titleString, JTable table, String textString) {
        super(titleString);
        selectTool = new SelectTool();

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        textField = new JTextField(20);
        textField.setText(textString);

//        多选框的配置
        jcb = new JComboBox();
        jcb.addItem(BY_NUMBER);
        jcb.addItem(BY_COURSENAME);
        jcb.addItem(BY_COURSENO);
        jcb.addItem(BY_NAME);


        topPanel.add(jcb);
        topPanel.add(textField);


        searchBtn = new JButton(SEARCH);
        searchBtn.addActionListener(this);

        topPanel.add(searchBtn);
        if (!textString.isEmpty()) {
            container.add(table);
        }
        container.add(topPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textString = textField.getText().toString().trim();
        String selectString = "'" + textString + "'";

        if (!textString.isEmpty()) {
            String jcbString = jcb.getSelectedItem().toString();
            String selectBy;
            if (jcbString.equals(BY_NUMBER)) {
                selectBy = "CteacherNo";
            } else if (jcbString.equals(BY_NAME)) {
                selectBy = "Cteacher";
            } else if (jcbString.equals(BY_COURSENAME)) {
                selectBy = "Cname";
            } else {
                selectBy = "Cno";
            }
//            合成查询语句, 合成之后一定要打印一下进行检验

            String sqlString = "select * from course "
                    + "where " + selectBy + "=" + selectString
                    + " order by Cno ";
            System.out.println("sqlString:" + sqlString);
            JTable table = selectTool.refresh(2, sqlString);
            this.setVisible(false);

            //只能这样传递数据了
            new SearchTeacherFrame("heh", table, textString);
        }
    }


    public static void main(String[] args) {
        new SearchTeacherFrame("dd", null, "");
    }
}
