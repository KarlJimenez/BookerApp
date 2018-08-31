package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServiceOb;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceOb, Integer> {

}
