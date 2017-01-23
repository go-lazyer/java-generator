package com.shadowh.lazy.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;

import com.shadowh.lazy.util.StringUtil;

/**
 * 数据源 
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class DataSourceEntity {
	private String url;
	private String dbname;
	private String username;
	private String password;
	
	public static DataSourceEntity parseXml(Element rootElement){
		/**
		 * 获取数据源 先通过属性获取，再通过子元素获取
		 */
		DataSourceEntity dataSource = new DataSourceEntity();
		Element dataSourceElement = rootElement.element("data-source");
		if (dataSourceElement != null) {
			List<Attribute> dataSourceAttributes = dataSourceElement.attributes();
			for (Attribute attribute : dataSourceAttributes) {
				switch (attribute.getName()) {
				case "url":
					dataSource.setUrl(attribute.getValue());
					break;
				case "username":
					dataSource.setUsername(attribute.getValue());
					break;
				case "password":
					dataSource.setPassword(attribute.getValue());
					break;
				default:
					break;
				}
			}

			List<Element> dataSourceElements = dataSourceElement.elements("property");
			for (Element element : dataSourceElements) {
				Attribute nameAttribute = element.attribute("name");
				Attribute valueAttribute = element.attribute("value");
				switch (nameAttribute.getValue()) {
				case "url":
					dataSource.setUrl(valueAttribute.getValue());
					break;
				case "username":
					dataSource.setUsername(valueAttribute.getValue());
					break;
				case "password":
					dataSource.setPassword(valueAttribute.getValue());
					break;
				default:
					break;
				}
			}
		}
		return dataSource;
	}
	
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		
		this.url = url;
		if(StringUtils.isNotEmpty(url)){
			url = url.split("\\?")[0];
			url=url.split("/")[3];
			this.dbname=url;
		}
	}

}
