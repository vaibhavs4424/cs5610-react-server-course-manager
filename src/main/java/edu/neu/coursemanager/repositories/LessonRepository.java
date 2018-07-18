package edu.neu.coursemanager.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.neu.coursemanager.models.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
