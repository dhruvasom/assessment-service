package com.example.assessment.service.entity.repository;


import com.example.assessment.service.dto.Difficulty;
import com.example.assessment.service.dto.Format;
import com.example.assessment.service.entity.QuestionDetails;
import com.example.assessment.service.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionDetails, String> {
    List<QuestionDetails> findAllByTopic(Topic topic);
    List<QuestionDetails> findAllByTopicAndDifficultyAndFormat(Topic topic, Difficulty difficulty, Format format);

    List<QuestionDetails> findAllByTopicAndDifficulty(Topic topic, Difficulty difficulty);

    List<QuestionDetails> findAllByTopicAndFormat(Topic topic, Format format);
}
