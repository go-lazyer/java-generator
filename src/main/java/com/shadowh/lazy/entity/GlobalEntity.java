package com.shadowh.lazy.entity;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.dom4j.Element;

import com.shadowh.lazy.util.JsonUtil;

public class GlobalEntity {
	
	
	private String controllerFilePath;
	private String controllerFilePackage;

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

	public static GlobalEntity parseXml(Element rootElement){

		/**
		 * 获取全局配置
		 */
		GlobalEntity globalEntity = new GlobalEntity();

		Element propertyElement = rootElement.element("properties");
		Map<String, Object> propertyMap = JsonUtil.elementToMap(propertyElement);
		if (propertyMap != null) {
			globalEntity.setAuthor(MapUtils.getString(propertyMap, "author"));
		}

		Element entityElement = rootElement.element("entity");
		Map<String, Object> entityMap = JsonUtil.elementToMap(entityElement);
		if (entityMap != null) {
			globalEntity.setEntityFilePath(MapUtils.getString(entityMap, "file-path"));
			globalEntity.setEntityFilePackage(MapUtils.getString(entityMap, "file-package"));
		}

		// mapper
		Element mapperElement = rootElement.element("mapper");
		Map<String, Object> mapperMap = JsonUtil.elementToMap(mapperElement);
		if (mapperMap != null) {
			globalEntity.setMapperFilePath(MapUtils.getString(mapperMap, "file-path"));
			globalEntity.setMapperFilePackage(MapUtils.getString(mapperMap, "file-package"));
		}

		// mapperxml
		Element mapperXmlElement = rootElement.element("mapper-xml");
		Map<String, Object> mapperXmlMap = JsonUtil.elementToMap(mapperXmlElement);
		if (mapperXmlMap != null) {
			globalEntity.setMapperXmlFilePath(MapUtils.getString(mapperXmlMap, "file-path"));
			globalEntity.setMapperXmlFilePackage(MapUtils.getString(mapperXmlMap, "file-package"));
		}

		// service
		Element serviceElement = rootElement.element("service");
		Map<String, Object> serviceMap = JsonUtil.elementToMap(serviceElement);
		if (serviceMap != null) {
			globalEntity.setServiceFilePath(MapUtils.getString(serviceMap, "file-path"));
			globalEntity.setServiceFilePackage(MapUtils.getString(serviceMap, "file-package"));
		}

		// serviceimp
		Element serviceImplElement = rootElement.element("service-impl");
		Map<String, Object> serviceImplMap = JsonUtil.elementToMap(serviceImplElement);
		if (serviceImplMap != null) {
			globalEntity.setServiceImplFilePath(MapUtils.getString(serviceImplMap, "file-path"));
			globalEntity.setServiceImplFilePackage(MapUtils.getString(serviceImplMap, "file-package"));
		}
		
		// serviceimp
		Element controllerElement = rootElement.element("controller");
		Map<String, Object> controllerMap = JsonUtil.elementToMap(controllerElement);
		if (controllerMap != null) {
			globalEntity.setControllerFilePath(MapUtils.getString(controllerMap, "file-path"));
			globalEntity.setControllerFilePackage(MapUtils.getString(controllerMap, "file-package"));
		}
		return globalEntity;
	}
	
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
	
	public String getControllerFilePath() {
		return controllerFilePath;
	}

	public void setControllerFilePath(String controllerFilePath) {
		this.controllerFilePath = controllerFilePath;
	}

	public String getControllerFilePackage() {
		return controllerFilePackage;
	}

	public void setControllerFilePackage(String controllerFilePackage) {
		this.controllerFilePackage = controllerFilePackage;
	}

	@Override
	public String toString() {
		return "GlobalEntity [controllerFilePath=" + controllerFilePath + ", controllerFilePackage="
				+ controllerFilePackage + ", entityFilePath=" + entityFilePath + ", entityFilePackage="
				+ entityFilePackage + ", serviceFilePath=" + serviceFilePath + ", serviceFilePackage="
				+ serviceFilePackage + ", serviceImplFilePath=" + serviceImplFilePath + ", serviceImplFilePackage="
				+ serviceImplFilePackage + ", mapperFilePath=" + mapperFilePath + ", mapperFilePackage="
				+ mapperFilePackage + ", mapperXmlFilePath=" + mapperXmlFilePath + ", mapperXmlFilePackage="
				+ mapperXmlFilePackage + ", author=" + author + ", updateTime=" + updateTime + "]";
	}
}
