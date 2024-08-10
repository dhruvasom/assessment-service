package com.example.assessment.service.service;

import com.example.assessment.service.dto.TopicDTO;
import com.example.assessment.service.entity.Topic;
import com.example.assessment.service.entity.repository.TopicRepository;
import com.example.assessment.service.exceptions.ResourceNotFoundException;
import com.example.assessment.service.mapper.TopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    TopicService (TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }
    public TopicDTO saveTopic(TopicDTO topicDTO) {
        Topic topicToSave = topicMapper.toTopic(topicDTO);
        topicToSave.setId(UUID.randomUUID().toString());
        return topicMapper.fromTopic(topicRepository.save(topicToSave));
    }

    public List<TopicDTO> findAll() {
        return topicRepository.findAll().stream().map(topicMapper::fromTopic).toList();
    }

    public TopicDTO findById(String topicId) {
        return topicRepository.findById(topicId).map(topicMapper::fromTopic)
                .orElseThrow(()->new ResourceNotFoundException("Topic with id :"+ topicId + " not found"));
    }

    protected Topic findTopicById(String topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(()->new ResourceNotFoundException("Topic with id :"+ topicId + " not found"));
    }

    public TopicDTO updateTopic(TopicDTO topicDTO) {
      Topic topicToUpdate = topicRepository.findById(topicDTO.id())
              .orElseThrow(()->new ResourceNotFoundException("Topic with id :"+topicDTO.id()+ " not found"));
      topicToUpdate.setName(topicDTO.name());
      topicToUpdate.setDetail(topicDTO.detail());
      return topicMapper.fromTopic(topicRepository.save(topicToUpdate));
    }

    public String deleteById(String topicId) {
        if (!topicRepository.existsById(topicId)) {
          throw new ResourceNotFoundException("Topic with id :"+ topicId + " not found");
        }
        topicRepository.deleteById(topicId);
        return topicId;
    }
}
