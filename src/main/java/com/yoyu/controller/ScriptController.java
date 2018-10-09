package com.yoyu.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yoyu.exception.BizException;
import com.yoyu.req.DataTableReq;
import com.yoyu.req.ScriptInfoReq;
import com.yoyu.resp.ScriptInfoResp;
import com.yoyu.service.IScriptInfoService;
import com.yoyu.util.FileUtils;

@Controller
@RequestMapping("/script")
public class ScriptController {

	private final static Logger logger = LoggerFactory.getLogger(ScriptController.class);
	
	@Autowired
	IScriptInfoService scriptInfoService;
	
	@Value("${img.scriptlogo.path}")
	String logoPath;
	
	
	@ResponseBody
	@RequestMapping("/getScriptInfoList.json")
	public ScriptInfoResp getScriptInfo(HttpServletRequest request, ScriptInfoReq req) throws BizException {
	
		return scriptInfoService.queryScriptInfoList(req);
	}
	
	@ResponseBody
	@PostMapping("/addScriptInfo.json")
	public ScriptInfoResp addScriptInfo(@RequestParam("logo") MultipartFile file,HttpServletRequest request) throws BizException {
		
		String scriptName = request.getParameter("scriptName");
		String intro = request.getParameter("intro");
		if(StringUtils.isBlank(scriptName)) {
			throw new BizException("脚本名称为空！");
		}
		
		 try {
			    if (file.isEmpty()) {
			    	logger.info("上传文件为空");
			    	throw new BizException("上传文件为空");
			    }
			    // 获取文件摘要
			    //fileDigest = getFileDigest(file);
			    //System.out.println("合同原始文件摘要--->"+fileDigest);
			    // 获取文件名
			    String fileName = file.getOriginalFilename();
			    logger.info("上传的文件名为：" + fileName);
			    
			    // 获取文件的后缀名
			    String suffixName = fileName.substring(fileName.lastIndexOf("."));
			    //logger.info("文件的后缀名为：" + suffixName);
			    //if(!isSupportFileType(fileName)) {
			    //	logger.info("不支持的文件类型：[{}]",suffixName);
			    //	throw new ApiException(ApiErrNoConstants.ERR_UNSUPPORTED_FILE_TYPE);
			    //}
			    // 混淆文件名
			    fileName = FileUtils.generatorMixStr(fileName);
			    // 设置文件存储路径
			    String path = logoPath + File.separator + fileName + suffixName;
			    File dest = new File(path);
			    System.out.println("文件存储路径："+dest);
			    // 检测是否存在目录
			    if (!dest.getParentFile().exists()) {
			        dest.getParentFile().mkdirs();// 新建文件夹
			    }
			    file.transferTo(dest);// 文件写入
		    } catch (IllegalStateException | IOException e) {
		        e.printStackTrace();
		        throw new BizException("添加脚本失败，图片上传失败！");
		    }
		
		return new ScriptInfoResp();
	}
	
	public DataTableReq httpReq2TableReq(HttpServletRequest request) {
		DataTableReq dataTableReq = new DataTableReq();
		dataTableReq.setStart(request.getParameter("start"));
		dataTableReq.setDraw(request.getParameter("draw"));
		dataTableReq.setLength(request.getParameter("length"));
		return dataTableReq;
	}
}
