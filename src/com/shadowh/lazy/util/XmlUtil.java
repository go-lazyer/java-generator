package com.shadowh.lazy.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * 
 * @author hanchanghong
 * @date 2014年9月20日 上午12:31:21
 */
public class XmlUtil {
	public static Map<String,Object> elementToMap(Element element){
		if (element == null) {
			return null;
		}
		Map<String,Object>  elementMap=new HashMap<String,Object>();
		
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
		return elementMap;
	}

}
