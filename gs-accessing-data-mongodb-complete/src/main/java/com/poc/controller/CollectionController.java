package com.poc.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.model.GenericCollection;
import com.poc.service.CollectionRepository;
import com.poc.service.CounterService;


@RestController
@RequestMapping("/api")
public class CollectionController {

    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private CounterService counterService;
    @Autowired
    private MongoOperations mongo;
     

    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public GenericCollection createGenericCollection(@Valid @RequestBody GenericCollection collection) {
    	
    	if(mongo.getCollection(collection.getCollectionName()+"Collection")==null)//if collection name is not created
    		mongo.createCollection(collection.getCollectionName()+"Collection");//TO DO
    	
        collection.setVersion(counterService.getNextIdSequence(collection.getCollectionName()));
        collectionRepository.save(collection);
       
        return collection;
    }

    @RequestMapping(value = "/collections", method = RequestMethod.GET)
    public List<GenericCollection> getAllCollections() {
    	
       return collectionRepository.findAll();
    }
    
    @RequestMapping(value = "/collection/{cName}", method = RequestMethod.GET)
    public List<GenericCollection> getCollection(@PathVariable("cName") String cName) {
    	
       Query query = new Query(Criteria.where("collectionName").is(cName));
       List<GenericCollection> collections = mongo.find(query, GenericCollection.class, "collection");
       return collections.stream().sorted(new GenericCollection()).limit(1).collect(Collectors.toList());
    	
    	
    	
    }
    
    
}
