package com.msskx.java.view;

import com.msskx.java.controller.EmployeeRequest;
import com.msskx.java.controller.MainViewController;
import com.msskx.java.model.TableDTO;
import com.msskx.java.service.EmployeeService;
import com.msskx.java.service.impl.EmployeeServiceImpl;
import com.msskx.java.util.DButil;
import com.msskx.java.util.DimensionUtil;
import com.msskx.java.view.table.MainViewTable;
import com.msskx.java.view.table.MainViewTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainView extends JFrame {
    Container container=getContentPane();
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));//面板流式布局向左对齐
    JButton addBtn=new JButton("添加");
    JButton updateBtn=new JButton("修改");
    JButton delBtn=new JButton("删除");
    JTextField searchTXt=new JTextField(15);
    JButton searchBtn=new JButton("查询");
    JButton chartBtn =new JButton("显示图表");
    JButton reLogin=new JButton("重新登录");
    JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));//面板流式布局向右对齐
    JButton preBtn=new JButton("上一页");
    JButton nextBtn=new JButton("下一页");

    private int pageNow=1;//当前页数
    private int pageSize=10;//一页显示记录条数

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    MainViewTable mainViewTable=new MainViewTable();
    //主页面监听控制
    MainViewController mainViewController=new MainViewController(this);

    public MainView() {
        super("员工工资管理系统");
        //全屏情况下窗体大小
        Rectangle bounds= DimensionUtil.getBounds();
        pageSize=Math.floorDiv(bounds.height,35);
        //北面布局
        layoutNorth();
        //中心布局
        layoutCenter();
        //南面布局
        layoutSouth();

        setIconImage(new ImageIcon(".\\src\\com\\msskx\\java\\res\\icon.png").getImage());
        setBounds(bounds);
        setExtendedState(MAXIMIZED_BOTH);//默认最大化
        setLocationRelativeTo(null);//默认居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);//退出销毁窗体
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter() {
        TableDTO dto=getableDTO();
        //将获取到的表格数据整合到表格模型
        MainViewTableModel mainViewTableModel=MainViewTableModel.assembleModel(dto.getData());
        mainViewTable.setModel(mainViewTableModel);//给表格设置模型
        mainViewTable.renderRule();//渲染
        JScrollPane jScrollPane=new JScrollPane(mainViewTable);//将表格添加到滚动面板
        container.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }
    /**
     * 将两个按钮添加到南面
     */
    private void layoutSouth() {
        preBtn.addActionListener(mainViewController);
        nextBtn.addActionListener(mainViewController);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        container.add(southPanel,BorderLayout.SOUTH);
    }
    /**
     * 将四个按钮和一个搜索框加入北面
     */
    private void layoutNorth() {
        addBtn.addActionListener(mainViewController);
        updateBtn.addActionListener(mainViewController);
        delBtn.addActionListener(mainViewController);
        searchBtn.addActionListener(mainViewController);
        reLogin.addActionListener(mainViewController);
        chartBtn.addActionListener(mainViewController);

        addBtn.setFont(new Font("宋体",Font.BOLD,18));
        updateBtn.setFont(new Font("宋体",Font.BOLD,18));
        delBtn.setFont(new Font("宋体",Font.BOLD,18));
        searchBtn.setFont(new Font("宋体",Font.BOLD,18));
        searchTXt.setFont(new Font("宋体",Font.BOLD,18));
        reLogin.setFont(new Font("宋体",Font.BOLD,18));
        chartBtn.setFont(new Font("宋体",Font.BOLD,18));
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTXt);
        northPanel.add(searchBtn);
        northPanel.add(chartBtn);
        northPanel.add(reLogin);

        container.add(northPanel,BorderLayout.NORTH);
    }
    /**
     * 获取表格对象
     * @return
     */
    private TableDTO getableDTO(){
        EmployeeService employeeService=new EmployeeServiceImpl();
        EmployeeRequest request=new EmployeeRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTXt.getText().trim());
        //将当前页面的基本信息获取到，利用获取到的数据初始化表格模型
        TableDTO tableDTO=employeeService.retrieveEmployee(request);
        return tableDTO;
    }
    //设置南边布局按钮是否可见
    private void showPreNext(int totalCount){
        if(pageNow==1){
            preBtn.setVisible(false);
        }else{
            preBtn.setVisible(true);
        }
        int pageCount=0;
        if(totalCount%pageSize==0){
            //总页数/每页个数等于页数
            pageCount=totalCount/pageSize;
        }else{
            //如果不能整除说明数据多余，还要另外再取一页
            pageCount=totalCount/pageSize+1;
        }
        if(pageNow==pageCount){
            nextBtn.setVisible(false);
        }else{
            nextBtn.setVisible(true);
        }
    }

    /**
     * 刷新表格
     */
    public void reloadTable(){
        TableDTO dto=getableDTO();//获取表格对象
        MainViewTableModel.updateModel(dto.getData());//更新表格模型
        mainViewTable.renderRule();//渲染样式
        showPreNext(dto.getTotalCount());//是否显示按钮
    }

    /**
     * 获取被选中的ids列
     * @return
     */
    public int[] getSelectedStudentIds(){
        int[]selectedRows=mainViewTable.getSelectedRows();
        int[]ids=new int[selectedRows.length];
        for(int i=0;i<selectedRows.length;i++){
            int rowIndex=selectedRows[i];
            Object idObj=mainViewTable.getValueAt(rowIndex,0);
            ids[i]=Integer.valueOf(idObj.toString());
        }
        return ids;
    }
    /**
     * 排序功能，没用，直接数据库排序
     * @return
     */
    public void order() {
        StringBuilder sql =new StringBuilder(" SELECT * from employee order by id desc");
        Connection conn=null;
        PreparedStatement ps=null;

        try {
            conn= DButil.getConnection();
            ps=conn.prepareStatement(sql.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closePS(ps);
            DButil.closeConnection(conn);
        }
    }

}
