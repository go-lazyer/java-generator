package com.shadowh.lazy.entity;

import org.apache.commons.lang.StringUtils;

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
