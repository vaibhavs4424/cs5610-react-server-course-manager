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

import edu.neu.coursemanager.models.Lesson;
import edu.neu.coursemanager.models.Module;
import edu.neu.coursemanager.models.Topic;
import edu.neu.coursemanager.repositories.LessonRepository;
import edu.neu.coursemanager.repositories.ModuleRepository;
import edu.neu.coursemanager.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*")
public class TopicServices {

	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	TopicRepository topicRepository;

	@GetMapping("/api/topic")
	public Iterable<Topic> findAllTopics() {
		return topicRepository.findAll();
	}

	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public Topic createTopic(@PathVariable("moduleId") int courseId, @PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId, @RequestBody Topic newTopic) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);

		if (data.isPresent()) {
			Lesson lesson = data.get();
			newTopic.setLesson(lesson);
			return topicRepository.save(newTopic);

		}
		return null;
	}

	@DeleteMapping("/api/topic/{topicId}")
	public void deleteTopic(@PathVariable("topicId") int topicId) {
		topicRepository.deleteById(topicId);
	}

	@GetMapping("/api/course/{cid}/module/{mid}/lesson/{lessonId}/topic")
	public List<Topic> findAllTopicsforLesson(@PathVariable("cid") int cid, @PathVariable("mid") int mid,
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);

		if (data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getTopics();

		}
		return null;
	}

}
