package com.shadowh.lazy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.joda.time.DateTime;

import com.shadowh.lazy.entity.DataSourceEntity;
import com.shadowh.lazy.entity.FieldEntity;
import com.shadowh.lazy.entity.GlobalConfigEntity;
import com.shadowh.lazy.entity.TableEntity;
import com.shadowh.lazy.util.FreeMarkerUtil;
import com.shadowh.lazy.util.StringUtil;
import com.shadowh.util.JsonUtil;

public class GenerateCode {
	private static DataSourceEntity dataSource;
	private static GlobalConfigEntity globalConfigEntity;
	private static List<TableEntity> tableList;

	public void gencode(){
		gencode("lazy-config.xml");
	}
	/**
	 * 生成代码
	 * @date 2016年5月9日 下午2:03:19
	 */
	public void gencode(String configXml){
		parseXml(configXml);
		globalConfigEntity.setUpdateTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

		/** 模板引擎所需要的数据Map */
		Map<String, Object> viewMap = new HashMap<String, Object>();
		viewMap.put("updateTime", globalConfigEntity.getUpdateTime());
		viewMap.put("author", globalConfigEntity.getAuthor());
		viewMap.put("entityPackage", globalConfigEntity.getEntityFilePackage());
		viewMap.put("mapperPackage", globalConfigEntity.getMapperFilePackage());
		viewMap.put("mapperPackage", globalConfigEntity.getMapperFilePackage());

		FieldEntity fieldEntity = new FieldEntity();
		for (TableEntity tableEntity : tableList) {
			viewMap.put("tableName", tableEntity.getTableName());
			viewMap.put("moduleName", tableEntity.getModuleName());
			viewMap.put("moduleNameCapi", tableEntity.getModuleNameCapi());
			List<FieldEntity> fieldList = fieldEntity.queryFieldList(dataSource, tableEntity.getTableName());
			viewMap.put("attrList", fieldList);
			FreeMarkerUtil.crateFile(viewMap, "entity.ftl", globalConfigEntity.getEntityFilePath() + "/" + globalConfigEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Entity.java");
			FreeMarkerUtil.crateFile(viewMap, "example.ftl", globalConfigEntity.getEntityFilePath() + "/" + globalConfigEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Example.java");
			FreeMarkerUtil.crateFile(viewMap, "mapper.xml", globalConfigEntity.getMapperXmlFilePath() + "/" + globalConfigEntity.getMapperXmlFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Mapper.xml");
			FreeMarkerUtil.crateFile(viewMap, "mapper.ftl", globalConfigEntity.getMapperFilePath() + "/" + globalConfigEntity.getMapperFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Mapper.java");
		}
	}

	public static void parseXml(String configXml) {
		try {
			if(StringUtil.isEmpty(configXml)){
				configXml="lazy-config.xml";
			}
			InputStream inputStream = GenerateCode.class.getResourceAsStream("/" + configXml);
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("UTF-8");
			Document document = saxReader.read(inputStream);
			Element rootElement = document.getRootElement();
			/**
			 * 获取数据源 先通过属性获取，再通过子元素获取
			 */
			Element dataSourceElement = rootElement.element("data-source");
			if (dataSourceElement != null) {
				dataSource = new DataSourceEntity();
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
			/**
			 * 获取table 先通过属性获取，再通过子元素获取
			 */
			List<Element> tablesElements = rootElement.elements("table");
			if (tablesElements != null) {
				tableList = new ArrayList<TableEntity>();
				for (Element tableElement : tablesElements) {
					TableEntity tableEntity = new TableEntity();
					List<Attribute> tableAttributes = tableElement.attributes();
					for (Attribute attribute : tableAttributes) {
						switch (attribute.getName()) {
						case "id":
							tableEntity.setId(attribute.getValue());
							break;
						case "table-name":
							tableEntity.setTableName(attribute.getValue());
							break;
						case "module-name":
							tableEntity.setModuleName(attribute.getValue());
							break;
						default:
							break;
						}
					}

					List<Element> tableElements = tableElement.elements("property");
					for (Element element : tableElements) {
						Attribute nameAttribute = element.attribute("name");
						Attribute valueAttribute = element.attribute("value");
						switch (nameAttribute.getValue()) {
						case "id":
							tableEntity.setId(valueAttribute.getValue());
							break;
						case "table-name":
							tableEntity.setTableName(valueAttribute.getValue());
							break;
						case "module-name":
							tableEntity.setModuleName(valueAttribute.getValue());
							break;
						default:
							break;
						}
					}
					tableList.add(tableEntity);
				}
				System.out.println(tableList);
			}

			/**
			 * 获取全局配置
			 */
			globalConfigEntity = new GlobalConfigEntity();

			Element propertyElement = rootElement.element("properties");
			Map<String, Object> propertyMap = JsonUtil.elementToMap(propertyElement);
			if (propertyMap != null) {
				globalConfigEntity.setAuthor(MapUtils.getString(propertyMap, "author"));
			}

			Element entityElement = rootElement.element("entity");
			Map<String, Object> entityMap = JsonUtil.elementToMap(entityElement);
			if (entityMap != null) {
				globalConfigEntity.setEntityFilePath(MapUtils.getString(entityMap, "file-path"));
				globalConfigEntity.setEntityFilePackage(MapUtils.getString(entityMap, "file-package"));
			}

			// mapper
			Element mapperElement = rootElement.element("mapper");
			Map<String, Object> mapperMap = JsonUtil.elementToMap(mapperElement);
			if (mapperMap != null) {
				globalConfigEntity.setMapperFilePath(MapUtils.getString(mapperMap, "file-path"));
				globalConfigEntity.setMapperFilePackage(MapUtils.getString(mapperMap, "file-package"));
			}

			// mapperxml
			Element mapperXmlElement = rootElement.element("mapper-xml");
			Map<String, Object> mapperXmlMap = JsonUtil.elementToMap(mapperXmlElement);
			if (mapperXmlMap != null) {
				globalConfigEntity.setMapperXmlFilePath(MapUtils.getString(mapperXmlMap, "file-path"));
				globalConfigEntity.setMapperXmlFilePackage(MapUtils.getString(mapperXmlMap, "file-package"));
			}

			// service
			Element serviceElement = rootElement.element("service");
			Map<String, Object> serviceMap = JsonUtil.elementToMap(serviceElement);
			if (serviceMap != null) {
				globalConfigEntity.setServiceFilePath(MapUtils.getString(serviceMap, "file-path"));
				globalConfigEntity.setServiceFilePackage(MapUtils.getString(serviceMap, "file-package"));
			}

			// serviceimp
			Element serviceImplElement = rootElement.element("service-impl");
			Map<String, Object> serviceImplMap = JsonUtil.elementToMap(serviceImplElement);
			if (serviceImplMap != null) {
				globalConfigEntity.setServiceImplFilePath(MapUtils.getString(serviceImplMap, "file-path"));
				globalConfigEntity.setServiceImplFilePackage(MapUtils.getString(serviceImplMap, "file-package"));
			}

			System.out.println(globalConfigEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Throwable {
		parseXml("");
	}
}
