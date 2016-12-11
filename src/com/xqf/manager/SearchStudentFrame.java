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

//搞死人了，。，。，
public class SearchStudentFrame extends MyFrame implements ActionListener {

    public static final String BY_NUMBER = "按学号";
    public static final String BY_NAME = "按姓名";
    public static final String BY_MAJOR = "按专业";
    public static final String SEARCH = "查询";

    public JComboBox jcb;
    public JTextField textField;
    public JButton searchBtn;
    public SelectTool selectTool;


    public SearchStudentFrame(String titleString, JTable table, String textString) {
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
        jcb.addItem(BY_NAME);
        jcb.addItem(BY_MAJOR);


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


    //把查询事件放进点击事件再传递下去就可以了，。，。我真是天才，以外来的数据包保存数据好像没有实现，。，。主要是单例模式不熟
//希望后面有时间使用单例模式写一遍
    @Override
    public void actionPerformed(ActionEvent e) {
        String textString = textField.getText().toString().trim();
        if (!textString.isEmpty()) {
            String jcbString = jcb.getSelectedItem().toString();
            String selectBy;
            if (jcbString.equals(BY_NUMBER)) {
                selectBy = "Sno";
            } else if (jcbString.equals(BY_NAME)) {
                selectBy = "Sname";
            } else {
                selectBy = "Smajor";
                textString = "'" + textString + "'";
            }
//            合成查询语句, 合成之后一定要打印一下进行检验

            String sqlString = "select * from students "
                    + "where " + selectBy + "=" + textField.getText().toString().trim()
                    + " order by Sno ";
            System.out.println("sqlString:" + sqlString);
            JTable table = selectTool.refresh(1, sqlString);
            this.setVisible(false);

            //只能这样传递数据了
            new SearchStudentFrame("heh", table, textString);
        }
    }


    public static void main(String[] args) {
        new SearchStudentFrame("查询学生", null, "");
    }


}
