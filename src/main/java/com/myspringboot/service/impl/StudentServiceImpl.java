package com.myspringboot.service.impl;

import com.myspringboot.mapper.StudentMapper;
import com.myspringboot.pojo.Student;
import com.myspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student update(Student student) {
        this.studentMapper.update(student);
        return this.studentMapper.queryStudentById(student.getStudent_id());
    }

    @Override
    public void deleteStudentById(String id) {
        this.studentMapper.deleteStudentById(id);
    }

    @Override
    public Student queryStudentById(String id) {
        return studentMapper.queryStudentById(id);
    }
}
