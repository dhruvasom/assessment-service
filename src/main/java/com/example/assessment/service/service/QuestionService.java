package com.example.assessment.service.service;

import com.example.assessment.service.dto.Difficulty;
import com.example.assessment.service.dto.Format;
import com.example.assessment.service.dto.QuestionDetailsDTO;
import com.example.assessment.service.entity.QuestionDetails;
import com.example.assessment.service.entity.Topic;
import com.example.assessment.service.entity.repository.QuestionRepository;
import com.example.assessment.service.exceptions.ResourceNotFoundException;
import com.example.assessment.service.mapper.QuestionDetailsMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionDetailsMapper questionDetailsMapper;

    private final TopicService topicService;

    QuestionService(QuestionRepository questionRepository, QuestionDetailsMapper questionDetailsMapper, TopicService topicService) {
        this.questionDetailsMapper = questionDetailsMapper;
        this.questionRepository = questionRepository;
        this.topicService = topicService;
    }
    public QuestionDetailsDTO addQuestion(QuestionDetailsDTO questionDetailsDTO) {
        Topic topic = topicService.findTopicById(questionDetailsDTO.topic());
        QuestionDetails questionDetails = questionDetailsMapper.toQuestionDetails(questionDetailsDTO, topic);
        questionDetails.setQuestionId(UUID.randomUUID().toString());
        QuestionDetails savedQuestion = questionRepository.save(questionDetails);
        return questionDetailsMapper.fromQuestionDetails(savedQuestion);
    }

    public QuestionDetailsDTO findQuestionById(String questionId) {
       return  questionRepository.findById(questionId).map(questionDetailsMapper::fromQuestionDetails)
                .orElseThrow(()-> new ResourceNotFoundException(""));
    }

    public List<QuestionDetailsDTO> findQuestionsByFilter(String topicId, Difficulty difficulty, Format format) {
        List<QuestionDetails> questions;
        Topic topic = topicService.findTopicById(topicId);
         if (difficulty !=null && format !=null) {
             questions = questionRepository.findAllByTopicAndDifficultyAndFormat(topic,difficulty,format);
         } else if (difficulty !=null) {
             questions = questionRepository.findAllByTopicAndDifficulty(topic,difficulty);
         } else if (format !=null) {
             questions = questionRepository.findAllByTopicAndFormat(topic,format);
         } else {
             questions = questionRepository.findAllByTopic(topic);
         }
         return questions.stream().map(questionDetailsMapper::fromQuestionDetails).toList();
    }

    public QuestionDetailsDTO updateQuestion(QuestionDetailsDTO questionDetailsDTO) {
        Topic topic = topicService.findTopicById(questionDetailsDTO.topic());
        QuestionDetails questionDetails = questionDetailsMapper.toQuestionDetails(questionDetailsDTO, topic);
        QuestionDetails savedQuestion = questionRepository.save(questionDetails);
        return questionDetailsMapper.fromQuestionDetails(savedQuestion);
    }

    public String deleteQuestion(String questionId) {
       if(!questionRepository.existsById(questionId)) {
           throw new ResourceNotFoundException("");
       }
       questionRepository.deleteById(questionId);
       return questionId;
    }
}
