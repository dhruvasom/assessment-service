package com.example.assessment.service.mapper;

import com.example.assessment.service.dto.QuestionDetailsDTO;
import com.example.assessment.service.entity.QuestionDetails;
import com.example.assessment.service.entity.Topic;
import org.springframework.stereotype.Component;

@Component
public class QuestionDetailsMapper {

    public QuestionDetails toQuestionDetails(QuestionDetailsDTO questionDetailsDTO, Topic topic) {
        return new QuestionDetails(
                questionDetailsDTO.id(),
                questionDetailsDTO.Question(),
                questionDetailsDTO.difficulty(),
                topic,
                questionDetailsDTO.format()
        );
    }

    public QuestionDetailsDTO fromQuestionDetails(QuestionDetails question) {
        return new QuestionDetailsDTO(
                question.getQuestionId(),
                question.getQuestion(),
                question.getDifficulty(),
                question.getTopic().getId(),
                question.getFormat()
        );
    }
}
