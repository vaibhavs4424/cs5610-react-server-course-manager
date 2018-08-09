package edu.neu.coursemanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.coursemanager.models.Lesson;
import edu.neu.coursemanager.models.Topic;
import edu.neu.coursemanager.models.Widget;
import edu.neu.coursemanager.repositories.LessonRepository;
import edu.neu.coursemanager.repositories.TopicRepository;
import edu.neu.coursemanager.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {

	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	TopicRepository topicRepository;

	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}

	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
		widgetRepository.deleteAll();
		for (Widget widget : widgets) {
			widgetRepository.save(widget);
		}

	}

	@PostMapping("/api/topic/{topicId}/widget/save")
	public void createWidgets(@PathVariable("topicId") int topicId, @RequestBody List<Widget> widgets) {
		Optional<Topic> data = topicRepository.findById(topicId);

		if (data.isPresent()) {
			Topic topic = data.get();
			List<Widget> prevWidgets = topic.getWidgets();

			for (Widget widget : prevWidgets) {
				widgetRepository.deleteById(widget.getId());
			}

			for (Widget widget : widgets) {
				widget.setTopic(topic);
				widgetRepository.save(widget);
			}
		}
	}

	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			return topic.getWidgets();
		}
		return null;
	}

	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsForTopic2(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			return topic.getWidgets();
		}
		return null;
	}
}