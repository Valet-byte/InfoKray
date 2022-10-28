package com.valet.infokray.service.person_service;

import com.valet.infokray.model.DTO.PersonDTO;
import com.valet.infokray.model.Person;
import com.valet.infokray.repo.PersonRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceWithoutValidationTest {
    @Autowired
    PersonServiceWithoutValidation personService;

    @MockBean
    PersonRepo personRepo;

    @Test
    void findById() {
        Mockito.when(personRepo.findById(1L)).thenReturn(Person.builder().id(1L).build());

        PersonDTO person = personService.findById(1L);

        assertEquals(person.getId(), 1L);
    }

    @Test
    void findByName() {
        Mockito.when(personRepo.findByName("Name")).thenReturn(List.of(Person.builder().name("Name").id(1L).build(), Person.builder().name("Name").id(2L).build()));

        List<PersonDTO> persons = personService.findByName("Name");

        assertAll(() -> {
            assertEquals(persons.size(), 2);
            assertEquals(persons.stream().filter(personDTO -> personDTO.getName().equals("Name")).toList().size(), 2);
        });
    }

    @Test
    void save() {
        Person person = new Person(null, "Test", "Test", "Test@test");
        Mockito.when(personRepo.save(person)).thenReturn(new Person(1L, "Test", "Test", "Test@test"));

        Person p = personService.save(person);
        assertAll(() -> {
            assertEquals(p.getPassword(), "NONE");
            assertEquals(p.getEmail(), person.getEmail());
            assertNotNull(p.getId());
        });
    }

    @Test
    void existPerson() {
        Mockito.when(personRepo.existPerson("Test@test")).thenReturn(true);
        Mockito.when(personRepo.existPerson("Test")).thenReturn(false);
        Mockito.when(personRepo.existPerson(1L)).thenReturn(true);
        Mockito.when(personRepo.existPerson(2L)).thenReturn(false);

        assertAll(() -> {
            assertTrue(personService.existPerson(1L));
            assertFalse(personService.existPerson(2L));

            assertTrue(personService.existPerson("Test@test"));
            assertFalse(personService.existPerson("Test"));
        });
    }
}