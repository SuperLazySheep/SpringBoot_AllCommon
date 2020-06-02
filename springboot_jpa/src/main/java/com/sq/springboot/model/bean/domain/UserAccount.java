package com.sq.springboot.model.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserAccount {
    private String username;
    private Double money;

    public UserAccount() {

    }

    public UserAccount(String username, Double money) {
        this.username = username;
        this.money = money;
    }
}
