package com.shadowh.lazy.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author hanchanghong
 * @date 2014年9月20日 上午12:31:21
 */
public class JsonUtil {
	
	private static ObjectMapper mapper;   
    public static synchronized ObjectMapper getObjectMapper() {   
        if (mapper == null) {   
            mapper = new ObjectMapper();   
    		//序列化时去掉为空的字段
            mapper.setSerializationInclusion(Include.NON_NULL);
    		//设置时间格式
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        }
        return mapper;   
    }   
    
	public static void main(String[] args) throws Exception {
		String uri="http://121.199.34.79:5555/v2/cbclk?m=123";
		System.out.println(uri);
		uri=uri.replaceAll("\\?", "321");
		System.out.println(uri);
	}
}
