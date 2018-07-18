package edu.neu.coursemanager.repositories;
import org.springframework.data.repository.CrudRepository;
import edu.neu.coursemanager.models.Course;


public interface CourseRepository extends CrudRepository<Course, Integer>{

}
