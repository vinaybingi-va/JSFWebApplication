package com.gcio.jsfWebApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "db_sequence")
public class DbSequence {

    @Id
    private String id;
    private int seq;
}
