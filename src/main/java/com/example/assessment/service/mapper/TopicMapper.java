package com.example.assessment.service.mapper;

import com.example.assessment.service.dto.TopicDTO;
import com.example.assessment.service.entity.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {
    public Topic toTopic(TopicDTO topicDTO) {
        return new Topic(topicDTO.id(),topicDTO.name(), topicDTO.detail());
    }

    public TopicDTO fromTopic(Topic topic) {
        return new TopicDTO(topic.getId(),topic.getName(),topic.getDetail());
    }
}
