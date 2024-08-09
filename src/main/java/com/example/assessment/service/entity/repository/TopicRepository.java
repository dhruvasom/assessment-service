package com.example.assessment.service.entity.repository;

import com.example.assessment.service.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository <Topic, String> {

}
