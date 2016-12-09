package com.xqf.manager;

import javax.swing.*;

/**
 * Created by XQF on 2016/12/7.
 */

public class AddStudentItem {
    public static final int GAP = 5;
    public static final int LABEL_X = 150;
    public static final int LABEL_LENGTH = 100;
    public static final int LABEL_WIDTH = 20;
    public static final int TEXT_X = LABEL_X + LABEL_LENGTH + 20;
    public static final int TEXT_LENGTH = 200;
    public static final int TEXT_WIDTH = 20;
    private JPanel panel;
    private JTextField textField;

    public AddStudentItem(String labelString) {
        panel = new JPanel();
        panel.setLayout(null);
        JLabel label = new JLabel(labelString);
        label.setBounds(LABEL_X, GAP, LABEL_LENGTH, LABEL_WIDTH);
        textField = new JTextField();
        textField.setBounds(TEXT_X, GAP, TEXT_LENGTH, TEXT_WIDTH);
        panel.add(label);
        panel.add(textField);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTextField() {
        return textField;
    }
}