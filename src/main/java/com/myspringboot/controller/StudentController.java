package com.myspringboot.controller;

import com.myspringboot.mapper.StudentMapper;
import com.myspringboot.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    @RequestMapping("/listStudent")
    public String listStudent(Model model){
        List<Student> students=studentMapper.findAll();
        model.addAttribute("students",students);
        return "listStudent";
    }

    @RequestMapping("/student")
    public String index(Model model){
        List<Student> list=new ArrayList<Student>();
        list=studentMapper.findAll();
        model.addAttribute("studentList", list);
        return "student";

    }


}
