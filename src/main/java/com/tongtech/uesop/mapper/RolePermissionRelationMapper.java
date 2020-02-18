package com.tongtech.uesop.mapper;

import com.tongtech.uesop.dto.RolePermissionRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RolePermissionRelationMapper {
    int deleteByPrimaryKey(Integer rpId);

    int insert(RolePermissionRelation record);

    int insertSelective(RolePermissionRelation record);

    RolePermissionRelation selectByPrimaryKey(Integer rpId);

    int updateByPrimaryKeySelective(RolePermissionRelation record);

    int updateByPrimaryKey(RolePermissionRelation record);
}