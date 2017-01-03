package com.shadowh.lazy.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 
 * @author hanchanghong
 * @date 2014年9月20日 上午12:31:21
 */
public class JsonUtil {

	private static ObjectMapper mapperJson;
	private static ObjectMapper mapperXml;

	static {
		mapperJson = new ObjectMapper();
		mapperXml = new XmlMapper();
		// 序列化时去掉为空的字段
		mapperJson.setSerializationInclusion(Include.NON_NULL);
		// 设置时间格式
		mapperJson.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 将json字符串转换为实体类对象
	 * @param json
	 * @return
	 */
	public static <T> T jsonToObject(String json, Class<T> c) {
		try {
			Object object = mapperJson.readValue(json, c);
			return (T) object;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象object转换为json字符串.
	 * @param object
	 * @return
	 */
	public static String objToJson(Object object) {
		try {
			String json = mapperJson.writeValueAsString(object);
			return json;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将Xm字符串转换为实体类对象
	 * @param json
	 * @return
	 */
	public static <T> T xmlToObject(String xml, Class<T> c) {
		try {
			Object object = mapperXml.readValue(xml, c);
			return (T) object;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将对象object转换为Xm字符串.
	 * @param object
	 * @return
	 */
	public static String objToXml(Object object) {
		try {
			String xml = mapperXml.writeValueAsString(object);
			return xml;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String,Object> elementToMap(Element element){
		Map<String,Object> elementMap=null;
		if (element != null) {
			elementMap=new HashMap<String,Object>();
			
			List<Attribute> attributes = element.attributes();
			for (Attribute attribute : attributes) {
				elementMap.put(attribute.getName(), attribute.getValue());
			}
			List<Element> elements = element.elements("property");
			for (Element e : elements) {
				Attribute nameAttribute = e.attribute("name");
				Attribute valueAttribute = e.attribute("value");
				elementMap.put(nameAttribute.getValue(), valueAttribute.getValue());
			}
		}
		return elementMap;
	}
	
	public static void main(String[] args) throws Throwable {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		String objToJson = JsonUtil.objToJson(list);
		System.out.println(objToJson);
		
		String readFileToString = FileUtils.readFileToString(new File("g://1.txt"));
		Map jsonToObject = JsonUtil.jsonToObject(readFileToString,Map.class);
		System.out.println(jsonToObject);
	}

}
