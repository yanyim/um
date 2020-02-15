package com.tongtech.uesop.mapper;

import com.tongtech.uesop.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UmMapper {

    @Select("SELECT COUNT(*) FROM ${table}")
    int getTotal(@Param("table") String table);

    @Select("select * from user limit #{start},#{end}")
    List<User> listUsers(@Param("start") int start, @Param("end") int end);

    @Select("select * from department")
    List<Department> listDepartment();

    @Select("select * from permission where type = 'view'")
    List<Permission> listViewPermissions();

    @Select("INSERT INTO department (department_name, parent_department) VALUES(#{name}, #{parent})")
    void addDepartment(@Param("name") String department_name,
                       @Param("parent") int parent_department);

    @Select("INSERT INTO permission (permission_name, " +
            "code, " +
            "type, " +
            "parent_permission, " +
            "status, " +
            "description) VALUES(" +
            "#{name}, " +
            "#{code}, " +
            "#{type}, " +
            "#{parent}, " +
            "#{status}, " +
            "#{description})")
    void addPermission(@Param("name") String permission_name,
                       @Param("code") String code,
                       @Param("type") String type,
                       @Param("parent") int parent_permission,
                       @Param("status") String status,
                       @Param("description") String description);

    @Select("UPDATE permission SET " +
            "permission_name = #{name}," +
            "code =#{code}," +
            "type = #{type}," +
            "parent_permission = #{parent}," +
            "status = #{status}," +
            "description = #{description} where id = #{id}")
    void editPermission(
            @Param("id") int id,
            @Param("name") String permission_name,
            @Param("code") String code,
            @Param("type") String type,
            @Param("parent") int parent_permission,
            @Param("status") String status,
            @Param("description") String description);

    @Select("UPDATE permission SET " +
            "status = #{status} where id = #{id} or parent_permission = #{id}")
    void changeStatusOfPermission(
            @Param("id") int id,
            @Param("status") String status);


    @Select("DELETE FROM permission where id = #{id}")
    void deletePermission(@Param("id") int id);

    @Select("INSERT INTO user (user_name, " +
            "department, " +
            "avatar_url, " +
            "user_id, " +
            "status ) VALUES(" +
            "#{name}, " +
            "#{department}, " +
            "#{avatar_url}, " +
            "#{user_id}, " +
            "#{status}) ")
    void addUser(
            @Param("name") String user_name,
            @Param("department") String department,
            @Param("avatar_url") String avatar_url,
            @Param("user_id") String user_id,
            @Param("status") String status);

    @Select("select * from user where user_id = #{user_id}")
    User getUser(@Param("user_id") String user_id);
}
