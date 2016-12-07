package com.xqf.basic;

import javax.swing.*;

/**
 * Created by XQF on 2016/12/7.
 */
public class AddStudentItem {
    private  JPanel panel;
    private  JLabel label;
    private  JTextField textField;
    public  AddStudentItem(String labelString){
        panel=new JPanel();
        label=new JLabel(labelString);
        textField=new JTextField(20);
        panel.add(label);
        panel.add(textField);
    }

    public JPanel getPanel(){
        return panel;
    }
    public  JTextField getTextField(){
        return textField;
    }
}
