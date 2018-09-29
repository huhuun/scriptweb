package com.yoyu.mapper;

import com.yoyu.pojo.ClientScript;
import com.yoyu.pojo.ClientScriptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClientScriptMapper {
    long countByExample(ClientScriptExample example);

    int deleteByExample(ClientScriptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClientScript record);

    int insertSelective(ClientScript record);

    List<ClientScript> selectByExample(ClientScriptExample example);

    ClientScript selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClientScript record, @Param("example") ClientScriptExample example);

    int updateByExample(@Param("record") ClientScript record, @Param("example") ClientScriptExample example);

    int updateByPrimaryKeySelective(ClientScript record);

    int updateByPrimaryKey(ClientScript record);
}