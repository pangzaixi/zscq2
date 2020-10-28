package com.pjgl.stock.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pjgl.stock.bean.Stock;
import com.pjgl.stock.service.StockService;

@Controller
public class hzsController {

	@Autowired 
	public StockService stockService;
	
	@RequestMapping(value="/getStock.do",method={RequestMethod.POST})
	@ResponseBody
	public void pushInfo(HttpServletRequest request, HttpServletResponse response ) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		//参数放到url中的获取方式
		String id =request.getParameter("id");
		if(id == null || "".equals(id.trim())){
			return ;
		}
		id = stockService.selectOrgMapping(id);
		if(id == null || "".equals(id.trim())){
			return ;
		}
//		// 流方式获取post参数
//		StringBuffer sb = new StringBuffer();
//		InputStream is = request.getInputStream();
//		InputStreamReader isr = new InputStreamReader(is);
//		BufferedReader br = new BufferedReader(isr);
//		String s = "";
//		while ((s = br.readLine()) != null) {
//			sb.append(s);
//		}
//		  sb.toString();
//		Gson gson = new Gson();
//		Map map2 = gson.fromJson(sb.toString(), Map.class);//如果没有传参，则map=null
//		System.out.println(map2.get("id"));
		
		Gson gson = new Gson();
		Map map = new HashMap<Object,Object>();
		map.put("companyId", id);
		map.put("begin", 0);
		map.put("end", 5);
		List<Stock> list = stockService.selectStock(map);
		String aaa= gson.toJson(list);
		String encoding = System.getProperty(aaa); 
		response.setContentType("text/html;charset=UTF-8");//这些设置必须要放在getWriter的方法之前，
		response.getWriter().print(aaa);
	}
	public static void main(String args[]) throws ClientProtocolException, IOException { 
	    System.out.println("Hello World!".hashCode());
	    
//	    HttpPost httpPost = new HttpPost("http://172.20.15.97:8080/pjgl/pushInfo.do?platenum=1&workdate=2018-11-11");// 创建httpPost
	    HttpPost httpPost = new HttpPost("http://172.20.15.97:8080/pjgl/getStock.do?id=3");// 创建httpPost
//		httpPost.setHeader("Accept", "application/json"); 
		httpPost.setHeader("Content-Type", "text/plain;charset=UTF-8");
		httpPost.setHeader("type","post"); 
		httpPost.setHeader("dataType","json");
		String charSet = "UTF-8";
		
		//将参数写到header中，也可以写到上面的url中，上面方式用request.getParameter就能获得，下面方式需要用流的方式获取
		StringEntity entity;		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("id", "3");
		Gson gson = new Gson();
		String aaa= gson.toJson(map2);
		entity = new StringEntity(aaa, charSet);
		entity.setContentType("text/json");
		entity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
		httpPost.setEntity(entity);
		

	    CloseableHttpResponse response = null;
	     
	    try {
	    	CloseableHttpClient httpclient = HttpClients.createDefault();
	    	response = httpclient.execute(httpPost);
	        StatusLine status = response.getStatusLine();
	        int state = status.getStatusCode();
	        if (state == HttpStatus.SC_OK) {
	        	HttpEntity responseEntity = response.getEntity();
	        	String jsonString = EntityUtils.toString(responseEntity);
//	        	System.out.println(params);
//	        	System.out.println(jsonString);
	        	
	        	if(jsonString.contains("500")){
	        	
	        	}
	        	
	        }
	        else{
	        	HttpEntity responseEntity = response.getEntity();
	        	String jsonString = EntityUtils.toString(responseEntity);
	        	System.out.println(jsonString);
	        	
			}
	    }catch (Exception e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
	    }	
	    finally {
	        if (response != null) {
	            try {
	                response.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	} 
}
