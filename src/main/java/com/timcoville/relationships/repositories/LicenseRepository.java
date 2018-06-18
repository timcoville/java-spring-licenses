package com.timcoville.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.timcoville.relationships.models.License;

@Repository
public interface LicenseRepository extends CrudRepository<License, Long>{
	License findByPerson_id(Long id);
	List<License> findAllByOrderByIdDesc();

}
