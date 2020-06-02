package com.sq.springboot.service.inter;

import com.sq.springboot.model.bean.domain.UserAccount;
import com.sq.springboot.model.bean.domain.UserCount;
import com.sq.springboot.model.bean.jpa.Account;
import com.sq.springboot.model.bean.jpa.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();

    User save(User user);

    void deleteById(int id);

    Integer updateUser(String Username, int id);

    List<UserCount> findCount();

    List<Map<String, Account>> findAccountMap();

    List<UserAccount> findUserAccountEntity(int page, int size);
}
