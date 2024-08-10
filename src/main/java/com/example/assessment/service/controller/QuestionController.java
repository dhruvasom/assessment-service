package com.example.assessment.service.controller;

import com.example.assessment.service.dto.Difficulty;
import com.example.assessment.service.dto.Format;
import com.example.assessment.service.dto.QuestionDetailsDTO;
import com.example.assessment.service.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/question")
    public ResponseEntity<QuestionDetailsDTO> addQuestion(QuestionDetailsDTO questionDetailsDTO){
        return new ResponseEntity<>(questionService.addQuestion(questionDetailsDTO), HttpStatus.CREATED);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<QuestionDetailsDTO> getQuestion(@PathVariable String questionId){
        return new ResponseEntity<>(questionService.findQuestionById(questionId), HttpStatus.OK);
    }

    @GetMapping("question/")
    public ResponseEntity<List<QuestionDetailsDTO>> getQuestions(
            @RequestParam(value = "topic") String topic,
            @RequestParam(value = "difficulty", required = false) Difficulty difficulty,
            @RequestParam(value = "questionType", required = false)Format format
            ) {
        return new ResponseEntity<>(questionService.findQuestionsByFilter( topic, difficulty, format), HttpStatus.OK);
    }

    @PutMapping("/question")
    public ResponseEntity<QuestionDetailsDTO> updateQuestion(QuestionDetailsDTO questionDetailsDTO) {
        return  new ResponseEntity<>(questionService.updateQuestion(questionDetailsDTO), HttpStatus.OK);
    }

    @DeleteMapping("/question/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String questionId){
        return new ResponseEntity<>(questionService.deleteQuestion(questionId), HttpStatus.OK);
    }
}
