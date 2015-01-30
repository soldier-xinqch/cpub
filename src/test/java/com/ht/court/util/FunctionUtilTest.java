package com.ht.court.util;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class FunctionUtilTest {

	@Test
	public void testGetVODFiles() throws Exception {
		System.out.println(System.getProperty("user.dir"));
		String folder=System.getProperty("user.dir") + File.separator
				+ "src" + File.separator + "test" + File.separator + "java"
				+ File.separator + "com" + File.separator + "ht"
				+ File.separator + "court"
				+ File.separator + "util";
		File f=new File(folder);
		//System.out.println(f.isDirectory());
		List<String> files=FunctionUtil.getVODFiles(folder, "java", true);
		for(String file:files){
			System.out.println("file=" + file);
		}
		assertEquals(1,files.size());
	}

	@Test
	public void testGenVodFilesRelativePaths() throws Exception {
		List<String> vodFiles=new ArrayList<String>();
		vodFiles.add("001.flv");
		vodFiles.add("002.flv");
		vodFiles.add("003.flv");
		List<String> paths=FunctionUtil.genVodFilesRelativePaths("刑1张三审001", new Date(), vodFiles, true);
		for(String path:paths){
			System.out.println("path=" + path);
		}
	}

	@Test
	public void testGenVODUrls() throws Exception {
		List<String> vodFiles=new ArrayList<String>();
		vodFiles.add("001.flv");
		vodFiles.add("002.flv");
		vodFiles.add("003.flv");
		List<String> paths=FunctionUtil.genVodFilesRelativePaths("刑1张三审001", new Date(), vodFiles, true);
		List<String> urls=FunctionUtil.genVODUrls("rtmp", "192.168.200.12", 5085, "live", "streams", paths);
		for(String url:urls){
			System.out.println("url=" + url);
		}
	}

}
