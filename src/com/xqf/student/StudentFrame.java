package com.xqf.student;

import com.xqf.basic.MyFrame;

/**
 * Created by XQF on 2016/12/7.
 */
public class StudentFrame extends MyFrame {
    // TODO: 2016/12/7 学生登陆后映入眼帘应该就是各个学期的成绩，每一个学期一个成绩框，现在是第6学期，统计各项事情 
    // TODO: 2016/12/7 这个数据的显示要从上课表中查询主要的信息 
    public StudentFrame(String titleString) {
        super(titleString);
    }

    public static void main(String[] args) {
        new StudentFrame("hhh");
    }
}
