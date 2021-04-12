package com.gcio.jsfWebApplication.controller;

import com.gcio.jsfWebApplication.model.Person;
import com.gcio.jsfWebApplication.repository.PersonRepository;
import com.gcio.jsfWebApplication.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component(value = "personController")
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SequenceGeneratorService sgservice;

    @PostMapping("/addPerson")
    public String savePerson(@RequestBody Person person) {
        person.setId (sgservice.getSequenceNumber(person.SEQUENCE_NAME));
        personRepository.save(person);
        return "Saved person details to db";
    }

    @GetMapping("/findAllPersonDetails")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/findPersonDetails/{id}")
    public Optional<Person> getPerson(@PathVariable int id) {
        return personRepository.findById(id);
    }

    @DeleteMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable int id) {
        personRepository.deleteById(id);
        return "person deleted with id: " + id;
    }
}
