package com.valet.infokray.service.person_service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceWitValidationTest {

    @Autowired
    PersonServiceWitValidation personService;

    @MockBean
    PersonServiceWithoutValidation personServiceWithoutValidation;

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void save() {
    }

    @Test
    void existPerson() {
    }

    @Test
    void testExistPerson() {
    }
}