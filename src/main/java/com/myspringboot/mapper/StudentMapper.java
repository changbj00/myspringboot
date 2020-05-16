package com.myspringboot.mapper;

import com.myspringboot.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;

import java.util.Date;
import java.util.List;

@Mapper
@CacheConfig(cacheNames = "student")
public interface StudentMapper {

    @Select("SELECT * FROM student")
    List<Student> findAll();

    @Update("update student set name=#{name},sex=#{sex},age=#{age} where student_id=#{student_id}")
    int update(Student student);

    @Delete("delete from student where student_id=#{student_id}")
    void deleteStudentById(String student_id);

    @Select("select * from student where student_id=#{student_id}")
    @Results(id = "student",value = {
            @Result(property = "id",column = "student_id",javaType = String.class),
            @Result(property = "name",column = "name",javaType = String.class),
            @Result(property = "sex",column = "sex",javaType = String.class),
            @Result(property = "age",column = "age",javaType = String.class),
            @Result(property = "birthday",column = "birthday",javaType = Date.class)})
    Student queryStudentById(String student_id);
}

