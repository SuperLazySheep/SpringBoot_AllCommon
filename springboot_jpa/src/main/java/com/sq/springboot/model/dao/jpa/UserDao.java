package com.sq.springboot.model.dao.jpa;

import com.sq.springboot.model.bean.domain.UserAccount;
import com.sq.springboot.model.bean.domain.UserCount;
import com.sq.springboot.model.bean.jpa.Account;
import com.sq.springboot.model.bean.jpa.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {

    @Override
    List<User> findAll();

    @Override
    User save(User user);

    //命名规则
    void deleteById(int id);
    //Optional<User> findById(int id);

    List<User> findByUsernameContaining(String str);

    //hql
    @Modifying
    @Transactional
    @Query("update User set username = ?1 where id = ?2")
    Integer updateUser(String username, int id);

    //hql，直接返回domain实体类
    @Query("Select new com.sq.springboot.model.bean.domain.UserCount(username,roles.size) from User")
    List<UserCount> findCount();
    //hql，map接受返回结果集
    @Query("select new map(u.username, a) from User u left join com.sq.springboot.model.bean.jpa.Account a on u.id = a.uid ")
    List<Map<String,Account>> findAccountMap();

    //hql，同时直接返回domain实体类，支持翻页
    @Query(value = "select new com.sq.springboot.model.bean.domain.UserAccount(u.username,a.money) from User u left join com.sq.springboot.model.bean.jpa.Account a "+
            "on u.id = a.uid",
            countQuery = "select count(u.id) from User u left join com.sq.springboot.model.bean.jpa.Account a on u.id = a.uid"
            )
    List<UserAccount> findUserAccountEntity(Pageable pageable);
}
