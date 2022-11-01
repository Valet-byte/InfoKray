package com.valet.infokray.service.person_service;

import static org.junit.jupiter.api.Assertions.*;

import com.valet.infokray.model.DTO.PersonDTO;
import com.valet.infokray.model.Person;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PersonServiceWitValidationTest {

    @Autowired PersonServiceWitValidation personService;

    @MockBean PersonServiceWithoutValidation personServiceWithoutValidation;

    @Test
    void findById() {
        PersonDTO dto = new PersonDTO(1L, "Test");
        Mockito.when(personServiceWithoutValidation.findById(1L)).thenReturn(dto);

        assertAll(
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findById(null));

                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findById(-1L));

                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findById(-50L));

                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findById(0L));
                    assertEquals(dto, personService.findById(1L));
                });
    }

    @Test
    void findByName() {
        PersonDTO dto = new PersonDTO(1L, "Test2");
        Mockito.when(personServiceWithoutValidation.findByName("Test2")).thenReturn(List.of(dto));

        assertAll(
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findByName(null));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findByName(""));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findByName("123"));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findByName(
                                    "0123456789111213141516171819202122232425262728293031323334353637383940"));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.findByName("Я Люблю Токийского Гуля Канеки Кена"));
                    assertEquals(dto, personService.findByName("Test2").get(0));
                });
    }

    @Test
    void save() {
        Person validPerson = new Person(1L, "Valet-byte", "123654", "TestEmail231@gmail.com");
        Mockito.when(personServiceWithoutValidation.save(validPerson)).thenReturn(validPerson);
        Mockito.when(personServiceWithoutValidation.existPerson("TestEmail231@gmail.com"))
                .thenReturn(false);
        Mockito.when(personServiceWithoutValidation.existPerson("TestEmail123@gmail.com"))
                .thenReturn(true);

        assertAll(
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(Person.builder().build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(null));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(Person.builder().name("132").build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(Person.builder().name("").build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder()
                                            .name(
                                                    "0123456789111213141516171819202122232425262728293031323334353637383940")
                                            .build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(Person.builder().name("Valet-byte").build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder().password("").name("Valet-byte").build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder()
                                            .password("11111")
                                            .name("Valet-byte")
                                            .build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder()
                                            .password("Asefadw58156#@")
                                            .name("Valet-byte")
                                            .build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder()
                                            .email("159")
                                            .password("Asefadw58156#@")
                                            .name("Valet-byte")
                                            .build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder()
                                            .email("159@587")
                                            .password("Asefadw58156#@")
                                            .name("Valet-byte")
                                            .build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder()
                                            .email("@@@@@")
                                            .password("Asefadw58156#@")
                                            .name("Valet-byte")
                                            .build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder()
                                            .email("")
                                            .password("Asefadw58156#@")
                                            .name("Valet-byte")
                                            .build()));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.save(
                                    Person.builder()
                                            .email("TestEmail123@gmail.com")
                                            .password("Asefadw58156#@")
                                            .name("Valet-byte")
                                            .build()));
                    assertEquals(validPerson, personService.save(validPerson));
                });
    }

    @Test
    void existPerson() {
        Mockito.when(personServiceWithoutValidation.existPerson(1L)).thenReturn(true);
        Mockito.when(personServiceWithoutValidation.existPerson(2L)).thenReturn(false);
        Mockito.when(personServiceWithoutValidation.existPerson("TestEmail231@gmail.com"))
                .thenReturn(true);
        Mockito.when(personServiceWithoutValidation.existPerson("TestEmail123@gmail.com"))
                .thenReturn(false);

        assertAll(
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.existPerson(""));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.existPerson("AAAAAA"));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.existPerson("AAAAAA@"));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.existPerson("A@@AA@"));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.existPerson(-5L));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.existPerson("A@@AA@"));
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> personService.existPerson(-5L));
                    assertTrue(personService.existPerson(1L));
                    assertTrue(personService.existPerson("TestEmail231@gmail.com"));
                    assertFalse(personService.existPerson("TestEmail123@gmail.com"));
                    assertFalse(personService.existPerson(2L));
                });
    }
}
