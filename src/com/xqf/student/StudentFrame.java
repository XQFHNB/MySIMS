package com.xqf.student;

import com.xqf.basic.MyFrame;
import com.xqf.config.Config;
import com.xqf.db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by XQF on 2016/12/7.
 */
public class StudentFrame extends MyFrame {
    // TODO: 2016/12/7 学生登陆后映入眼帘应该就是各个学期的成绩，每一个学期一个成绩框，现在是第6学期，统计各项事情 
    // TODO: 2016/12/7 这个数据的显示要从上课表中查询主要的信息

    Connection connection = DBHelper.getDbHelper().getConnection();
    private static final String WARNING = "你还有30秒被开除，\n碾压他们";
    private static final String ENCOURAGMNET = "好好学习，天天向上";
    private static final int LIMIT = 15;

    private String studentNo;
    private int counterCredit;

    public StudentFrame(String titleString, String studentNo) {
        super(titleString);
        this.studentNo = studentNo;
        counterCredit = 0;
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());


        //左边的成绩信息部分，默认在中间填充所有位置
        JScrollPane scrollPanel = new JScrollPane();
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setSize(600, 450);

        JPanel allPanelContainer = new JPanel(new GridLayout(8, 1));
        scrollPanel.setViewportView(allPanelContainer);

        JPanel panel1 = getTermItemPanel("第一学期", 1);
        JPanel panel2 = getTermItemPanel("第二学期", 2);
        JPanel panel3 = getTermItemPanel("第三学期", 3);
        JPanel panel4 = getTermItemPanel("第四学期", 4);
        JPanel panel5 = getTermItemPanel("第五学期", 5);
        JPanel panel6 = getTermItemPanel("第六学期", 6);
        JPanel panel7 = getTermItemPanel("第七学期", 7);
        JPanel panel8 = getTermItemPanel("第八学期", 8);

        allPanelContainer.add(panel1);
        allPanelContainer.add(panel2);
        allPanelContainer.add(panel3);
        allPanelContainer.add(panel4);
        allPanelContainer.add(panel5);
        allPanelContainer.add(panel6);
        allPanelContainer.add(panel7);
        allPanelContainer.add(panel8);

        //右边有一个提示区域和按钮
        JPanel tipPanel = new JPanel(new GridLayout(4, 1));
        JTextArea tipText = new JTextArea();
        tipText.setEnabled(false);
        if (LIMIT - counterCredit < 3) {
            tipText.setText(WARNING);
            tipText.setBackground(Color.RED);
        } else {
            tipText.setText(ENCOURAGMNET);
            tipText.setBackground(Color.GREEN);
        }

        JButton stopBtn = new JButton("申请退学");
        JButton moneyBtn = new JButton("申请奖学金");
        JButton findTeacherBtn = new JButton("你的老师们");
        findTeacherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyTeacherFrame(Config.TITLE, studentNo);
            }
        });
        tipPanel.add(tipText);
        tipPanel.add(stopBtn);
        tipPanel.add(moneyBtn);
        tipPanel.add(findTeacherBtn);

        container.add(scrollPanel);
        container.add(tipPanel, BorderLayout.EAST);


    }

    private JPanel getTermItemPanel(String labelStr, int num) {
        String sqlCreateViewString = "create view helpView as "
                + "select course.Cno,course.Cname,course.Cteacher,course.Ccredit,sc.score,sc.courseNature"
                + " from sc,course"
                + " where term=" + num
                + " and sc.studentNo=" + "1400000"
                + " and sc.courseNo=course.Cno";
        //必修的分数和学分总和
        double sum1Score = 0;
        double sum1Ccredit = 0;

        //所有科目的分数和学分总和
        double allScore = 0;
        double allCcredit = 0;
        Vector allVector = new Vector();

        Vector tableHead = new Vector();
        tableHead.add("课号");
        tableHead.add("课名");
        tableHead.add("老师");
        tableHead.add("学分");
        tableHead.add("成绩");
        tableHead.add("性质");

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(Config.DROP_VIEW);
            stmt.execute(sqlCreateViewString);
            ResultSet resultSet = stmt.executeQuery(Config.QUERY_VIEW);
            while (resultSet.next()) {
                //// TODO: 2016/12/8 对于不及格的科目用红色标记
                System.out.println(resultSet.getInt(1) + " "
                        + resultSet.getString(2) + " "
                        + resultSet.getString(3) + " "
                        + resultSet.getInt(4) + " "
                        + resultSet.getInt(5) + " "
                        + resultSet.getInt(6));

                Vector vector = new Vector();
                vector.add(resultSet.getString(1));
                vector.add(resultSet.getString(2));
                vector.add(resultSet.getString(3));

                int Ccredit = resultSet.getInt(4);
                vector.add(resultSet.getInt(4));
                int score = resultSet.getInt(5);
                if (score < 60) {
                    counterCredit += Ccredit;
                }
                vector.add(resultSet.getInt(5));

                allCcredit += Ccredit;
                allScore += score;
                int a = resultSet.getInt(6);
                if (a == 0) {
                    vector.add("选修");
                } else {
                    vector.add("必修");
                    sum1Score = score * Ccredit;
                    sum1Ccredit += Ccredit;
                }

                allVector.add(vector);
                // stmt.execute(sqlDropViewString);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        double result1Avg = sum1Score / sum1Ccredit;
        double resultAllAvg = allScore / allCcredit;


        JPanel panel = new JPanel(new BorderLayout());

        String labelString = labelStr + "     必修课平均分：" + result1Avg + "  所有科目平均分：" + resultAllAvg;
        JLabel label = new JLabel(labelString);
        panel.add(label, BorderLayout.NORTH);

        JTable table = new JTable(allVector, tableHead);
        table.setEnabled(false);

        panel.add(table);

//        JPanel scrollPanel = new JPanel(new BorderLayout());
//        scrollPanel.add(table);
//        JScrollPane scrollPane = new JScrollPane(scrollPanel);

//        panel.add(scrollPane);
        return panel;
    }

    public static void main(String[] args) {
        new StudentFrame("hhh", "");
    }
}
