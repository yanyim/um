package com.tongtech.uesop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tongtech.uesop.dto.Permission;
import com.tongtech.uesop.dto.RolePermissionRelation;
import com.tongtech.uesop.mapper.PermissionMapper;
import com.tongtech.uesop.mapper.RolePermissionRelationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.tongtech.uesop.dto.Role;
import com.tongtech.uesop.mapper.RoleMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RolePermissionRelationMapper rolePermissionRelationMapper;

    public int deleteByPrimaryKey(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public int insert(Role record, Integer[] permissions) {
        roleMapper.insert(record);
        for (Integer permission : permissions) {
            RolePermissionRelation rp = new RolePermissionRelation();
            rp.setPrmissionId(permission);
            rp.setRoleId(record.getId());
            rolePermissionRelationMapper.insert(rp);
        }
        return 1;
    }

    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    public Role selectByPrimaryKey(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    public PageInfo<Role> selectRoleswithPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(roleMapper.selectRoles());
    }

    public List<Role> selectRoles() {
        return roleMapper.selectRoles();
    }

    public Role selectDetailByPrimaryKey(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        List<Permission> permissions = permissionMapper.selectByRoleKey(id);
        role.setPermissions(permissions);
        return role;
    }

    public List<Role> selectDetailByPrimaryKeys(Integer[] ids) {
        List<Role> roles = new ArrayList<>();
        for (Integer id : ids) {
            Role role = roleMapper.selectByPrimaryKey(id);
            List<Permission> permissions = permissionMapper.selectByRoleKey(id);
            role.setPermissions(permissions);
            roles.add(role);
        }
        return roles;
    }
}

