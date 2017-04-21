package cn.didano.video.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.didano.base.exception.ServiceException;
import cn.didano.base.interaction.StorageFileNotFoundException;
import cn.didano.base.interaction.StorageService;
import cn.didano.base.model.Tb_interactive_catalog;
import cn.didano.base.model.Tb_interactive_model;
import cn.didano.base.service.InteractiveModelService;
import cn.didano.video.constant.BackType;
import cn.didano.video.entity.Interactive;
import cn.didano.video.entity.OssInfo;
import cn.didano.video.json.In_InteractiveModel_add;
import cn.didano.video.json.Out;
import cn.didano.video.json.OutList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "互动模板编写", tags = "互动模板服务，提供编写新的模块")
@RestController
@RequestMapping(value = "/base/interactive/post/")
public class InteractiveModelWritingController {

	static Logger logger = Logger.getLogger(InteractiveModelWritingController.class);
     @Autowired
     private InteractiveModelService interactiveService;
     @Autowired
     private StorageService storageService;
     

     

	/**
	 * 下载
	 */
	@PostMapping(value = "download/{time}")
	@ApiOperation(value = "下载", notes = "下载")
	@ResponseBody
	public Out<OutList<Tb_interactive_model>> download(@PathVariable("time")long time) {
		logger.info("访问   InteractiveModelWritingController :download,time="+time);
		List<Tb_interactive_model> models = null;
		OutList<Tb_interactive_model> outList = null;
		Out<OutList<Tb_interactive_model>> back = new Out<OutList<Tb_interactive_model>>();
		try {
			// 查找所有time时间之后的zip包
			models= interactiveService.findByUpdate(time);
			Tb_interactive_catalog catalog=null;
			Tb_interactive_catalog catalogParent=null;
			for (int i = 0; i < models.size(); i++) {
			   catalog = interactiveService.findCatalogById(models.get(i).getCatalog());
               catalogParent=interactiveService.findCatalogById(catalog.getParentId());
			   StringBuilder sb=new StringBuilder(catalogParent.getName());
			   sb.append("-"+catalog.getName());
			   models.get(i).setCatalogName(sb.toString());
			}
			
			if (models.size() > 0) {
				
				outList = new OutList<Tb_interactive_model>(models.size(), models);
				back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
			} else {
				
				back.setBackTypeWithLog(outList, BackType.FAIL_SEARCH_NORMAL);
			}
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}
	
	/**
	 * 查找所有模块
	 */
	@PostMapping(value = "findAllModel")
	@ApiOperation(value = "查找所有模块", notes = "查找所有模块")
	@ResponseBody
	public Out<OutList<Tb_interactive_model>> findAllModel() {
		logger.info("访问   InteractiveModelWritingController :findAllModel");
		List<Tb_interactive_model> models = null;
		OutList<Tb_interactive_model> outList = null;
		Out<OutList<Tb_interactive_model>> back = new Out<OutList<Tb_interactive_model>>();
		try {
			// 查找所有time时间之后的zip包
			models= interactiveService.findAllModel();
			Tb_interactive_catalog catalog=null;
			Tb_interactive_catalog catalogParent=null;
			for (int i = 0; i < models.size(); i++) {
			   catalog = interactiveService.findCatalogById(models.get(i).getCatalog());
               catalogParent=interactiveService.findCatalogById(catalog.getParentId());
			   StringBuilder sb=new StringBuilder(catalogParent.getName());
			   sb.append("-"+catalog.getName());
			   models.get(i).setCatalogName(sb.toString());
			}
			
			if (models.size() > 0) {
				
				outList = new OutList<Tb_interactive_model>(models.size(), models);
				back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
			} else {
				
				back.setBackTypeWithLog(outList, BackType.FAIL_SEARCH_NORMAL);
			}
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}
}
