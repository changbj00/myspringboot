package com.myspringboot.service;

import com.myspringboot.myspringbootApplication;
import com.myspringboot.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {myspringbootApplication.class,MockServletContext.class})
public class PersonServiceTest {
    @Autowired
    private PersonService personService;
    private Person person;

    @Test
    public void test(){
        personService.getAll();
    }

}