package com.msskx.java.controller;



import com.msskx.java.model.EmployeeDO;
import com.msskx.java.service.EmployeeService;
import com.msskx.java.service.impl.EmployeeServiceImpl;
import com.msskx.java.view.MainView;
import com.msskx.java.view.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateViewController implements ActionListener {

    private UpdateView updateView;
    private MainView mainView;

    public UpdateViewController(UpdateView updateView, MainView mainView) {
        this.updateView = updateView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        String text=jButton.getText();
        if("修改".equals(text)){
            EmployeeService employeeService=new EmployeeServiceImpl();
            EmployeeDO employeeDO=updateView.buildUpdateEmployeeDO();
            boolean updateResult=employeeService.update(employeeDO);
            if(updateResult){
                mainView.reloadTable();
                updateView.dispose();
            }else{
                JOptionPane.showMessageDialog(updateView,"修改失败");
            }
        }
    }
}
