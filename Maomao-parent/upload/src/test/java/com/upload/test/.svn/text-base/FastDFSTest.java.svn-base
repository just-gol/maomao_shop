package com.upload.test;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.test.FastDFSUtil;

public class FastDFSTest {
	
	@Test
	public void uploadTest() throws Exception{
		//创建一个配置文件,内容就tracker服务器的地址
		//使用全局对象加载配置文件
		ClientGlobal.init("I:/workspace01/e3-manager--web/src/main/resources/conf/tracker_server.conf");
		
		//创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		
		//通过TrackClient获得一个TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		
		//创建一个StorageServer的引用,可以是null
		StorageServer storageServer = null;
		
		//创建一个StorageClient,参数需要TrackerServer和StorageServer
		StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
		
		//使用StorageClient上传文件
		String[] str = client1.upload_appender_file("D:/timg.jpg", "jpg", null);
		for (String string : str) {
			System.out.println(string);
		}
	}
	
	//测试fastDfs工具类
	@Test
	public void FastDFSUtilTest()throws Exception{
		//测试环境不支持classpath ,正式环境支持
		FastDFSUtil util = new FastDFSUtil("I:/workspace01/e3-manager--web/src/main/resources/conf/tracker_server.conf");
		String uploadFile = util.uploadFile("D:/timg.jpg");
		System.out.println(uploadFile);
	}
}








































































