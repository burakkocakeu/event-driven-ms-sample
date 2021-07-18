package com.burakkocak.eventdrivenms.listener;

import com.burakkocak.eventdrivenms.entity.UserFollower;
import com.burakkocak.eventdrivenms.exception.FollowerAlreadyExistsException;
import com.burakkocak.eventdrivenms.service.UserFollowerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaConsumer {

    @Autowired
    private UserFollowerService userFollowerService;
    private static final String TOPIC = "User_Created";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = TOPIC, groupId = "follower")
    public void consumeUserFollower(JsonNode follower) {
        try {
            logger.info("Received: {}", follower);
            ObjectNode node = mapper.convertValue(follower, ObjectNode.class);
            UserFollower userFollower = new UserFollower();
            userFollower.setId(UUID.fromString(node.get("id").asText()));
            userFollower.setFollowerList(node.get("followerList").toString());
            userFollowerService.create(userFollower);
        } catch (FollowerAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

}
