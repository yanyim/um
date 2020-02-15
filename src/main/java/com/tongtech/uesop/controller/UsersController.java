

package com.tongtech.uesop.controller;

import com.tongtech.uesop.dto.*;
import com.tongtech.uesop.dto.User;
import com.tongtech.uesop.mapper.UmMapper;
import com.tongtech.uesop.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/um")
public class UsersController {

    @Autowired
    private UmMapper umMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ListResult<List<User>> listUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        int total = umMapper.getTotal("user");
        List<User> users = umMapper.listUsers(page * pageSize - pageSize, page * pageSize);
        ListResult<List<User>> responseResult = new ListResult<List<User>>();
        responseResult.setBody(users);
        responseResult.setTotal(total);
        responseResult.setCurrentPage(page);
        responseResult.setPageSize(pageSize);
        responseResult.setState(1);
        return responseResult;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseResult<Integer> addUser(
            @RequestBody(required = true) User user
    ) {
        user.setStatus(UMStatus.VALID);
        umMapper.addUser(
                user.getUserName(),
                user.getDepartment(),
                user.getAvatarUrl(),
                user.getUserName(),
                user.getStatus());
        ResponseResult result = new ResponseResult();
        result.setMsg("添加成功");
        result.setState(1);
        return result;
    }


    @RequestMapping(path = "/departments", method = RequestMethod.GET)
    public ListResult<List<Department>> listDepartments() {
        List<Department> departments = departmentMapper.selectAll();
        ListResult result = new ListResult();
        result.setBody(departments);
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/department", method = RequestMethod.GET)
    public ResponseResult<Department> queryDepartment(@RequestParam(defaultValue = "1") int id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        ResponseResult result = new ResponseResult();
        result.setBody(department);
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/department", method = RequestMethod.POST)
    public ResponseResult<Integer> addDepartment(
            @RequestBody(required = true) Department department
    ) {
        umMapper.addDepartment(
                department.getDepartmentName(),
                department.getParentDepartment());
        ResponseResult result = new ResponseResult();
        result.setMsg("添加成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/permission", method = RequestMethod.POST)
    public ResponseResult<Integer> addPermission(
            @RequestBody(required = true) Permission permission
    ) {
        permission.setStatus(UMStatus.VALID);
        umMapper.addPermission(
                permission.getPermission_name(),
                permission.getCode(),
                permission.getType(),
                permission.getParent_permission(),
                permission.getStatus(),
                permission.getDescription());
        ResponseResult result = new ResponseResult();
        result.setMsg("添加成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/editPermission", method = RequestMethod.POST)
    public ResponseResult<Integer> editPermission(
            @RequestBody(required = true) Permission permission
    ) {
        umMapper.editPermission(
                permission.getId(),
                permission.getPermission_name(),
                permission.getCode(),
                permission.getType(),
                permission.getParent_permission(),
                permission.getStatus(),
                permission.getDescription());
        ResponseResult result = new ResponseResult();
        result.setMsg("修改成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/changeStatusPermission", method = RequestMethod.POST)
    public ResponseResult<Integer> changeStatusOfPermission(
            @RequestBody(required = true) Permission permission
    ) {
        umMapper.changeStatusOfPermission(
                permission.getId(),
                permission.getStatus());
        ResponseResult result = new ResponseResult();
        result.setMsg("修改成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/permission", method = RequestMethod.DELETE)
    public ResponseResult<Integer> deletePermission(
            @RequestBody(required = true) Permission permission
    ) {
        umMapper.deletePermission(permission.getId());
        ResponseResult result = new ResponseResult();
        result.setMsg("删除成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/viewPermissions", method = RequestMethod.GET)
    public ListResult<List<Permission>> listViewPermissions() {
        List<Permission> permissions = umMapper.listViewPermissions();
        ListResult result = new ListResult();
        result.setBody(permissions);
        result.setMsg("添加成功");
        result.setState(1);
        return result;
    }

}
