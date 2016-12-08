package com.xqf.manager;

import com.xqf.basic.MyFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by XQF on 2016/12/7.
 */
public class SearchStudentFrame extends MyFrame {
    public SearchStudentFrame(String titleString) {
        super(titleString);
        Container container=this.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel=new JPanel();
        JTextField textField=new JTextField(20);
        JComboBox jcb=new JComboBox();
        JButton searchBtn=new JButton("查询");
        jcb.addItem("按学号");
        jcb.addItem("按姓名");
        jcb.addItem("按专业号");
        topPanel.add(jcb);
        topPanel.add(textField);
        topPanel.add(searchBtn);
        container.add(topPanel,BorderLayout.NORTH);
        JTextArea textInfoArea=new JTextArea();
        container.add(textInfoArea);

    }
    public  static  void main(String []args){
        new SearchStudentFrame("查询学生");
    }
}
