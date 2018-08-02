package edu.neu.coursemanager.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.neu.coursemanager.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

}
