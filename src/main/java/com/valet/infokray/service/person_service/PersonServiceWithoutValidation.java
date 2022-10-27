package com.valet.infokray.service.person_service;

import com.valet.infokray.config.annotation.WithoutValidation;
import com.valet.infokray.model.DTO.PersonDTO;
import com.valet.infokray.model.Person;
import com.valet.infokray.repo.PersonRepo;
import com.valet.infokray.service.encoding.EncodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@WithoutValidation
public class PersonServiceWithoutValidation implements PersonService {

    private final PersonRepo repo;
    private final EncodingService encodingService;

    @Override
    public PersonDTO findById(Long id) {
        return repo.findById(id).toDTO();
    }

    @Override
    public List<PersonDTO> findByName(String name) {
        return repo.findByName(name).stream().map(Person::toDTO).toList();
    }

    @Override
    public Person save(Person person) {
        person.setPassword(encodingService.encode(person.getPassword()));
        var res = repo.save(person);
        res.setPassword("NONE");
        return res;
    }

    @Override
    public boolean existPerson(Long id) {
        return repo.existPerson(id);
    }

    @Override
    public boolean existPerson(String email) {
        return repo.existPerson(email);
    }
}
