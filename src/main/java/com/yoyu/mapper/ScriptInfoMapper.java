package com.yoyu.mapper;

import com.yoyu.pojo.ScriptInfo;
import com.yoyu.pojo.ScriptInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScriptInfoMapper {
    long countByExample(ScriptInfoExample example);

    int deleteByExample(ScriptInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScriptInfo record);

    int insertSelective(ScriptInfo record);

    List<ScriptInfo> selectByExampleWithBLOBs(ScriptInfoExample example);

    List<ScriptInfo> selectByExample(ScriptInfoExample example);

    ScriptInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScriptInfo record, @Param("example") ScriptInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ScriptInfo record, @Param("example") ScriptInfoExample example);

    int updateByExample(@Param("record") ScriptInfo record, @Param("example") ScriptInfoExample example);

    int updateByPrimaryKeySelective(ScriptInfo record);

    int updateByPrimaryKeyWithBLOBs(ScriptInfo record);

    int updateByPrimaryKey(ScriptInfo record);
}