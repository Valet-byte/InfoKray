package com.valet.infokray.service.person_service;

import com.valet.infokray.model.DTO.PersonDTO;
import com.valet.infokray.model.Person;
import java.util.List;

public interface PersonService {
    PersonDTO findById(Long id);

    List<PersonDTO> findByName(String name) throws IllegalArgumentException;

    Person save(Person person) throws IllegalArgumentException;

    boolean existPerson(Long id);

    boolean existPerson(String email);
}
