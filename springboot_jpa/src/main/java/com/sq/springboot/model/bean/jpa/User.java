package com.sq.springboot.model.bean.jpa;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "user")
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "用户ID")
    private int id;
    @Column(unique = true, nullable = false)
    @ApiModelProperty(value = "用户姓名")
    private String username;
    @Column(nullable = false)
    @ApiModelProperty(value = "用户出生日")
    private Date birthday;
    @Column(nullable = false)
    @ApiModelProperty(value = "用户性别")
    private String sex;
    @Column(nullable = false)
    @ApiModelProperty(value = "用户家庭地址")
    private String address;

    @ApiModelProperty(value = "多对多对应的role表")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "rid")})
    private List<Role> roles;

   /* @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;*/

    public User() {
    }

    public User(String username, Date birthday, String sex, String address) {
        this.username = username;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
    }
}
