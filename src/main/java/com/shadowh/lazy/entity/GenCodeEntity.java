package com.shadowh.lazy.entity;

public class GenCodeEntity {
	private String tableName;
	private String moduleName;
	private String moduleNameCapi;//首字母大写的模块名
	private String moduleNameCn;
	private String entityPath;
	private String servicePath;
	private String serviceImplPath;
	private String mapperPath;
	private String mapperImplPath;
	
	private String author;//作者
	private String updateTime;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleNameCapi() {
		return moduleNameCapi;
	}
	public void setModuleNameCapi(String moduleNameCapi) {
		this.moduleNameCapi = moduleNameCapi;
	}
	public String getModuleNameCn() {
		return moduleNameCn;
	}
	public void setModuleNameCn(String moduleNameCn) {
		this.moduleNameCn = moduleNameCn;
	}
	public String getEntityPath() {
		return entityPath;
	}
	public void setEntityPath(String entityPath) {
		this.entityPath = entityPath;
	}
	public String getServicePath() {
		return servicePath;
	}
	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}
	public String getServiceImplPath() {
		return serviceImplPath;
	}
	public void setServiceImplPath(String serviceImplPath) {
		this.serviceImplPath = serviceImplPath;
	}
	public String getMapperPath() {
		return mapperPath;
	}
	public void setMapperPath(String mapperPath) {
		this.mapperPath = mapperPath;
	}
	public String getMapperImplPath() {
		return mapperImplPath;
	}
	public void setMapperImplPath(String mapperImplPath) {
		this.mapperImplPath = mapperImplPath;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "GenCodeEntity [tableName=" + tableName + ", moduleName=" + moduleName + ", moduleNameCapi="
				+ moduleNameCapi + ", moduleNameCn=" + moduleNameCn + ", entityPath=" + entityPath + ", servicePath="
				+ servicePath + ", serviceImplPath=" + serviceImplPath + ", mapperPath=" + mapperPath
				+ ", mapperImplPath=" + mapperImplPath + ", author=" + author + ", updateTime=" + updateTime + "]";
	}
	
}
