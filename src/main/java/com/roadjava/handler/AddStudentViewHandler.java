package com.roadjava.handler;

import com.roadjava.entity.StudentDO;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.AddStudentView;
import com.roadjava.student.view.MainView;
import com.roadjava.student.view.UpdateStudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class AddStudentViewHandler extends KeyAdapter implements ActionListener {

    private AddStudentView addStudentView;
    private MainView mainView;
    public AddStudentViewHandler(AddStudentView addStudentView, MainView mainView) {
        this.addStudentView=addStudentView;
        this.mainView=mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton)e.getSource();
        String text= jButton.getText();
        if("添加学生".equals(text)){
            StudentService studentService=new StudentServiceImpl();
            StudentDO studentDO= addStudentView.buildStudentDO();
            boolean addResult=studentService.add(studentDO);
            if(addResult){
                //重新加载表格并获取数据
                mainView.reloadTable();
                addStudentView.dispose();
            }else{
                JOptionPane.showMessageDialog(addStudentView,"添加失败");
            }
        }
    }
}
