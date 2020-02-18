package com.tongtech.uesop.service2;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.tongtech.uesop.dto2.Synonyms;
import com.tongtech.uesop.mapper2.SynonymsMapper;
import com.tongtech.uesop.service.SynonymsService;

@Service
public class SynonymsServiceImpl implements SynonymsService {

    @Resource
    private SynonymsMapper synonymsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return synonymsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Synonyms record) {
        return synonymsMapper.insert(record);
    }

    @Override
    public int insertSelective(Synonyms record) {
        return synonymsMapper.insertSelective(record);
    }

    @Override
    public Synonyms selectByPrimaryKey(Integer id) {
        return synonymsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Synonyms record) {
        return synonymsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Synonyms record) {
        return synonymsMapper.updateByPrimaryKey(record);
    }

}



