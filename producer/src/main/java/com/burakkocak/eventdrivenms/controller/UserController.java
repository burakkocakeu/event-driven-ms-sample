package com.burakkocak.eventdrivenms.controller;

import com.burakkocak.eventdrivenms.assembler.UserAssembler;
import com.burakkocak.eventdrivenms.entity.User;
import com.burakkocak.eventdrivenms.exception.UserAlreadyExistsException;
import com.burakkocak.eventdrivenms.resource.UserResource;
import com.burakkocak.eventdrivenms.resource.response.ResponseEntityResource;
import com.burakkocak.eventdrivenms.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserAssembler userAssembler;
    @Autowired
    private UserService userService;
    @Autowired
    private KafkaTemplate<String, JsonNode> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String TOPIC = "User_Created";

    /**
     * Handle a single producer
     * @param userResource
     * @return
     */
    @PostMapping
    private ResponseEntity<ResponseEntityResource> create(@RequestBody UserResource userResource) {
        ResponseEntityResource entityResource = new ResponseEntityResource();
        User user = userAssembler.toEntity(userResource);
        try {
            userService.create(user);
            kafkaTemplate.send(TOPIC, mapper.convertValue(new UserResource(user.getId(), userResource.getFollowerList()), JsonNode.class));
            entityResource.getDetails().getSuccess().add(user.getName());
            entityResource.setMessage("User '" + user.getName() + "' is saved successfully!");
            logger.info("User '" + user.getName() + "' is saved successfully! {}", user);
        } catch (UserAlreadyExistsException e) {
            entityResource.getDetails().getFail().add(user.getName());
            entityResource.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(entityResource, entityResource.getDetails().getFail().size() > 0 ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
    }

    /**
     * Handle a list of producers
     * @param userResourceList
     * @return
     */
    @PostMapping("/bulk")
    private ResponseEntity<ResponseEntityResource> create(@RequestBody List<UserResource> userResourceList) {
        ResponseEntityResource entityResource = new ResponseEntityResource();
        for (UserResource userResource : userResourceList) {
            User user = userAssembler.toEntity(userResource);
            try {
                userService.create(user);
                kafkaTemplate.send(TOPIC, mapper.convertValue(new UserResource(user.getId(), userResource.getFollowerList()), JsonNode.class));
                entityResource.getDetails().getSuccess().add(user.getName());
                logger.info("User '" + user.getName() + "' is saved successfully! {}", user);
            } catch (UserAlreadyExistsException e) {
                entityResource.getDetails().getFail().add(user.getName());
                if (StringUtils.isEmpty(entityResource.getMessage()))
                    entityResource.setMessage(e.getMessage());
            }
        }
        if (StringUtils.isEmpty(entityResource.getMessage()))
            entityResource.setMessage("Users are saved successfully!");
        return new ResponseEntity<>(entityResource, entityResource.getDetails().getFail().size() > 0 ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
    }
}


