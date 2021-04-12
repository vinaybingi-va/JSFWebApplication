package com.gcio.jsfWebApplication.controller;


import com.gcio.jsfWebApplication.model.Person;
import com.gcio.jsfWebApplication.repository.PersonRepository;
import com.gcio.jsfWebApplication.service.SequenceGeneratorService;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "personRegistration")
@ELBeanName(value = "personRegistration")
public class PersonRegisterController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SequenceGeneratorService sgservice;

    private Person person = new Person();

    public String save() {
        try {
            person.setId (sgservice.getSequenceNumber(person.SEQUENCE_NAME));
            personRepository.save(person);
            clear();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "/personDB.jsf?faces-redirect=true";
    }

    public void clear () {
        person.setFirstName (null);
        person.setLastName (null);
        person.setAddress (null);
    }

    public Person getPerson(){
        return person;
    }
}
