package com.xqf.manager;

import com.xqf.basic.MyFrame;
import com.xqf.util.SelectTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by XQF on 2016/12/7.
 */
public class SearchStudentFrame extends MyFrame {

    public static final String BY_NUMBER = "按学号";
    public static final String BY_NAME = "按姓名";
    public static final String BY_MAJOR = "按专业";
    public static final String SEARCH = "查询";

    public JComboBox jcb;
    public JTextField textField;
    public JButton searchBtn;
    public SelectTool selectTool;

    public SearchStudentFrame(String titleString, String textString) {
        super(titleString);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        textField = new JTextField(20);
        jcb = new JComboBox();
        jcb.addItem(BY_NUMBER);
        jcb.addItem(BY_NAME);
        jcb.addItem(BY_MAJOR);
        topPanel.add(jcb);
        topPanel.add(textField);
        textField.setText(textString);


        searchBtn = new JButton(SEARCH);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new SearchStudentFrame("查询学生");
                new SearchStudentFrame("heh", textField.getText().toString().trim());
            }
        });
        topPanel.add(searchBtn);
        container.add(topPanel, BorderLayout.NORTH);


        if (!textField.getText().toString().trim().isEmpty()) {
            String jcbString = jcb.getSelectedItem().toString();
//            获取查询依据，是学号还是专业
            String selectBy;
            if (jcbString.equals(BY_NUMBER)) {
                selectBy = "Sno";
            } else if (jcbString.equals(BY_NAME)) {
                selectBy = "Sname";
            } else {
                selectBy = "Smajor";
            }
//            合成查询语句, 合成之后一定要打印一下进行检验
            String sqlString = "select * from students "
                    + "where " + selectBy + "=" + textString
                    + " order by Sno ";
            System.out.println("sqlString:" + sqlString);
            JTable table = selectTool.refresh(1, sqlString);
            JScrollPane scrollPane = new JScrollPane(table);
            container.add(scrollPane);
        }

    }


    public static void main(String[] args) {
        new SearchStudentFrame("查询学生", "");
    }
}
