package com.burakkocak.eventdrivenms.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResource {
    private @NotBlank UUID id;
    private @NotBlank String name;
    private String surname;
    private ArrayList<Integer> followerList;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public UserResource() {}

    public UserResource(@NotBlank UUID id, ArrayList<Integer> followerList) {
        this.id = id;
        this.followerList = followerList;
    }

    public UserResource(@NotBlank UUID id, @NotBlank String name, String surname, ArrayList<Integer> followerList, Date createdAt) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.followerList = followerList;
        this.createdAt = createdAt;
    }

    public @NotBlank UUID getId() {
        return id;
    }

    public void setId(@NotBlank UUID id) {
        this.id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<Integer> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(ArrayList<Integer> followerList) {
        this.followerList = followerList;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserResource copy() {
        UserResource resource = new UserResource();
        resource.id = this.id;
        resource.name = this.name;
        resource.surname = this.surname;
        resource.followerList = this.followerList;
        resource.createdAt = this.createdAt;
        return resource;
    }
}
