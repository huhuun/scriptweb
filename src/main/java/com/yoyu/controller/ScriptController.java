package com.yoyu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoyu.exception.BizException;
import com.yoyu.req.DataTableReq;
import com.yoyu.req.ScriptInfoReq;
import com.yoyu.resp.ScriptInfoResp;
import com.yoyu.service.IScriptInfoService;

@Controller
@RequestMapping("/script")
public class ScriptController {

	@Autowired
	IScriptInfoService scriptInfoService;
	
	@ResponseBody
	@RequestMapping("/getScriptInfoList.json")
	public ScriptInfoResp getScriptInfo(HttpServletRequest request, ScriptInfoReq req) throws BizException {
	
		return scriptInfoService.queryScriptInfoList(req);
	}
	
	
	public DataTableReq httpReq2TableReq(HttpServletRequest request) {
		DataTableReq dataTableReq = new DataTableReq();
		dataTableReq.setStart(request.getParameter("start"));
		dataTableReq.setDraw(request.getParameter("draw"));
		dataTableReq.setLength(request.getParameter("length"));
		return dataTableReq;
	}
}
