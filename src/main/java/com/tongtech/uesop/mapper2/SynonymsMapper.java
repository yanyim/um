package com.tongtech.uesop.mapper2;

import com.tongtech.uesop.dto2.Synonyms;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SynonymsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Synonyms record);

    int insertSelective(Synonyms record);

    Synonyms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Synonyms record);

    int updateByPrimaryKey(Synonyms record);
}