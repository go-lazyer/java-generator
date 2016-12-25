package com.shadowh.lazy.entity;

/**
 * 数据源 
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class DatabaseEntity {
	private String name;
	private String host;
	private String username;
	private String password;
	private String port;
	public String getName() {
		return name;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setName(String name) {
		this.name = name;
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
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "DatabaseEntity [name=" + name + ", username=" + username + ", password=" + password + ", port=" + port
				+ "]";
	}

}
