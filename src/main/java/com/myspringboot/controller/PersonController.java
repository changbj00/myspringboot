package com.myspringboot.controller;

import com.myspringboot.pojo.Person;
import com.myspringboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAll(){
        return personService.getAll();
    }

    @PostMapping
    public Person createPerson(Person person){
        return personService.createPerson(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable String id){
        personService.deletePerson(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id,Person person){
        personService.updatePerson(id,person);
    }
    /**
     * 根据用户 id查找
     * 存在返回，不存在返回 null
     */
    @GetMapping("/{id}")
    public Person findPerson(@PathVariable String id){
        return personService.getPerson(id).orElse(null);
    }
    /**
     * 根据年龄段来查找
     */
    @GetMapping("/age/{min}/{max}")
    public List<Person> getPersonByAge(@PathVariable Integer min,@PathVariable Integer max){
        return personService.getPersonByAge(min, max);
    }
    /**
     * 根据用户名查找
     */
    @GetMapping("/name/{name}")
    public List<Person> getUserByName(@PathVariable String name) {
        return personService.getPersonByName(name);
    }

    /**
     * 根据用户描述模糊查找
     */
    @GetMapping("/description/{description}")
    public List<Person> getUserByDescription(@PathVariable String description) {
        return personService.getPersonByDescription(description);
    }

    /**
     * 根据多个检索条件查询
     */
    @GetMapping("/condition")
    public Page<Person> getUserByCondition(int size, int page, Person person) {
        return personService.getPersonByCondition(size, page, person);
    }
}
