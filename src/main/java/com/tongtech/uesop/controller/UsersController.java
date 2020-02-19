

package com.tongtech.uesop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tongtech.uesop.dto.*;
import com.tongtech.uesop.dto.User;
import com.tongtech.uesop.service.DepartmentService;
import com.tongtech.uesop.service.PermissionService;
import com.tongtech.uesop.service.RoleService;
import com.tongtech.uesop.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/um")
public class UsersController {


    @Resource(name = "departmentService")
    private DepartmentService departmentService;

    @Resource
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "permissionService")
    private PermissionService permissionService;

    /*
     *
     * 操作用户
     *
     * */
    @RequestMapping(path = "/usersWithPage", method = RequestMethod.GET)
    public ResponseResult<PageInfo<User>> selectUserswithPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int pageSize) {
        PageHelper.startPage(page, pageSize);
        ResponseResult result = new ResponseResult();
        PageInfo info = userService.selectUserswithPage(page, pageSize);
        result.setBody(info);
        result.setMsg("查询成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/usersAndFilterWithPage", method = RequestMethod.GET)
    public ResponseResult<PageInfo<User>> selectAndfilterUserswithPage(
            @RequestParam(value = "userName", defaultValue = "") String userName,
            @RequestParam(value = "status", defaultValue = "") String status,
            @RequestParam(value = "userId", defaultValue = "") String userId,
            @RequestParam(value = "department", defaultValue = "") String department,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "phone", defaultValue = "") String phone,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        PageHelper.startPage(page, pageSize);
        User user = new User();
        user.setUserName(userName);
        user.setStatus(status);
        user.setUserId(userId);
        user.setDepartment(department);
        user.setPhone(phone);
        ResponseResult result = new ResponseResult();
        PageInfo info = userService.selectByAllwithPage(user, page, pageSize);
        result.setBody(info);
        result.setMsg("查询成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseResult<Integer> addUser(
            @RequestBody(required = true) UserRequest userRequest
    ) {
        int resultStatus = userService.insert(userRequest.getUser(), userRequest.getRoles());
        ResponseResult result = new ResponseResult();
        result.setMsg("添加成功");
        result.setState(resultStatus);
        return result;
    }

    @RequestMapping(path = "/user", method = RequestMethod.DELETE)
    public ResponseResult<Integer> removeUser(
            @RequestBody(required = true) User user
    ) {
        int resultStatus = userService.deleteByPrimaryKey(user.getId());
        ResponseResult result = new ResponseResult();
        result.setMsg("删除成功");
        result.setState(resultStatus);
        return result;
    }


    /*
     *   操作部门
     * */
    @RequestMapping(path = "/departments", method = RequestMethod.GET)
    public ResponseResult<List<Department>> listDepartments() {
        List<Department> departments = departmentService.selectAll();
        ResponseResult result = new ResponseResult();
        result.setBody(departments);
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/department", method = RequestMethod.GET)
    public ResponseResult<Department> queryDepartment(@RequestParam(defaultValue = "1") int id) {
        Department department = departmentService.selectByPrimaryKey(id);
        ResponseResult result = new ResponseResult();
        result.setBody(department);
        result.setState(1);
        return result;
    }

    @Transactional
    @RequestMapping(path = "/department", method = RequestMethod.POST)
    public ResponseResult<Integer> addDepartment(
            @RequestBody(required = true) DepartmentRequest departmentRequest
    ) {
        Department department = departmentRequest.getDepartment();
        Permission permission = departmentRequest.getPermission();
        permission.setType("data");
        String parentStr = department.getParentDepartmentName() == null? "ROOT":department.getParentDepartmentName();
        permission.setPermissionName(parentStr+"." + department.getDepartmentName());

        permission.setStatus(UMStatus.VALID);
        permission.setDescription("部门" + department.getDepartmentName() + "默认权限");
        permission.setLock(1);
        permissionService.insert(permission);
        Role role = departmentRequest.getRole();
        role.setRoleName(parentStr +"."+ department.getDepartmentName());
        Integer[] permissions = {permission.getId()};
        int roleId = roleService.insert(role, permissions);
        department.setRoleId(roleId);
        int sqlResult = departmentService.insert(department);
        permission.setLinkDepartment(department.getId());
        permissionService.updateByPrimaryKey(permission);
        ResponseResult result = new ResponseResult();
        result.setMsg("添加成功");
        result.setState(sqlResult);
        return result;
    }

    /*
     *
     * 操作角色
     *
     * */
    @RequestMapping(path = "/role/detail", method = RequestMethod.GET)
    public ResponseResult<Role> selectRoleDetail(@RequestParam int id) {
        Role role = roleService.selectDetailByPrimaryKey(id);
        ResponseResult result = new ResponseResult();
        result.setBody(role);
        result.setMsg("查询成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/role/details", method = RequestMethod.GET)
    public ResponseResult<List<Role>> selectRolesDetail(@RequestParam Integer[] ids) {
        List<Role> roles = roleService.selectDetailByPrimaryKeys(ids);
        ResponseResult result = new ResponseResult();
        result.setBody(roles);
        result.setMsg("查询成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/roles", method = RequestMethod.GET)
    public ResponseResult<List<Role>> selectRoles() {
        ResponseResult result = new ResponseResult();
        List<Role> roles = roleService.selectRoles();
        result.setBody(roles);
        result.setMsg("查询成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/rolesWithPage", method = RequestMethod.GET)
    public ResponseResult<PageInfo<User>> selectRoleswithPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int pageSize) {
        ResponseResult result = new ResponseResult();
        PageInfo info = roleService.selectRoleswithPage(page, pageSize);
        result.setBody(info);
        result.setMsg("查询成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/role", method = RequestMethod.POST)
    public ResponseResult<PageInfo<User>> addRole(@RequestBody(required = true) RoleRequest rr) {
        int roleId = roleService.insert(rr.getRole(), rr.getPermissions());
        ResponseResult result = new ResponseResult();
        result.setMsg("创建成功");
        result.setState(1);
        return result;
    }

    /*
     * 操作权限
     * */
    @RequestMapping(path = "/permission", method = RequestMethod.POST)
    public ResponseResult<Integer> addPermission(
            @RequestBody(required = true) Permission permission
    ) {
        permission.setStatus(UMStatus.VALID);
        permission.setType("data");
        int resultCode = permissionService.insert(permission);
        ResponseResult result = new ResponseResult();
        result.setMsg("添加成功");
        result.setState(resultCode);
        return result;
    }

    @RequestMapping(path = "/permissionWithLinkDepartmentId", method = RequestMethod.GET)
    public ResponseResult<Integer> queryPermissionWithLinkDepartmentId(
            @RequestParam(required = true) Integer departmentId
    ) {
        List<Permission> permissions = permissionService.selectByLinkId(departmentId);
        ResponseResult result = new ResponseResult();
        result.setBody(permissions);
        result.setMsg("添加成功");
        result.setState(1);
        return result;
    }

    @RequestMapping(path = "/editPermission", method = RequestMethod.POST)
    public ResponseResult<Integer> editPermission(
            @RequestBody(required = true) Permission permission
    ) {
        int resultCode = permissionService.updateByPrimaryKeySelective(permission);
        ResponseResult result = new ResponseResult();
        result.setMsg("修改成功");
        result.setState(resultCode);
        return result;
    }

    @RequestMapping(path = "/changeStatusPermission", method = RequestMethod.POST)
    public ResponseResult<Integer> changeStatusOfPermission(
            @RequestBody(required = true) Permission permission
    ) {
        int resultCode = permissionService.updateByPrimaryKeySelective(permission);
        ResponseResult result = new ResponseResult();
        result.setMsg("修改成功");
        result.setState(resultCode);
        return result;
    }

    @RequestMapping(path = "/permission", method = RequestMethod.DELETE)
    public ResponseResult<Integer> deletePermission(
            @RequestBody(required = true) Permission permission
    ) {
        int resultCode = permissionService.deleteByPrimaryKey(permission.getId());
        ResponseResult result = new ResponseResult();
        result.setMsg("删除成功");
        result.setState(resultCode);
        return result;
    }

    @RequestMapping(path = "/listPermissions", method = RequestMethod.GET)
    public ResponseResult<PageInfo<Permission>> listPermissionswithPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int pageSize) {
        PageInfo<Permission> info = permissionService.selectAllwithPage(page, pageSize);
        ResponseResult<PageInfo<Permission>> result = new ResponseResult();
        result.setBody(info);
        result.setMsg("添加成功");
        result.setState(1);
        return result;
    }

}
