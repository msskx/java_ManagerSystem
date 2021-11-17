package com.roadjava.student.view;

import com.roadjava.handler.MainViewHandler;
import com.roadjava.req.StudentRequest;
import com.roadjava.response.TableDTO;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.table.MainViewTable;
import com.roadjava.student.table.MainViewTableModel;
import main.java.com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class MainView extends JFrame {


    Container container=getContentPane();
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn=new JButton("增加");
    JButton updateBtn=new JButton("修改");
    JButton delBtn=new JButton("删除");
    JTextField searchTXt=new JTextField(15);
    JButton searchBtn=new JButton("查询");

    JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn=new JButton("上一页");
    JButton nextBtn=new JButton("下一页");

    MainViewTable mainViewTable=new MainViewTable();
    private int pageNow=1;//当前是第几页
    private int pageSize=10;//一页显示多少条记录
    MainViewHandler mainViewHandler=new MainViewHandler(this);

    public MainView() {
        //北面布局
        layoutNorth();
        //中间布局
        layoutCenter();
        //南面布局
        layoutSouth();

        setIconImage(new ImageIcon(".\\src\\main\\res\\icon.png").getImage());
        setBounds(DimensionUtil.getBounds());
        setExtendedState(JFrame.MAXIMIZED_BOTH);//默认最大化
        setLocationRelativeTo(null);//默认居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter() {
        TableDTO dto = getableDTO();
        MainViewTableModel mainViewTableModel=MainViewTableModel.assembleModel(dto.getData());
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane=new JScrollPane(mainViewTable);
        container.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getableDTO() {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTXt.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudents(request);
        return tableDTO;
    }

    private void layoutSouth() {
        preBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(nextBtn);
        container.add(southPanel,BorderLayout.SOUTH);
    }
    //设置上一页下一页是否可见
    private void showPreNext(int totalCount){
        if(pageNow==1){
            preBtn.setVisible(false);
        }else{
            preBtn.setVisible(true);
        }
        int pageCount=0;
        if(totalCount%pageSize==0){
            pageCount=totalCount/pageSize;
        }else{
            pageCount=totalCount/pageSize+1;
        }
        if(pageNow==pageCount){
            nextBtn.setVisible(false);
        }else{
            nextBtn.setVisible(true);
        }
    }

    private void layoutNorth() {
        addBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);

        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchBtn);
        northPanel.add(searchTXt);
        container.add(northPanel,BorderLayout.NORTH);
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }


    public void reloadTable() {
        TableDTO dto = getableDTO();
        MainViewTableModel.updateModel(dto.getData());
        mainViewTable.renderRule();
        showPreNext(dto.getTotalCount());
    }
    public int[]getSelectedStudentIds(){
        int[]selectedRows=mainViewTable.getSelectedRows();
        int[]ids=new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex=selectedRows[i];
            Object idObj=mainViewTable.getValueAt(rowIndex,0);
            ids[i]=Integer.valueOf(idObj.toString());
        }
        return ids;
    }

}

