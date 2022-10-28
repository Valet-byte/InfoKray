package com.valet.infokray.service.person_service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PersonServiceWitValidationTest {

    @Autowired PersonServiceWitValidation personService;

    @MockBean PersonServiceWithoutValidation personServiceWithoutValidation;

    @Test
    void findById() {}

    @Test
    void findByName() {}

    @Test
    void save() {}

    @Test
    void existPerson() {}

    @Test
    void testExistPerson() {}
}
