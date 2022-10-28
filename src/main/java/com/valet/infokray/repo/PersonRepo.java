package com.valet.infokray.repo;

import com.valet.infokray.model.Person;

import java.util.List;

public interface PersonRepo {
    Person findById(Long id);
    List<Person> findByName(String name);
    Person findByEmail(String email);
    Person save(Person person);
    boolean existPerson(Long id);
    boolean existPerson(String email);
    boolean deleteAll();
}
