package com.tongtech.uesop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tongtech.uesop.dto.*;
import com.tongtech.uesop.mapper.PermissionMapper;
import com.tongtech.uesop.mapper.UserRoleRelationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.tongtech.uesop.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleRelationMapper userRoleRelationMapper;

    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        int resultCode = userMapper.deleteByPrimaryKey(id);
        int resultCode2 = userRoleRelationMapper.deleteByUserId(id);
        return resultCode2;
    }

    public int insert(User record) {
        return userMapper.insert(record);
    }

    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    public PageInfo<User> selectUserswithPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(userMapper.selectUsers());
    }

    public User selectwithUserId(String id) {
        return userMapper.selectwithUserId(id);
    }

    @Transactional
    public int insert(User record, Integer[] rids) {
        record.setStatus(UMStatus.VALID);
        userMapper.insert(record);
        for (Integer id : rids) {
            UserRoleRelation rp = new UserRoleRelation();
            rp.setUserId(record.getId());
            rp.setRoleId(id);
            userRoleRelationMapper.insert(rp);
        }
        return 1;
    }

    public PageInfo<User> selectByAllwithPage(User user, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(userMapper.selectByAll(user));
    }
}




