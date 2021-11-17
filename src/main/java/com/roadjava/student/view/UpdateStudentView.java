package com.roadjava.student.view;

import com.roadjava.entity.StudentDO;
import com.roadjava.handler.UpdateStudentViewHandler;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;


import javax.swing.*;
import java.awt.*;
import java.lang.*;

public class UpdateStudentView extends JDialog {

    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel idLabel=new JLabel("编号：",JLabel.RIGHT);
    JTextField idTxt=new JTextField();
    JLabel nameLabel=new JLabel("姓名：",JLabel.RIGHT);
    JTextField nameTxt=new JTextField();
    JLabel noLabel=new JLabel("学号：",JLabel.RIGHT);
    JTextField noTxt=new JTextField();
    JLabel homeLabel=new JLabel("家乡：",JLabel.RIGHT);
    JTextField homeTxt=new JTextField();
    JLabel cnLabel=new JLabel("语文成绩：",JLabel.RIGHT);
    JTextField cnTxt=new JTextField();
    JLabel mathLabel=new JLabel("数学成绩：",JLabel.RIGHT);
    JTextField mathTxt=new JTextField();
    JLabel enLabel=new JLabel("英语成绩：",JLabel.RIGHT);
    JTextField enTxt=new JTextField();
    JButton updateBtn =new JButton("修改学生");
    UpdateStudentViewHandler updateStudentViewHandler;

    public UpdateStudentView(MainView mainView, int selectedStudentId) {

        super(mainView,"修改学生",true);
        updateStudentViewHandler=new UpdateStudentViewHandler(this,mainView);
        //查询selectedStudentId对应的记录并回显
        StudentService studentService=new StudentServiceImpl();
        StudentDO selectedStu=studentService.getById(selectedStudentId);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        idTxt.setText(selectedStu.getId()+"");
        //设置id不可编辑
        idTxt.setEnabled(false);
        jPanel.add(idTxt);
        nameLabel.setPreferredSize(new Dimension(80,30));

        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameTxt.setText(selectedStu.getName());
        jPanel.add(nameTxt);
        noLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(noLabel);
        noTxt.setPreferredSize(new Dimension(200,30));
        noTxt.setText(selectedStu.getNo());
        jPanel.add(noTxt);
        homeLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(homeLabel);
        homeTxt.setPreferredSize(new Dimension(200,30));
        homeTxt.setText(selectedStu.getHomeTown());
        jPanel.add(homeTxt);
         cnLabel .setPreferredSize(new Dimension(80,30));
        jPanel.add(cnLabel);
        cnTxt.setPreferredSize(new Dimension(200,30));
        cnTxt.setText(String.valueOf(selectedStu.getCnScore()));
        jPanel.add(cnTxt);
        mathLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(mathLabel);
        mathTxt.setPreferredSize(new Dimension(200,30));
        mathTxt.setText(String.valueOf(selectedStu.getMathScore()));
        jPanel.add(mathTxt);
        enLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(enLabel);
        enTxt.setPreferredSize(new Dimension(200,30));
        enTxt.setText(String.valueOf(selectedStu.getEnScore()));
        
        jPanel.add(enTxt);
        updateBtn.addActionListener(updateStudentViewHandler);
        jPanel.add(updateBtn);
        Container container=getContentPane();
        container.add(jPanel);

        setSize(330,500);
        setLocationRelativeTo(null);
        //只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public StudentDO buildUpdatedStudentDO() {
        StudentDO studentDO=new StudentDO();
        studentDO.setId(Integer.valueOf(idTxt.getText()));
        studentDO.setName(nameTxt.getText());
        studentDO.setNo(noTxt.getText());
        studentDO.setHomeTown(homeTxt.getText());
        studentDO.setCnScore(Double.valueOf(cnTxt.getText()));
        studentDO.setEnScore(Double.valueOf(enTxt.getText()));
        studentDO.setMathScore(Double.valueOf(mathTxt.getText()));
        return studentDO;
    }
}
