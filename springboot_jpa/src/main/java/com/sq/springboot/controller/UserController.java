package com.sq.springboot.controller;

import com.sq.springboot.model.bean.common.ResultData;
import com.sq.springboot.model.bean.domain.UserAccount;
import com.sq.springboot.model.bean.domain.UserCount;
import com.sq.springboot.model.bean.jpa.Account;
import com.sq.springboot.model.bean.jpa.User;
import com.sq.springboot.model.dao.jpa.UserDao;
import com.sq.springboot.service.inter.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "User控制器")
@Slf4j
@Validated
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @ApiOperation(value = "查询所有", httpMethod = "GET")
    @GetMapping
    public ResultData<User> getAll(){
        log.info("get all");
        List<User> users = userService.findAll();
        return new ResultData(users);
    }

    @ApiOperation(value = "插入用户数据", httpMethod = "POST")
    @PostMapping
    public ResultData<User> saveUser(
            @ApiParam(value = "用户名", required = true) @RequestParam(name ="username" , defaultValue = "宋奇奇") String username,
            @ApiParam(value = "性别", required = true) @RequestParam(name ="sex" , defaultValue = "男") String sex,
            @ApiParam(value = "地址", required = true) @RequestParam(name ="address" , defaultValue = "陕西省") String address

    ){
        log.info("insert user "+username);
        User user = userService.save(new User(username, new Date(), sex, address));
            return new ResultData<>(user);
    }

    @ApiOperation(value = "删除用户数据",httpMethod ="DELETE")
    @DeleteMapping()
    public ResultData deleteUser(
            @ApiParam(value = "用户id", required = true) @RequestParam(name ="id",defaultValue = "49")  @PathVariable int id
    ){
        log.info("delete user" + id);
        userService.deleteById(id);
        return new ResultData("deleteUser"+id);
    }

    @ApiOperation(value = "修改数据", httpMethod = "PUT")
    @PutMapping
    public ResultData update(
            @ApiParam(value = "用户id", required = true) @RequestParam(name ="id",defaultValue = "50")   int id,
            @ApiParam(value = "用户姓名", required = true) @RequestParam(name = "username", defaultValue = "宋奇奇")  String username
    ){
        log.info("update user" + username);
        int i = userService.updateUser(username, id);
        return new ResultData("updateUser" + i);
    }

    @ApiOperation(value = "模糊查询", httpMethod = "GET")
    @GetMapping("search")
    public ResultData<List<User>> search(
            @ApiParam(value = "用户姓名", required = true) @RequestParam(name = "username", defaultValue = "小")  String str
    ){
        List<User> userList = userDao.findByUsernameContaining(str);
        if(userList.size() == 0){
            return null;
        }else{
            return new ResultData("search" + userList);
        }
    }

    @ApiOperation(value = "HQL对应VO查询", httpMethod = "GET")
    @GetMapping("/send")
    public ResultData<List<UserCount>> findCount(){
        List<UserCount> userCount = userService.findCount();
        if(userCount == null ){
            return null;
        }else {
            return new ResultData("get " + userCount);
        }
    }

    @ApiOperation(value = "HQL对应Map集合")
    @GetMapping("/find")
    public ResultData<List<Map<String, Account>>> findAccountMap(){
        log.info("find  map" );
        List<Map<String, Account>> accountMap = userService.findAccountMap();
        if(accountMap.size() == 0) {
            return null;
        }else{
            return new ResultData<>(accountMap);
        }
    }

    @ApiOperation(value = "HQL,同时直接返回domain实体类，支持翻页")
    @GetMapping("/find2")
    public ResultData<List<UserAccount>> findUserAccountEntity(
            @ApiParam(value = "第几页", required = true) @RequestParam(name = "page", defaultValue = "0", required = true) int page,
            @ApiParam(value = "每页的数量", required = true)@RequestParam(name ="size", defaultValue = "5", required = true)int size
    ){
        log.info("find2 userAccount");
        List<UserAccount> userAccountList = userService.findUserAccountEntity(page, size);
        if(userAccountList.size() == 0){
            return null;
        }else{
            return new ResultData<>(userAccountList);
        }
    }
}
