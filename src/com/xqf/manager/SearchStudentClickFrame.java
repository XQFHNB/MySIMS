//package com.xqf.manager;
//
//import com.xqf.util.SelectTool;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * Created by XQF on 2016/12/9.
// */
//public class SearchStudentClickFrame extends SearchStudentFrame {
//    private String jcbString;
//    private SelectTool selectTool;
//
//    public SearchStudentClickFrame(String titleString, String textString) {
//        super(titleString);
//        selectTool = new SelectTool();
//        this.textField.setText(textString);
//        Container container = this.getContentPane();
//        jcbString = jcb.getSelectedItem().toString();
//
//        System.out.println("jcbString:" + jcbString);
//        //获取查询依据，是学号还是专业
//        String selectBy;
//        if (jcbString.equals(BY_NUMBER)) {
//            selectBy = "Sno";
//        } else if (jcbString.equals(BY_NAME)) {
//            selectBy = "Sname";
//        } else {
//            selectBy = "Smajor";
//        }
//
//        //合成查询语句,合成之后一定要打印一下进行检验
//        String sqlString = "select * from students "
//                + "where " + selectBy + "=" + textString
//                + " order by Sno ";
//        System.out.println("sqlString:" + sqlString);
//
//        //生成结果
//        JTable table = selectTool.refresh(1, sqlString);
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        this.searchBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new SearchStudentClickFrame("heh", textField.getText().toString().trim());
//            }
//        });
//        container.add(scrollPane);
//    }
//
//
//    public static void main(String[] args) {
//        new SearchStudentClickFrame("点击", "1400000");
//    }
//
//
//}
