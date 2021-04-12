package com.gcio.jsfWebApplication.controller;

import com.gcio.jsfWebApplication.model.Person;
import com.gcio.jsfWebApplication.repository.PersonRepository;
import com.gcio.jsfWebApplication.service.SequenceGeneratorService;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.event.ActionEvent;
import java.util.List;


@Scope(value = "session")
@Component(value = "PersonList")
@ELBeanName(value = "PersonList")
public class PersonListController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SequenceGeneratorService sgservice;

    private List<Person> personList;

    private Person updatePerson = new Person();

    private Boolean otherRecordUpdating = false;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        personList = personRepository.findAll();
    }

    public List<Person> getPersonList() {
        try {
            loadData();
            return personList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateRecordUI (Person person) {
        try{
            updatePerson = new Person(person);
            person.setEditable(true);
            otherRecordUpdating = true;
            System.out.println("updateRecordUI - updatePerson:" + person);
            personRepository.save(person);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRecordDB (Person person) {
        try{
            person = updatePerson;
            person.setEditable(false);
            otherRecordUpdating = false;
            System.out.println("updateRecordDB - updatePerson:" + person);
            personRepository.save(person);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelUpdateRecordDB (Person person) {
        try{
            person.setEditable(false);
            otherRecordUpdating = false;
            System.out.println("updateRecordDB - updatePerson:" + person);
            personRepository.save(person);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disableEditable () {
        List<Person> editablePerson = personRepository.findAllByEditable(true);
        for (Person p : editablePerson) {
            p.setEditable(false);
        }
        otherRecordUpdating = false;
        personRepository.saveAll(editablePerson);
    }

    public void deleteRecord(Person person) {
            System.out.println("Deleting person records:" + person);
            if (person.editable) {
                otherRecordUpdating = false;
            }
            personRepository.delete(person);
            personList = personRepository.findAll();
    }

    public void printPersonDetails (Person person) {
        System.out.println("In PrintPersonDetails function");
        System.out.println("person details: " + person);
        System.out.println("person id: " + person.getId());
    }

    public Person getUpdatePerson () {
        return updatePerson;
    }

    public Boolean getOtherRecordUpdating() {
        return otherRecordUpdating;
    }
}
