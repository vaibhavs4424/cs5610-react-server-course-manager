package edu.neu.coursemanager.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.neu.coursemanager.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer> {
}
