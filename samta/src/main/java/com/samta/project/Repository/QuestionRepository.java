package com.samta.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.samta.project.model.Question;

public interface QuestionRepository extends MongoRepository<Question, String>  {
	
	

}
