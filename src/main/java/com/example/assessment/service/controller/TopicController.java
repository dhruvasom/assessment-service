package com.example.assessment.service.controller;

import com.example.assessment.service.dto.TopicDTO;
import com.example.assessment.service.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicController {
    private final TopicService topicService;

    TopicController (TopicService topicService){
        this.topicService = topicService;
    }

    @PostMapping("/topic")
    public ResponseEntity<TopicDTO> createTopic(TopicDTO topicDTO){
        return new ResponseEntity<>(topicService.saveTopic(topicDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all-topics")
    public ResponseEntity<List<TopicDTO>> allTopics(){
        return new ResponseEntity<>(topicService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/topics/{topicId}")
    public ResponseEntity<Optional<TopicDTO>> findTopic(@PathVariable String topicId){
        return new ResponseEntity<>(topicService.findById(topicId), HttpStatus.OK);
    }

    @PutMapping("/topic")
    public ResponseEntity<TopicDTO> updateTopic(TopicDTO topicDTO){
        return new ResponseEntity<>(topicService.updateTopic(topicDTO), HttpStatus.OK);
    }

    @DeleteMapping("/topic/{topicId}")
    public ResponseEntity<String> deleteTopic(@PathVariable String topicId){
        topicService.deleteById(topicId);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
