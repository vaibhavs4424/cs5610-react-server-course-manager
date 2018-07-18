package edu.neu.coursemanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.coursemanager.models.Course;
import edu.neu.coursemanager.models.Module;
import edu.neu.coursemanager.repositories.CourseRepository;
import edu.neu.coursemanager.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*")
public class ModuleServices {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;

	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") int courseId, @RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);

		if (data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			return moduleRepository.save(newModule);

		}
		return null;
	}

	@DeleteMapping("/api/module/{mId}")
	public void deleteModule(@PathVariable("mId") int moduleId) {
		moduleRepository.deleteById(moduleId);
	}

	@GetMapping("/api/module/{id}")
	public Module findModuleById(@PathVariable("id") int id) {
		Optional<Module> data = moduleRepository.findById(id);

		if (data.isPresent())
			return data.get();

		return null;

	}

	@GetMapping("/api/module")
	public Iterable<Module> findAllModules() {
		return moduleRepository.findAll();
	}

	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);

		if (data.isPresent()) {
			Course course = data.get();
			return course.getModules();

		}
		return null;
	}

}
