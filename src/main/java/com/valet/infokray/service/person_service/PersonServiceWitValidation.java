package com.valet.infokray.service.person_service;

import com.valet.infokray.config.annotation.WithValidation;
import com.valet.infokray.config.annotation.WithoutValidation;
import com.valet.infokray.model.DTO.PersonDTO;
import com.valet.infokray.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@WithValidation
public class PersonServiceWitValidation implements PersonService {

    @WithoutValidation
    private final PersonService personService;

    @Override
    public PersonDTO findById(Long id) {
        if (id != null && id > 0){
            return personService.findById(id);
        } else throw new IllegalArgumentException("id not must be less 0!");

    }

    @Override
    public List<PersonDTO> findByName(String name) throws IllegalArgumentException {
        if (name != null && name.length() > 4 && name.length() < 50 ){
            if (name.split(" ").length <= 4){
                return personService.findByName(name);
            }else throw new IllegalArgumentException("name must contain up to 4 words!");
        } else throw new IllegalArgumentException("name.length() must be greater than 4 and less than or equal to 50!");

    }

    @Override
    public Person save(Person person) throws IllegalArgumentException {
        if (person.getName().split(" ").length <= 4){
            if (person.getName().length() > 4 && person.getName().length() < 50){
                if (person.getPassword().length() >= 6){
                    if (EmailValidator.validate(person.getEmail())){
                        if (!existPerson(person.getEmail())){
                            return personService.save(person);
                        } else throw new IllegalArgumentException("Person already exist!");
                    } else throw new IllegalArgumentException("Not valid email !");
                } else throw new IllegalArgumentException("password.length() must be greater than 6");
            } else throw new IllegalArgumentException("name.length() must be greater than 4 and less than or equal to 50");
        } throw new IllegalArgumentException("name must contain up to 4 words!");
    }

    @Override
    public boolean existPerson(Long id) {
        if (id != null && id > 0){
            return personService.existPerson(id);
        } else throw new IllegalArgumentException("id not must be less 0!");
    }

    @Override
    public boolean existPerson(String email) {
        if (EmailValidator.validate(email)){
            return personService.existPerson(email);
        } else throw new IllegalArgumentException("Not valid email !");
    }

    private static class EmailValidator{
        private static final String EMAIL_PATTERN =
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+";

        public static boolean validate(final String hex) {
            var pattern = Pattern.compile(EMAIL_PATTERN);
            var matcher = pattern.matcher(hex);

            return matcher.matches();
        }
    }
}
