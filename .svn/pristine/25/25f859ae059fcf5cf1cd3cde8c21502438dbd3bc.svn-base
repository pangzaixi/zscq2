package com.pjgl.beidou.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pjgl.beidou.bean.ParamEntity;
import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.stock.bean.Stock;
import com.pjgl.stock.service.StockService;
import com.yewu.zscq.bean.User;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class BeidouController {

//	@Autowired
//    public CompanyService companyService;
//	
//	@Autowired 
//	public StockService stockService;
//	
//	MongoCollection collection;
//	
//	MongoClient mClient176 = new MongoClient("172.20.10.176");
//	MongoClient mClient177 = new MongoClient("172.20.10.177");
//	MongoDatabase db176 = mClient176.getDatabase("agmc");
//	MongoDatabase db177 = mClient177.getDatabase("agmc");
//	
////	@ResponseBody//此注解不能省略 否则ajax无法接受返回值
//	@RequestMapping("/getGuiJi.do")
//	public @ResponseBody String aaa(HttpServletRequest request, HttpServletResponse response ,@RequestParam String platenum,@RequestParam String workdate){
////	public @ResponseBody String aaa(HttpServletRequest request, HttpServletResponse response ,@RequestParam String platenum,@RequestParam String workdate){
//		System.out.println(platenum);
//		User user = (User)request.getSession().getAttribute("user");
//		Map<Object,Object> map2 = new HashMap<Object,Object>();
//		map2.put("begin", 0);
//		if("吉YS-58531".equals(platenum)){
//			map2.put("end", 1000000);
//		}else{
//			map2.put("end", 1000);
//		}
//		if("2018-06-27".equals(workdate)){
//			map2.put("begin", 2000);
//			map2.put("end", 20);
//		}
//		if("2018-06-28".equals(workdate)){
//			map2.put("begin", 0);
//			map2.put("end", 50000);
//		}
//		if("2018-06-29".equals(workdate)){
//			map2.put("begin", 5000);
//			map2.put("end", 2000);
//		}
//		map2.put("companyId", "3");
//		List<Stock> list = stockService.selectStock(map2);
//		
//		List<String[]> list3 = new ArrayList<String[]>();
//		for(int i=0;i<list.size();i++){
//			String[] b5 = {String.valueOf(list.get(i).getTotalNum()),String.valueOf(list.get(i).getAgeragePrice())};
//			 list3.add(b5);
//		}
//		
//		Map<String,Object> map3 = new HashMap<String,Object>();
//		 String [][] test3 = new String[list.size()][2];
//		 list3.toArray(test3);
//		 map3.put("aa", test3);
//		 Gson gson = new Gson();
//		 String aaa= gson.toJson(map3);
//		return aaa;
//		
//
//	}
//	
//	/**
//	  * 获得全天轨迹   
//	  * @param platenum
//	  * @param workdate
//	  * @param town
//	  * @return
//	  */
//	 private String getAllGuijiForReport(String platenum,String workdate,String town){
////	      "t":[{t1":"","t2":"","t3":"","t4":"","t5":"","t6":""..........}]
////	    	ShebeiService shebeiService = (ShebeiService)MyApplicationContextUtil.getBean("shebeiService");
//	    	
////	    	MongoClient mClient ;
//	    	
//		
//	    	
//	    	
//	    		collection = db176.getCollection("caams_task_data_220182203");
//	    	
////	    		collection = db177.getCollection("caams_task_data_"+town);
//	    	
//	    	
//	    	    		
//	    		
//	    		Document doc = new Document("workdate","2018-06-28");
//	    			doc.append("plate_num", "吉YS-58531");
//	    		
//	    		
//	    		Document doc2 = new Document("writetime",1);//按照写入日期排序，1升序，-1降序
////	    		FindIterable<Document> iter1 = collection.find(doc).sort(doc2).limit(1);
//	    		FindIterable<Document> iter1 = collection.find(doc).sort(doc2);
//	 
//	    		List<String[]> list2 = new ArrayList<String[]>();
//	    		
//	    		StringBuffer result = new StringBuffer();
//	    		result.append("[");
//	    		
//	    		iter1.forEach(new Block<Document>() {
//	    			public void apply(Document _doc1) {
////	    				System.out.println(_doc1.toJson());//测试用，生产环境注销掉，提高效率
////	    		      "t":[{t1":"","t2":"","t3":"","t4":"","t5":"","t6":""..........}]
//	    				 String[] b5 = {(String)_doc1.get("lat"),(String)_doc1.get("lng")};
//	    				 list2.add(b5);
//	    				
//	    				result.append("{");
//	    				result.append("\"t1\""+":"+"\""+_doc1.get("lat")+"\",");//经度
//	    				result.append("\"t2\""+":"+"\""+_doc1.get("lng")+"\",");//纬度
//	    				result.append("\"t3\""+":"+"\""+_doc1.get("writetime")+"\",");//定位时间
//	    				result.append("\"t4\""+":"+"\"subsoiling_work\",");//机具识别号
//	    				result.append("\"t5\""+":"+"\""+_doc1.get("gs")+"\",");//耕深
//	    				result.append("\"t6\""+":"+"0,");//方向
//	    				result.append("\"t7\""+":"+"\""+_doc1.get("cs")+"\",");//速度
////	    				result.append("\"t8\""+":"+"\""+_doc1.get("fuk")+"\",");//农具宽度
//	    				
//	    				result.append("\"t8\""+":"+(int)(Double.valueOf((String)_doc1.get("fuk"))*1000D)+",");//农具宽度
//	    				if("1".equals(_doc1.get("pic_path"))){
//	    					result.append("\"t9\""+":"+"\"http://114.255.9.78:9926/"+_doc1.get("pic_path")+"\"");//图片路径
//	    				}else{
//	    					result.append("\"t9\""+":"+"\"\"");//图片路径
//	    				}
//	    				
//	    				result.append("},");
//	    			}
//	    		});
//	    		
//	    		if(result.toString().length() != 1){
//	    			result.deleteCharAt(result.length()-1);
//	    		}
//	    		result.append("]");
//	    		
////	    		return result.toString();
//	    		
//	    		 Map<String,Object> map2 = new HashMap<String,Object>();
//	    		 String [][] test = new String[list2.size()][2];
//	    		 list2.toArray(test);
//	    		 map2.put("aa", test);
//	    		 Gson gson = new Gson();
//	    		 String aaa= gson.toJson(map2);
//	    		 if(list2.size()==0){
//	    			 return null;
//	    		 }else{
//	    			 return aaa; 
//	    		 }
//	    		
//	    }	
///**
// *  
// * @param request
// * @param response
// * @param platenum
// * @param workdate
// * @return
// * @throws IOException 
// */
////@RequestMapping(value="/pushInfo.do")
//@RequestMapping(value="/pushInfo.do",method={RequestMethod.POST})
//@ResponseBody
//public String pushInfo(HttpServletRequest request, HttpServletResponse response ) throws IOException{
//	
//	
//	String platenum = request.getParameter("platenum");
//	// 获取post参数
//	StringBuffer sb = new StringBuffer();
//	InputStream is = request.getInputStream();
//	InputStreamReader isr = new InputStreamReader(is);
//	BufferedReader br = new BufferedReader(isr);
//	String s = "";
//	while ((s = br.readLine()) != null) {
//		sb.append(s);
//	}
//	  sb.toString();
//	  
//	  
//	  Gson gson = new Gson();
//	  Map map2 = gson.fromJson(sb.toString(), Map.class);//如果没有传参，则map=null
//
//	System.out.println(map2.get("platenum"));
//	System.out.println(map2.get("workdate"));
//	ArrayList zb = (ArrayList)map2.get("zb");
//	ArrayList aaaaa = (ArrayList)zb.get(0);
//
//	
////	{"NStatus":0,"NDesc":"success","Result": ""}
//	map2.clear();
//	map2.put("NStatus", 0);
//	map2.put("NDesc", "success");
//	map2.put("Result", "");
//	
//	return gson.toJson(map2);
//}
//public static void main(String args[]) throws ClientProtocolException, IOException { 
//    System.out.println("Hello World!".hashCode());
//    
////    HttpPost httpPost = new HttpPost("http://172.20.15.97:8080/pjgl/pushInfo.do?platenum=1&workdate=2018-11-11");// 创建httpPost
//    HttpPost httpPost = new HttpPost("http://172.20.15.97:8080/pjgl/pushInfo.do?");// 创建httpPost
////	httpPost.setHeader("Accept", "application/json"); 
//	httpPost.setHeader("Content-Type", "text/plain;charset=UTF-8");
//	httpPost.setHeader("type","post"); 
//	httpPost.setHeader("dataType","json");
//	String charSet = "UTF-8";
//	
//	StringEntity entity;		
//	
//
//	 Map<String,Object> map2 = new HashMap<String,Object>();
//	 
//	 map2.put("platenum", "11");
//	 map2.put("workdate", "22");
//	 Gson gson = new Gson();
//	 String aaa= gson.toJson(map2);
//
//	 
//	 
//	entity = new StringEntity(aaa, charSet);
//	entity.setContentType("text/json");
//	entity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
//	httpPost.setEntity(entity);
//	
//
//    CloseableHttpResponse response = null;
//     
//    try {
//    	CloseableHttpClient httpclient = HttpClients.createDefault();
//    	response = httpclient.execute(httpPost);
//        StatusLine status = response.getStatusLine();
//        int state = status.getStatusCode();
//        if (state == HttpStatus.SC_OK) {
//        	HttpEntity responseEntity = response.getEntity();
//        	String jsonString = EntityUtils.toString(responseEntity);
////        	System.out.println(params);
////        	System.out.println(jsonString);
//        	
//        	if(jsonString.contains("500")){
//        	
//        	}
//        	
//        }
//        else{
//        	HttpEntity responseEntity = response.getEntity();
//        	String jsonString = EntityUtils.toString(responseEntity);
//        	System.out.println(jsonString);
//        	
//		}
//    }catch (Exception e) {
////		// TODO Auto-generated catch block
//		e.printStackTrace();
//    }	
//    finally {
//        if (response != null) {
//            try {
//                response.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//} 

}
