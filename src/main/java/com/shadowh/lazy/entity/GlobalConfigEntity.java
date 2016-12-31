package com.shadowh.lazy.entity;

public class GlobalConfigEntity {
	private String entityFilePath;
	private String entityFilePackage;
	
	private String serviceFilePath;
	private String serviceFilePackage;
	
	private String serviceImplFilePath;
	private String serviceImplFilePackage;
	
	private String mapperFilePath;
	private String mapperFilePackage;
	
	private String mapperXmlFilePath;
	private String mapperXmlFilePackage;
	
	private String author;//作者
	private String updateTime;

	public String getEntityFilePath() {
		return entityFilePath;
	}
	public void setEntityFilePath(String entityFilePath) {
		this.entityFilePath = entityFilePath;
	}
	public String getServiceFilePath() {
		return serviceFilePath;
	}
	public void setServiceFilePath(String serviceFilePath) {
		this.serviceFilePath = serviceFilePath;
	}
	public String getServiceImplFilePath() {
		return serviceImplFilePath;
	}
	public void setServiceImplFilePath(String serviceImplFilePath) {
		this.serviceImplFilePath = serviceImplFilePath;
	}
	public String getMapperFilePath() {
		return mapperFilePath;
	}
	public void setMapperFilePath(String mapperFilePath) {
		this.mapperFilePath = mapperFilePath;
	}
	public String getMapperXmlFilePath() {
		return mapperXmlFilePath;
	}
	public void setMapperXmlFilePath(String mapperXmlFilePath) {
		this.mapperXmlFilePath = mapperXmlFilePath;
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
	
	public String getEntityFilePackage() {
		return entityFilePackage;
	}
	public void setEntityFilePackage(String entityFilePackage) {
		this.entityFilePackage = entityFilePackage;
	}
	public String getServiceFilePackage() {
		return serviceFilePackage;
	}
	public void setServiceFilePackage(String serviceFilePackage) {
		this.serviceFilePackage = serviceFilePackage;
	}
	public String getServiceImplFilePackage() {
		return serviceImplFilePackage;
	}
	public void setServiceImplFilePackage(String serviceImplFilePackage) {
		this.serviceImplFilePackage = serviceImplFilePackage;
	}
	public String getMapperFilePackage() {
		return mapperFilePackage;
	}
	public void setMapperFilePackage(String mapperFilePackage) {
		this.mapperFilePackage = mapperFilePackage;
	}
	public String getMapperXmlFilePackage() {
		return mapperXmlFilePackage;
	}
	public void setMapperXmlFilePackage(String mapperXmlFilePackage) {
		this.mapperXmlFilePackage = mapperXmlFilePackage;
	}
	@Override
	public String toString() {
		return "GlobalConfigEntity [entityFilePath=" + entityFilePath + ", entityFilePackage=" + entityFilePackage + ", serviceFilePath=" + serviceFilePath + ", serviceFilePackage=" + serviceFilePackage + ", serviceImplFilePath=" + serviceImplFilePath + ", serviceImplFilePackage=" + serviceImplFilePackage + ", mapperFilePath=" + mapperFilePath + ", mapperFilePackage=" + mapperFilePackage + ", mapperXmlFilePath=" + mapperXmlFilePath + ", mapperXmlFilePackage=" + mapperXmlFilePackage + ", author=" + author + ", updateTime=" + updateTime + "]";
	}
	
	
	
}
