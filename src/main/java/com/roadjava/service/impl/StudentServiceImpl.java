package com.roadjava.service.impl;

import com.roadjava.entity.StudentDO;
import com.roadjava.req.StudentRequest;
import com.roadjava.response.TableDTO;
import com.roadjava.service.StudentService;
import com.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentServiceImpl implements StudentService {


    @Override
    public TableDTO retrieveStudents(StudentRequest request) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from student ");
        if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
            sql.append(" where name like '%"+request.getSearchKey().trim()+"%'");
        }
        sql.append("order by id desc limit ").append(request.getStart()).append(",").append(
                request.getPageSize());
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        TableDTO returnDTO=new TableDTO();
        try {
            conn= DBUtil.getConn();
            ps= conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            //查询记录
            returnDTO.setData(fillData(rs));
            sql.setLength(0);
            sql.append("select count(*) from student ");
            if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
                sql.append(" where name like '%"+request.getSearchKey().trim()+"%'");
            }
            ps= conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            while(rs.next()){
                int count=rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean add(StudentDO studentDO) {
        StringBuilder sql=new StringBuilder();
        sql.append(" insert into student(name,no,home_town,cn_score,en_score,math_score) ");
        sql.append(" value(?,?,?,?,?,?) ");
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn= DBUtil.getConn();
            ps= conn.prepareStatement(sql.toString());
            ps.setString(1, studentDO.getName());
            ps.setString(2, studentDO.getNo());
            ps.setString(3, studentDO.getHomeTown());
            ps.setDouble(4, Double.valueOf(studentDO.getCnScore()));
            ps.setDouble(5, Double.valueOf(studentDO.getEnScore()));
            ps.setDouble(6, Double.valueOf(studentDO.getMathScore()));
            return ps.executeUpdate()==1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public StudentDO getById(int selectedStudentId) {
        StringBuilder sql=new StringBuilder( "select * from student where id = ? " );
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        StudentDO studentDO=new StudentDO();
        try {
            conn= DBUtil.getConn();
            ps= conn.prepareStatement(sql.toString());
            ps.setInt(1,selectedStudentId);
            rs=ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String no = rs.getString("no");
                String homeTown = rs.getString("home_town");
                double cnScore = rs.getDouble("cn_score");
                double enScore = rs.getDouble("en_score");
                double mathScore = rs.getDouble("math_score");
                studentDO.setName(name);
                studentDO.setId(id);
                studentDO.setHomeTown(homeTown);
                studentDO.setNo(no);
                studentDO.setCnScore(cnScore);
                studentDO.setEnScore(enScore);
                studentDO.setMathScore(mathScore);
            }
            return studentDO;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean update(StudentDO studentDO) {
        StringBuilder sql=new StringBuilder();
        sql.append(" update student set name=?,no=?,home_town=?,cn_score=?,en_score=?,math_score=? ");
        sql.append(" where id =? ");
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn= DBUtil.getConn();
            ps= conn.prepareStatement(sql.toString());
            ps.setString(1, studentDO.getName());
            ps.setString(2, studentDO.getNo());
            ps.setString(3, studentDO.getHomeTown());
            ps.setDouble(4, Double.valueOf(studentDO.getCnScore()));
            ps.setDouble(5, Double.valueOf(studentDO.getEnScore()));
            ps.setDouble(6, Double.valueOf(studentDO.getMathScore()));
            ps.setInt(7,studentDO.getId());
            return ps.executeUpdate()==1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean delete(int[] selectedStudentIds) {
        StringBuilder sql=new StringBuilder();
        sql.append(" delete from student where id in ( ");
        int length=selectedStudentIds.length;
        for (int i = 0; i < length; i++) {
            if(i==(length-1)){
                sql.append(" ? ");
            }else{
                sql.append(" ?, ");
            }
        }
        sql.append(" ) ");
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn= DBUtil.getConn();
            ps= conn.prepareStatement(sql.toString());
            for (int i = 0; i < length; i++) {
                //设置参数从一开始
                ps.setInt(i+1,selectedStudentIds[i]);
            }
            return ps.executeUpdate()==1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>>data=new Vector<>();
        while(rs.next()){
            Vector<Object>oneRecord=new Vector<>();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String homeTown = rs.getString("home_town");
            double cnScore = rs.getDouble("cn_score");
            double enScore = rs.getDouble("en_score");
            double mathScore = rs.getDouble("math_score");
            double totalScore=cnScore+enScore+mathScore;
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(no);
            oneRecord.addElement(homeTown);
            oneRecord.addElement(cnScore);
            oneRecord.addElement(enScore);
            oneRecord.addElement(mathScore);
            oneRecord.addElement(totalScore);
            data.addElement(oneRecord);
        }
        return data;
    }

}
