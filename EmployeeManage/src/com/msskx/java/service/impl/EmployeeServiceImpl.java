package com.msskx.java.service.impl;



import com.msskx.java.controller.EmployeeRequest;
import com.msskx.java.model.EmployeeDO;
import com.msskx.java.model.TableDTO;
import com.msskx.java.service.EmployeeService;
import com.msskx.java.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public TableDTO retrieveEmployee(EmployeeRequest employeeRequest) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from employee ");
        if(employeeRequest.getSearchKey()!=null&&!"".equals(employeeRequest.getSearchKey().trim())){
            sql.append(" where name like '%"+employeeRequest.getSearchKey().trim()+"%'");
        }
        sql.append("order by id desc limit ").append(employeeRequest.getStart()).append(",")
                .append(employeeRequest.getPageSize());
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        TableDTO returnDTO=new TableDTO();

        try {
            conn= DButil.getConnection();
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            //查询数据库并将数据填入
            returnDTO.setData(fillData(rs));
            //将语句清空
            sql.setLength(0);
            sql.append("select count(*) from employee ");
            if(employeeRequest.getSearchKey()!=null&&!"".equals(employeeRequest.getSearchKey().trim())){
                sql.append(" where name like '%"+employeeRequest.getSearchKey().trim()+"%'");
            }
            ps= conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            while(rs.next()){
                int count=rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return  returnDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeRS(rs);
            DButil.closePS(ps);
            DButil.closeConnection(conn);
        }
        return null;
    }

    /**
     * 添加
     * @param employeeDO
     * @return
     */
    @Override
    public boolean add(EmployeeDO employeeDO ) {
        StringBuilder sql=new StringBuilder();
        sql.append(" insert into employee(name,age,sex,base_salary,bonus,sum,apartment,position) ");
        sql.append(" value(?,?,?,?,?,?,?,?) ");
        Connection conn=null;
        PreparedStatement ps=null;

        try {
            conn=DButil.getConnection();
            ps= conn.prepareStatement(sql.toString());
            ps.setString(1,employeeDO.getName());
            ps.setInt(2,employeeDO.getAge());
            ps.setString(3,employeeDO.getSex());

            ps.setDouble(4,employeeDO.getBaseSalary());
            ps.setDouble(5,employeeDO.getBonus());
            ps.setDouble(6,employeeDO.getSum());
            ps.setString(7,employeeDO.getApartment());
            ps.setString(8,employeeDO.getPosition());

            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closePS(ps);
            DButil.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean delete(int[] selectEmployeeIds) {
        StringBuilder sql=new StringBuilder();
        sql.append(" delete from employee where id in (");
        int length=selectEmployeeIds.length;
        for (int i = 0; i < length; i++) {
            if(i==(length-1)){
                sql.append(" ? ");
            }else{
                sql.append(" ?, ");
            }
        }
        sql.append(") ");
        Connection conn=null;
        PreparedStatement  ps=null;
        try {
            conn=DButil.getConnection();
            ps=conn.prepareStatement(sql.toString());
            for (int i = 0; i < length; i++) {
                //将选中的列设置给sql语句
                ps.setInt(i+1,selectEmployeeIds[i]);
            }
            return ps.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closePS(ps);
            DButil.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean update(EmployeeDO employeeDO) {
        StringBuilder sql=new StringBuilder();
        sql.append(" update employee set name=?,age=?,sex=?,base_salary=?,bonus=?,sum=?,apartment=?,position=?");
        sql.append(" where id =? ");

        Connection conn=null;
        PreparedStatement ps=null;

        try {
            conn=DButil.getConnection();
            ps=conn.prepareStatement(sql.toString());
            ps.setString(1,employeeDO.getName());
            ps.setInt(2,employeeDO.getAge());
            ps.setString(3,employeeDO.getSex());

            ps.setDouble(4,employeeDO.getBaseSalary());
            ps.setDouble(5,employeeDO.getBonus());
            ps.setDouble(6,employeeDO.getSum());
            ps.setString(7,employeeDO.getApartment());
            ps.setString(8,employeeDO.getPosition());
            ps.setInt(9,employeeDO.getId());

            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closePS(ps);
            DButil.closeConnection(conn);
        }
        return false;
    }
    //获取对应ID的对象
    @Override
    public EmployeeDO getById(int selectedEmployeeId) {
        StringBuilder sql =new StringBuilder(" select * from employee where id=? ");

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        EmployeeDO employeeDO=null;

        try {
            employeeDO=new EmployeeDO();
            conn=DButil.getConnection();
            ps=conn.prepareStatement(sql.toString());
            ps.setInt(1,selectedEmployeeId);
            rs=ps.executeQuery();

            while(rs.next()){
                int id= rs.getInt("id");
                String name=rs.getString("name");
                Integer age=rs.getInt("age");
                String sex=rs.getString("sex");
                double baseSalary=rs.getDouble("base_salary");
                double bonus=rs.getDouble("bonus");
                double sum=rs.getDouble("sum");
                String apartment=rs.getString("apartment");
                String position=rs.getString("position");

                employeeDO.setId(id);
                employeeDO.setName(name);
                employeeDO.setAge(age);
                employeeDO.setSex(sex);
                employeeDO.setBonus(bonus);
                employeeDO.setPosition(position);
                employeeDO.setApartment(apartment);
                employeeDO.setSum(sum);
                employeeDO.setBaseSalary(baseSalary);
            }
            return employeeDO;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeRS(rs);
            DButil.closePS(ps);
            DButil.closeConnection(conn);
        }
        return null;
    }

    /**
     * 获取各个部门的总工资
     * @return
     */
    @Override
    public double[] getAllSalary() {
        StringBuilder sql1 =new StringBuilder(" select * from employee where apartment='研发部' ");
        StringBuilder sql2 =new StringBuilder(" select * from employee where apartment='运营部' ");
        StringBuilder sql3 =new StringBuilder(" select * from employee where apartment='产品部' ");
        StringBuilder sql4 =new StringBuilder(" select * from employee where apartment='市场部' ");
        StringBuilder sql5 =new StringBuilder(" select * from employee where apartment='销售部' ");

        Connection conn=null;
        PreparedStatement ps1=null;
        ResultSet rs1=null;
        PreparedStatement ps2=null;
        ResultSet rs2=null;
        PreparedStatement ps3=null;
        ResultSet rs3=null;
        PreparedStatement ps4=null;
        ResultSet rs4=null;
        PreparedStatement ps5=null;
        ResultSet rs5=null;
        double[] result=new double[5];

        try {
            conn=DButil.getConnection();
            ps1=conn.prepareStatement(sql1.toString());
            rs1=ps1.executeQuery();
            while(rs1.next()){
                result[0]+=rs1.getDouble("sum");
            }
            ps2=conn.prepareStatement(sql2.toString());
            rs2=ps2.executeQuery();
            while(rs2.next()){

                result[1]+=rs2.getDouble("sum");
            }
            ps3=conn.prepareStatement(sql3.toString());
            rs3=ps3.executeQuery();
            while(rs3.next()){

                result[2]+=rs3.getDouble("sum");
            }
            ps4=conn.prepareStatement(sql4.toString());
            rs4=ps4.executeQuery();
            while(rs4.next()){

                result[3]+=rs4.getDouble("sum");
            }
            ps5=conn.prepareStatement(sql5.toString());
            rs5=ps5.executeQuery();
            while(rs5.next()){

                result[4]+=rs5.getDouble("sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeRS(rs1);
            DButil.closePS(ps1);
            DButil.closeRS(rs2);
            DButil.closePS(ps2);
            DButil.closeRS(rs3);
            DButil.closePS(ps3);
            DButil.closeRS(rs4);
            DButil.closePS(ps4);
            DButil.closeRS(rs5);
            DButil.closePS(ps5);
            DButil.closeConnection(conn);
            return result;
        }
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>>data=new Vector<>();
        while(rs.next()){
            Vector<Object>oneRecord=new Vector<>();
            int id= rs.getInt("id");
            String name=rs.getString("name");
            Integer age=rs.getInt("age");
            String sex=rs.getString("sex");
            double baseSalary=rs.getDouble("base_salary");
            double bonus=rs.getDouble("bonus");
            double sum=rs.getDouble("sum");
            String apartment=rs.getString("apartment");
            String position=rs.getString("position");


            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(age);
            oneRecord.addElement(sex);
            oneRecord.addElement(baseSalary);
            oneRecord.addElement(bonus);
            oneRecord.addElement(sum);
            oneRecord.addElement(apartment);
            oneRecord.addElement(position);

            data.addElement(oneRecord);
        }
        return data;
    }
}
