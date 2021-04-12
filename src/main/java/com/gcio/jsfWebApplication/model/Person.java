package com.gcio.jsfWebApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "Person")
public class Person {

    // transient variables will not be persisted
    @Transient
    public static final String SEQUENCE_NAME="sequence_value";

    public boolean editable=false;

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String address;

    public Person() {
    }

    // used to make copy by value
    public Person (Person person) {
        this.firstName = person.firstName;
        this.lastName = person.lastName;
        this.address = person.address;
        this.id = person.id;
    }
}
