package com.yoyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yoyu.exception.BizException;
import com.yoyu.mapper.ScriptInfoMapper;
import com.yoyu.pojo.Datatable;
import com.yoyu.pojo.ScriptInfo;
import com.yoyu.pojo.ScriptInfoExample;
import com.yoyu.req.ScriptInfoReq;
import com.yoyu.resp.ScriptInfoResp;


@Service
public class ScriptInfoService implements IScriptInfoService{

//	private static Logger logger = LoggerFactory.getLogger(ScriptInfoService.class);
	//private final Logger log = LoggerFactory.getLogger(ScriptInfoService.class);
	@Autowired
	ScriptInfoMapper scriptInfoMapper;
	
	public ScriptInfoResp queryScriptInfoList(ScriptInfoReq req) throws BizException {
		
//		if(req == null || req.getItem() == null) {
//			throw new BizException("查询对象为空!");
//		}
		ScriptInfoResp resp = new ScriptInfoResp();
//		ScriptInfo scriptInfo = req.getItem();
		ScriptInfo scriptInfo = new ScriptInfo();
		ScriptInfoExample example = new ScriptInfoExample();
//		ScriptInfoExample.Criteria c =  example.createCriteria();
//		if(StringUtils.isNotBlank(String.valueOf(scriptInfo.getId()))) {
//			c.andIdEqualTo(scriptInfo.getId());
//		}
//		if(StringUtils.isNotBlank(scriptInfo.getScriptName())) {
//			c.andScriptNameLike("%"+ scriptInfo.getScriptName() +"%");
//		}
//		if(scriptInfo.getCreateTime() != null) {
//			c.andCreateTimeBetween(value1, value2)
//		}
//		Page
		int pageNum = (Integer.parseInt(req.getStart()) / Integer.parseInt(req.getLength())) + 1;
		PageHelper.startPage(pageNum,Integer.parseInt(req.getLength()));
		List<ScriptInfo> list = scriptInfoMapper.selectByExample(example);

		PageInfo<ScriptInfo> pageInfo = new PageInfo<ScriptInfo>(list);
		

		Datatable<ScriptInfo> dataTable = new Datatable<ScriptInfo>();
		dataTable.setDraw(req.getDraw());
		dataTable.setData(pageInfo.getList());  
		dataTable.setRecordsTotal(pageInfo.getTotal()+"");  
		dataTable.setRecordsFiltered(pageInfo.getTotal()+""); 
		resp.setDataTables(dataTable);
		//resp.setItemList(list);
		return resp;
	}
	
}
