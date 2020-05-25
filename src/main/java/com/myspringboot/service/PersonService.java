package com.myspringboot.service;

import com.myspringboot.dao.PersonDao;
import com.myspringboot.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;
@Service
public class PersonService {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Person> getAll(){
        return personDao.findAll();
    }

    public Optional<Person> getPerson(String id){
        return personDao.findById(id);
    }

    public Person createPerson(Person person){
        person.setId(null);
        return personDao.save(person);
    }

    public void deletePerson(String id){
        this.personDao.findById(id).ifPresent(person -> this.personDao.deleteById(id));

    }

    public void updatePerson(String id,Person person){
        this.personDao.findById(id).ifPresent(
                p ->{
                    p.setName(person.getName());
                    p.setAge(person.getAge());
                    p.setDescription(person.getDescription());
                    this.personDao.save(p);
                }
        );
    }
    public List<Person> getPersonByAge(Integer from, Integer to) {
        return this.personDao.findByAgeBetween(from, to);
    }

    public List<Person> getPersonByName(String name) {
        return this.personDao.findByNameEquals(name);
    }

    public List<Person> getPersonByDescription(String description) {
        return this.personDao.findByDescriptionIsLike(description);
    }
    public Page<Person> getPersonByCondition(int size,int page,Person person){

        Query query=new Query();
        Criteria criteria=new Criteria();
        if(!StringUtils.isEmpty(person.getName())){
            criteria.and("name").is(person.getName());
        }
        if(!StringUtils.isEmpty(person.getDescription())){
            criteria.and("description").regex(person.getDescription());
        }
        query.addCriteria(criteria);

        Sort sort = new Sort(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<Person> users = mongoTemplate.find(query.with(pageable), Person.class);
        return PageableExecutionUtils.getPage(users, pageable, () -> mongoTemplate.count(query, Person.class));
    }


}
