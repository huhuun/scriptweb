package com.yoyu.service;

import org.springframework.stereotype.Service;

import com.yoyu.exception.BizException;
import com.yoyu.pojo.ScriptInfo;
import com.yoyu.req.DataTableReq;
import com.yoyu.req.ScriptInfoReq;
import com.yoyu.resp.DatatableResp;
import com.yoyu.resp.ScriptInfoResp;


@Service
public interface IScriptInfoService {

	
	ScriptInfoResp queryScriptInfoList(ScriptInfoReq req) throws BizException;
	
}
