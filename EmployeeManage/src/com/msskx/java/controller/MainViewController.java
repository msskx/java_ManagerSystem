package com.msskx.java.controller;


import com.msskx.java.service.EmployeeService;
import com.msskx.java.service.impl.EmployeeServiceImpl;
import com.msskx.java.view.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {
    private MainView mainView;
    public MainViewController(MainView mainView) {
        this.mainView=mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        String text=jButton.getText();
        if("添加".equals(text)){
            new AddEmployeeView(mainView);
        }else if("修改".equals(text)){
            int[]selestedVegetableIds=mainView.getSelectedStudentIds();
            if(selestedVegetableIds.length!=1){
                JOptionPane.showMessageDialog(mainView,"一次只能修改一行！");
                return;
            }
            new UpdateView(mainView,selestedVegetableIds[0]);

        }else if("删除".equals(text)){
            int[]selectedVegetableIds=mainView.getSelectedStudentIds();
            if(selectedVegetableIds.length==0){
                JOptionPane.showMessageDialog(mainView,"请选择要删除的行");
                return;
            }
            int option=JOptionPane.showConfirmDialog(mainView,"你确定要删除选择的"+selectedVegetableIds.length
                    +"行吗？","确认删除",JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.YES_OPTION){
                //执行删除操作
                EmployeeService employeeService=new EmployeeServiceImpl();
                boolean deleteResult=employeeService.delete(selectedVegetableIds);
                if(deleteResult){
                    mainView.reloadTable();
                }else{
                    JOptionPane.showMessageDialog(mainView,"删除失败");
                }
            }
        }else if("查询".equals(text)){
            mainView.setPageNow(1);
            mainView.reloadTable();
        }else if("上一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow()-1);
            mainView.reloadTable();
        }else if("下一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow()+1);
            mainView.reloadTable();
        }else if("重新登录".equals(text)){
            mainView.dispose();
            new LoginView();
        }else if("显示图表".equals(text)){
            new DataChart();
        }
    }
}
