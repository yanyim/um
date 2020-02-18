package com.tongtech.uesop.service;

import com.tongtech.uesop.dto2.Synonyms;

public interface SynonymsService {


    int deleteByPrimaryKey(Integer id);

    int insert(Synonyms record);

    int insertSelective(Synonyms record);

    Synonyms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Synonyms record);

    int updateByPrimaryKey(Synonyms record);

}



