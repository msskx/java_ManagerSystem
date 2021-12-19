package com.msskx.java.controller;



import com.msskx.java.model.EmployeeDO;
import com.msskx.java.service.EmployeeService;
import com.msskx.java.service.impl.EmployeeServiceImpl;
import com.msskx.java.view.AddEmployeeView;
import com.msskx.java.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeController implements ActionListener {
    AddEmployeeView addedEmployeeView;
    MainView mainView;

    public AddEmployeeController(AddEmployeeView addedEmployeeView, MainView mainView) {
        this.addedEmployeeView = addedEmployeeView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        EmployeeService employeeService=new EmployeeServiceImpl();
        EmployeeDO employeeDO=addedEmployeeView.buildVegetableDO();
        boolean addResult=employeeService.add(employeeDO);
        if(addResult){
            //刷新表格内容
            mainView.reloadTable();
            addedEmployeeView.dispose();
        }else{
            JOptionPane.showMessageDialog(addedEmployeeView,"添加失败");
        }

    }
}
