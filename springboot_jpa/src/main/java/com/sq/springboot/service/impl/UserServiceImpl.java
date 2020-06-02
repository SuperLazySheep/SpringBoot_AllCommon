package com.sq.springboot.service.impl;

import com.sq.springboot.model.bean.domain.UserAccount;
import com.sq.springboot.model.bean.domain.UserCount;
import com.sq.springboot.model.bean.jpa.Account;
import com.sq.springboot.model.bean.jpa.User;
import com.sq.springboot.model.dao.jpa.UserDao;
import com.sq.springboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public Integer updateUser(String username, int id) {
        return userDao.updateUser(username, id);
    }

    /**
     * 返回domain类型的vo
     * @return
     */
    @Override
    public List<UserCount> findCount() {
        return userDao.findCount();
    }

    /**
     * 返回map类的数据集合
     * @return
     */
    @Override
    public List<Map<String, Account>> findAccountMap() {
        return userDao.findAccountMap();
    }

    @Override
    public List<UserAccount> findUserAccountEntity(int page, int size){
        List<UserAccount> userAccountEntity = userDao.findUserAccountEntity(PageRequest.of(page, size));
        return userAccountEntity;
    }

}
