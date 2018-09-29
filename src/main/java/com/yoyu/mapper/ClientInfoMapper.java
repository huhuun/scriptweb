package com.yoyu.mapper;

import com.yoyu.pojo.ClientInfo;
import com.yoyu.pojo.ClientInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClientInfoMapper {
    long countByExample(ClientInfoExample example);

    int deleteByExample(ClientInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClientInfo record);

    int insertSelective(ClientInfo record);

    List<ClientInfo> selectByExample(ClientInfoExample example);

    ClientInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClientInfo record, @Param("example") ClientInfoExample example);

    int updateByExample(@Param("record") ClientInfo record, @Param("example") ClientInfoExample example);

    int updateByPrimaryKeySelective(ClientInfo record);

    int updateByPrimaryKey(ClientInfo record);
}