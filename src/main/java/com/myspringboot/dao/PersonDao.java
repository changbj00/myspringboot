package com.myspringboot.dao;

import com.myspringboot.pojo.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends MongoRepository<Person,String> {

    /**
     * 根据年龄段来查找
     * @param min
     * @param max
     * @return
     */
    List<Person> findByAgeBetween(Integer min,Integer max);

    /**
     * 通过年龄段，用户名，描述（模糊查询）
     * @param min
     * @param max
     * @param name
     * @param description
     * @return
     */
    List<Person> findByAgeBetweenAndNameEqualsAndDescriptionIsLike(Integer min,Integer max,String name,String description);
    /**
     * 更具描述来模糊查询用户
     *
     * @param description 描述
     * @return List<User>
     */
    List<Person> findByDescriptionIsLike(String description);

    /**
     * 通过用户名查询
     *
     * @param name 用户名
     * @return List<User>
     */
    List<Person> findByNameEquals(String name);
}
