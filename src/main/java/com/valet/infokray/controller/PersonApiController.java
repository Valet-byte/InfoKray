package com.valet.infokray.controller;

import com.valet.infokray.model.DTO.PersonDTO;
import com.valet.infokray.model.Person;
import com.valet.infokray.service.person_service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonApiController {

    private final PersonService personService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration (@RequestBody Person person){
        try {
            return ResponseEntity.ok(personService.save(person));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public PersonDTO login(@AuthenticationPrincipal Person person){
        return person.toDTO();
    }

}
