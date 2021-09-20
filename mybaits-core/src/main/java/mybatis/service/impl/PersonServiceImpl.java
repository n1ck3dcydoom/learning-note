package mybatis.service.impl;

import mybatis.entity.Person;
import mybatis.mapper.PersonMapper;
import mybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person getPersonById(int id) {
        return personMapper.selectById(id);
    }
}