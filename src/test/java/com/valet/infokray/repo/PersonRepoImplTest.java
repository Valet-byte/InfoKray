package com.valet.infokray.repo;

import static org.junit.jupiter.api.Assertions.*;

import com.valet.infokray.model.Person;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonRepoImplTest {

    @Autowired PersonRepoImpl personRepo;

    @AfterEach
    void after() {
        personRepo.deleteAll();
    }

    @Test
    void findById() {
        Person person = new Person(null, "Roma", "111111", "rsumakov20@gmail.com");
        Person p = personRepo.save(person);
        Person p2 = personRepo.findById(p.getId());
        assertEquals(p2, p);
    }

    @Test
    void findByName() {
        Person person1 = new Person(null, "Roma", "111111", "rsumakov20@gmail.com");
        Person person2 = new Person(null, "Roma", "111111", "rsumakov21@gmail.com");

        personRepo.save(person1);
        personRepo.save(person2);

        List<Person> persons = personRepo.findByName(person1.getName());

        assertAll(
                () -> {
                    assertEquals(persons.get(0).getName(), person1.getName());
                    assertEquals(persons.get(0).getName(), person2.getName());
                    assertEquals(persons.get(1).getName(), person1.getName());
                    assertEquals(persons.get(1).getName(), person2.getName());
                    assertEquals(persons.get(0).getPassword(), person1.getPassword());
                    assertEquals(persons.get(0).getPassword(), person2.getPassword());
                    assertEquals(persons.get(1).getPassword(), person1.getPassword());
                    assertEquals(persons.get(1).getPassword(), person2.getPassword());
                });
    }

    @Test
    void findByEmail() {
        Person person = new Person(null, "Roma", "111111", "rsumakov20@gmail.com");
        Person p = personRepo.save(person);
        Person p2 = personRepo.findByEmail(p.getEmail());
        assertEquals(p2, p);
    }

    @Test
    void save() {
        Person person = new Person(null, "Roma", "111111", "rsumakov20@gmail.com");
        Person p = personRepo.save(person);
        person.setId(p.getId());
        assertEquals(person, p);
    }

    @Test
    void existPerson() {
        Person person = new Person(null, "Roma", "111111", "rsumakov20@gmail.com");
        Person p = personRepo.save(person);

        assertAll(
                () -> {
                    assertTrue(personRepo.existPerson(person.getEmail()));
                    assertTrue(personRepo.existPerson(p.getId()));
                });
    }
}
