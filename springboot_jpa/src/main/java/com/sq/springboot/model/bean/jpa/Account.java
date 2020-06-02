package com.sq.springboot.model.bean.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer uid;
    @Column(nullable = false)
    private Double money;
    /*@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")*/
    private User users;

    public Account() {

    }

    public Account(Integer uid, Double money) {
        this.uid = uid;
        this.money = money;
    }

}
