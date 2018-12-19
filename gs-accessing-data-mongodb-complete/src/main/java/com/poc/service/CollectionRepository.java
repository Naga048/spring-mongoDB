package com.poc.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.model.GenericCollection;

public interface CollectionRepository extends MongoRepository<GenericCollection,String> {
}
