package com.roadjava.service;

import com.roadjava.entity.StudentDO;
import com.roadjava.req.StudentRequest;
import com.roadjava.response.TableDTO;

public interface StudentService {
    TableDTO retrieveStudents(StudentRequest request);
    boolean add(StudentDO studentDO);

    StudentDO getById(int selectedStudentId);

    boolean update(StudentDO studentDO);

    boolean delete(int[] selectedStudentIds);
}
