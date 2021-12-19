package com.msskx.java.service;


import com.msskx.java.controller.EmployeeRequest;
import com.msskx.java.model.EmployeeDO;
import com.msskx.java.model.TableDTO;

public interface EmployeeService {
    TableDTO retrieveEmployee(EmployeeRequest employeeRequest);//在数据库取回数据
    boolean add(EmployeeDO employeeDO);
    boolean delete(int[]selectEmployeeIds);
    boolean update(EmployeeDO employeeDO);
    EmployeeDO getById(int selectedEmployeeId);
    double []getAllSalary();

}
