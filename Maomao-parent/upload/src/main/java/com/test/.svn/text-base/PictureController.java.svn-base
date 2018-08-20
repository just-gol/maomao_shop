package com.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;;

//图片上传处理controler
@Controller
public class PictureController {
	@Value("${PIC_SERVER}")
	private String IMAGE_SERVER_URL;
	
	//指定返回utf-8
	/*,produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8"*/
	@RequestMapping(value="/pic/upload")
	@ResponseBody//如果返回的是个字符串,相当于response.getwriter.write(),直接响应浏览器,如果返回的对象,会转json
	public JsonData uploadFile(MultipartFile uploadFile) {
		try {			
			//使用工具类上传到图片服务器
			FastDFSUtil fastDFSClient = new FastDFSUtil("classpath:conf/client.conf");
			
			//得到图片地址和文件名
			String originalFilename = uploadFile.getOriginalFilename();//获得原始文件名
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(),FilenameUtils.getExtension(originalFilename));
			
			//补充为完整的url
			url = IMAGE_SERVER_URL+url;
			System.out.println(url);
			//封装到map中返回
			Map<String,String> result = new HashMap<String,String>();
			result.put("url", url);
			
			return JsonData.setSuccessMessage(result);
		
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("图片上传失败");
		}
	}
}























