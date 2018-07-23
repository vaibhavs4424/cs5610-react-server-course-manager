package edu.neu.coursemanager.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.neu.coursemanager.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

}
