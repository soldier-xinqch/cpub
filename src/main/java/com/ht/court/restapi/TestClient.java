package com.ht.court.restapi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hx.util.JacksonUtil;

public class TestClient {
	
//	public static String loadJson (String url) {  
//        StringBuilder json = new StringBuilder();  
//        try {  
//            URL urlObject = new URL(url);  
//            URLConnection uc = urlObject.openConnection();  
//            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
//            String inputLine = null;  
//            while ( (inputLine = in.readLine()) != null) {  
//                json.append(inputLine);  
//            }  
//            in.close();  
//        } catch (MalformedURLException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//        return json.toString();  
//    }  
//	
	public static void main(String[] args) throws Exception { 
        String url = "http://192.168.1.108:8080/dts-dx/ht/court/courttb/ws/dddd";
////        		"http://api.map.baidu.com/telematics/v3/weather?location=%E6%88%90%E9%83%BD&output=json&ak=rnm8udmHdWaHFWZTO2tuTiG8";  
////        String url = "http://www.kuaidi100.com/query?type=yunda&postid=1201386764793";  
//        String json = loadJson(url);  
//        System.out.println(json);  
		TestClient dd = new TestClient();
		System.out.println(dd.getJsonString(url));;
		System.out.println();
		dd.jsonToObj(dd.getJsonString(url));
    } 

	protected String getJsonString(String urlPath) throws Exception {  
        URL url = new URL(urlPath);  
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
        connection.connect();  
        InputStream inputStream = connection.getInputStream();  
        //对应的字符编码转换  
        Reader reader = new InputStreamReader(inputStream, "UTF-8");  
        BufferedReader bufferedReader = new BufferedReader(reader);  
        String str = null;  
        StringBuffer sb = new StringBuffer();  
        while ((str = bufferedReader.readLine()) != null) {  
            sb.append(str);  
        }  
        reader.close();  
        connection.disconnect();  
        return sb.toString();  
    }  
	
	//解析
	public void jsonToObj(String jsonStr) throws Exception {  
		JSONObject jo = new JSONObject(jsonStr);
		System.out.println(jo.getString("code"));
    }
}
