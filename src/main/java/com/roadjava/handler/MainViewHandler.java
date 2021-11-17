package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import com.roadjava.service.impl.AdminServiceImpl;
import com.roadjava.student.view.AddStudentView;
import com.roadjava.student.view.LoginView;
import com.roadjava.student.view.MainView;
import com.roadjava.student.view.UpdateStudentView;
import jdk.nashorn.internal.runtime.regexp.joni.exception.JOniException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainViewHandler extends KeyAdapter implements ActionListener {

    private MainView mainView;
    public MainViewHandler(MainView mainView) {
        this.mainView=mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton)e.getSource();
        String text= jButton.getText();
        if("增加".equals(text)){
            new AddStudentView(mainView);
//
        }else if("修改".equals(text)){
            int[]selectedStudentIds= mainView.getSelectedStudentIds();
            if(selectedStudentIds.length!=1){
                JOptionPane.showMessageDialog(mainView,"一次只能修改一行！");
                return;
            }
            new UpdateStudentView(mainView,selectedStudentIds[0]);
        }else if("删除".equals(text)){
//
        }else if("查询".equals(text)){
            mainView.setPageNow(1);
            mainView.reloadTable();
        }else if("上一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow()-1);
            mainView.reloadTable();
        }else if("下一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow()+1);
            mainView.reloadTable();
        }
    }


}
