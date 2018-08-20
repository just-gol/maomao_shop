package com.maomao.ssm.controller.other;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.utils.FastDFSUtil;;

//图片上传处理controler
@Controller
public class PictureController {
	@Value("${PIC_SERVER}")
	private String PIC_SERVER;
	
	//指定返回utf-8
	@RequestMapping(value="/pic/upload.action"/*,produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8"*/)
	@ResponseBody//如果返回的是个字符串,相当于response.getwriter.write(),直接响应浏览器,如果返回的对象,会转json
	public JsonData uploadFile(MultipartFile[] uploadFile) {
		/*try {			
			//使用工具类上传到图片服务器
			FastDFSUtil fastDFSClient = new FastDFSUtil("classpath:conf/client.conf");
			
			String url = "";
			synchronized (PictureController.this) {
				//得到图片地址和文件名
				String originalFilename = uploadFile.getOriginalFilename();//获得原始文件名
				url = fastDFSClient.uploadFile(uploadFile.getBytes(),FilenameUtils.getExtension(originalFilename));
				
			}
			//补充为完整的url
			url = PIC_SERVER+url;
			
			//封装到map中返回
			Map<String,String> result = new HashMap<String,String>();
			result.put("url", url);
			return JsonData.setSuccessMessage(result);
		
		} catch (Exception e) {
			try {
				Process pro = Runtime.getRuntime().exec(new String[] {"/usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf restart","/usr/bin/fdfs_storaged /etc/fdfs/storage.conf restart"});
				pro.waitFor();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return JsonData.setErrorMessage("图片上传失败，请重新上传");
		}*/
		try {			
			//使用工具类上传到图片服务器
			FastDFSUtil fastDFSClient = new FastDFSUtil("classpath:conf/client.conf");
			
			//得到图片地址和文件名
			StringBuilder sb = new StringBuilder();
			if (uploadFile!=null && uploadFile.length>0) {
				for (MultipartFile multipartFile : uploadFile) {
					if (!multipartFile.isEmpty()) {
						String originalFilename = multipartFile.getOriginalFilename();//获得原始文件名
						String picPath = fastDFSClient.uploadFile(multipartFile.getBytes(),FilenameUtils.getExtension(originalFilename));
						if (uploadFile.length==1) {
							sb.append(PIC_SERVER).append(picPath);
						}else {
							sb.append(PIC_SERVER).append(picPath).append(";"); 
						}
						
					}
				}
			}
			
			//封装到map中返回
			Map<String,String> result = new HashMap<String,String>();
			result.put("url", sb.toString());
			
			return JsonData.setSuccessMessage(result);
		
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("图片上传失败"+e.getMessage());
		}
	}
}























