package com.sq.springboot.model.bean.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserCount {
    private String username;
    private Integer count;

    public UserCount() {
    }

    public UserCount(String username, Integer count) {
        this.username = username;
        this.count = count;
    }
}
