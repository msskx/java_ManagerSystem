package com.msskx.java.controller;

import com.msskx.java.view.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeMainViewController implements ActionListener {
    private EmployeeMainView employeeMainView;
    public EmployeeMainViewController(EmployeeMainView employeeMainView) {
        this.employeeMainView = employeeMainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        String text=jButton.getText();
        if("查询".equals(text)){
            employeeMainView.setPageNow(1);
            employeeMainView.reloadTable();
        }else if("上一页".equals(text)){
            employeeMainView.setPageNow(employeeMainView.getPageNow()-1);
            employeeMainView.reloadTable();
        }else if("下一页".equals(text)){
            employeeMainView.setPageNow(employeeMainView.getPageNow()+1);
            employeeMainView.reloadTable();
        }else if("重新登录".equals(text)){
            employeeMainView.dispose();
            new LoginView();
        }else if("显示图表".equals(text)){
            new DataChart();
        }
    }
}
