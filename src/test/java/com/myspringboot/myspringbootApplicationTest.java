package com.myspringboot;

import com.myspringboot.pojo.Student;
import com.myspringboot.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = myspringbootApplication.class)
public class myspringbootApplicationTest {
    @Autowired
    private StudentService studentService;
    @Test
    public void test01() {
        Student student = studentService.queryStudentById("111");
        System.out.println("学号"+student.getStudent_id()+"学生姓名"+student.getName());
        student.setName("康康");
        this.studentService.update(student);
        Student student1 = studentService.queryStudentById("111");
        System.out.println("学号"+student.getStudent_id()+"学生姓名"+student.getName());

    }
}