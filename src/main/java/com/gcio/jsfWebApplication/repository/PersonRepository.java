package com.gcio.jsfWebApplication.repository;

import com.gcio.jsfWebApplication.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, Integer> {
    public List<Person> findAllByEditable (Boolean bool);
}
