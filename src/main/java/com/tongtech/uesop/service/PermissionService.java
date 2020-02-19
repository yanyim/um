package com.tongtech.uesop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.tongtech.uesop.dto.Permission;
import com.tongtech.uesop.mapper.PermissionMapper;

import java.util.List;

@Service
public class PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    public int deleteByPrimaryKey(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    public int insertSelective(Permission record) {
        return permissionMapper.insertSelective(record);
    }

    public Permission selectByPrimaryKey(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    public PageInfo<Permission> selectAllwithPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(permissionMapper.selectAll());
    }

    public List<Permission> selectByRoleKey(Integer roleId) {
        return permissionMapper.selectByRoleKey(roleId);
    }

    public List<Permission> selectByLinkId(Integer departmentId) {
        return permissionMapper.selectByLinkId(departmentId);
    }
}



