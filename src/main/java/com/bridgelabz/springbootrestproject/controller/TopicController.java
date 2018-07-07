package com.bridgelabz.springbootrestproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.springbootrestpractice.model.Topic;

@RestController
public class TopicController {

	@RequestMapping("/topics")
	public List<Topic> getTopics() {
		List<Topic> list = new ArrayList<>();
		Topic t1 = new Topic("Spring", 1, "Spring Description");
		list.add(t1);
		Topic t2 = new Topic("Java", 2, "Java Description");
		list.add(t2);
		return list;
	}
}
