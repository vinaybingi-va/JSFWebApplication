package com.gcio.jsfWebApplication.service;


import com.gcio.jsfWebApplication.model.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber (String sequenceName) {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);

        // modify operation to increment sequence number in getSequence document
        DbSequence counter = mongoOperations.findAndModify(
                                query,
                                update,
                                FindAndModifyOptions.options().returnNew(true).upsert(true),
                                DbSequence.class);

        // returns 1 for the first time. else returns the updated sequence_value
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
