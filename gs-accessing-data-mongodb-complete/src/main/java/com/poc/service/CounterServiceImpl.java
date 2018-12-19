package com.poc.service;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.FindAndModifyOptions.*;
import org.springframework.data.mongodb.core.query.Update;

import com.poc.model.Counter;

public class CounterServiceImpl implements CounterService
{
    @Autowired
    private MongoOperations mongo;
    public long getNextIdSequence(String collectionName) {
        

        Counter cObj =    mongo.findOne(Query.query(Criteria.where("name").is(collectionName)),Counter.class);
        if (cObj==null) {
            Counter counter1 = new Counter();
            counter1.setName(collectionName);
            counter1.setSeqId(0L);
            mongo.insert(counter1);
        }
        Counter counter = mongo.findAndModify(
                Query.query(Criteria.where("name").is(collectionName)),
                new Update().inc("seqId", 1),
                FindAndModifyOptions.options().returnNew(true),
                Counter.class);

        return counter.getSeqId();
    	
    }
}
