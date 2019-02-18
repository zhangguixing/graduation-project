package com.jefflee.controller.shiro;

import com.jefflee.util.shiro.GlobalUtil;
import com.jefflee.util.shiro.ResultUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Controller
public class PictureController {
	
	@RequestMapping(value="/pic/upload", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public ResultUtil uploadFile(MultipartFile file, HttpServletRequest req){
		if(file==null){
			return ResultUtil.error("文件不能为空！");
		}
		String fileSub=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
		if(".jpg".equals(fileSub) ||".jpeg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)){
			Random d=new Random();
			String img = UUID.randomUUID().toString().replace("-", "")+""+d.nextInt(10000)+""+fileSub;
			try {
//				uploadFile.transferTo(new File(req.getServletContext().getRealPath("WEB-INF/upload"),img));
				file.transferTo(new File(GlobalUtil.getValue("upfile.path"),img));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Map<String,String> map=new HashMap<>();
			map.put("src",GlobalUtil.getValue("upfile.code")+img);
			return ResultUtil.ok(map);
		}else{
			return ResultUtil.error("文件格式不支持,请重新选择！");
		}
	}
	@RequestMapping(value="/pic/upload1", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public ResultUtil uploadFile1(MultipartFile uploadFile,HttpServletRequest req){
		if(uploadFile==null){
			return ResultUtil.error("文件不能为空！");
		}
		String fileSub=uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
		if(".jpg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)){
			Random d=new Random();
			String img = UUID.randomUUID().toString().replace("-", "")+""+d.nextInt(10000)+""+fileSub;
			try {
//				uploadFile.transferTo(new File(req.getServletContext().getRealPath("WEB-INF/upload"),img));
				uploadFile.transferTo(new File(GlobalUtil.getValue("upfile.path"),img));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Map<String,String> map=new HashMap<>();
			map.put("src",GlobalUtil.getValue("upfile.code")+img);
			return ResultUtil.ok(map);
		}else{
			return ResultUtil.error("文件格式不支持！");
		}
	}
}
