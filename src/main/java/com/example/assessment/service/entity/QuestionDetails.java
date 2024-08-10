package com.example.assessment.service.entity;

import com.example.assessment.service.dto.Difficulty;
import com.example.assessment.service.dto.Format;
import jakarta.persistence.*;

@Entity
public class QuestionDetails {
    @Id
    String questionId;
    String question;

    Difficulty difficulty;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    Topic topic;
    Format format;

    public QuestionDetails(String questionId, String question, Difficulty difficulty, Topic topic, Format format) {
        this.questionId = questionId;
        this.question = question;
        this.difficulty = difficulty;
        this.topic = topic;
        this.format = format;
    }

    public QuestionDetails() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

}
