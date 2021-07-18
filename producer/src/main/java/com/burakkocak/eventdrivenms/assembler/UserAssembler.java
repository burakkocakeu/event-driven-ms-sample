package com.burakkocak.eventdrivenms.assembler;

import com.burakkocak.eventdrivenms.entity.User;
import com.burakkocak.eventdrivenms.resource.UserResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserAssembler {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<User> toEntity(List<UserResource> userResourceList) {
        List<User> userList = new ArrayList<>();
        for (UserResource resource : userResourceList) {
            userList.add(toEntity(resource));
        }
        return userList;
    }

    public User toEntity(UserResource userResource) {
        User user = new User();
        user.setId(ObjectUtils.isEmpty(userResource.getId()) ? UUID.randomUUID() : userResource.getId());
        user.setName(userResource.getName());
        user.setSurname(userResource.getSurname());
        user.setFollowerList(userResource.getFollowerList().toString());
        user.setDate(userResource.getCreatedAt());
        return user;
    }

    public List<UserResource> toResource(List<User> userList) {
        List<UserResource> userResourceList = new ArrayList<>();
        for (User user : userList) {
            userResourceList.add(toResource(user));
        }
        return userResourceList;
    }

    public UserResource toResource(User user) {
        ArrayList<Integer> followers = new ArrayList<>();
        try { followers = mapper.convertValue(user.getFollowerList(), ArrayList.class); } catch (Exception e) { e.printStackTrace(); }
        UserResource resource = new UserResource();
        resource.setId(user.getId());
        resource.setName(user.getName());
        resource.setSurname(user.getSurname());
        resource.setFollowerList(followers);
        resource.setCreatedAt(user.getDate());
        return resource;
    }

}
