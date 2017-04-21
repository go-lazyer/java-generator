package com.shadowh.lazy;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.joda.time.DateTime;

import com.shadowh.lazy.entity.DataSourceEntity;
import com.shadowh.lazy.entity.FieldEntity;
import com.shadowh.lazy.entity.GlobalEntity;
import com.shadowh.lazy.entity.JoinTableEntity;
import com.shadowh.lazy.entity.TableEntity;
import com.shadowh.lazy.util.FreeMarkerUtil;
import com.shadowh.lazy.util.JdbcUtil;

public class GenerateCode {
	private static DataSourceEntity dataSource;
	private static GlobalEntity globalEntity;
	private static List<TableEntity> tableList;

	public static void gencode(){
		gencode("lazy-config.xml");
	}
	/**
	 * 生成代码
	 * @date 2016年5月9日 下午2:03:19
	 */
	public static void gencode(String configXmlName){
		parseXml(configXmlName);
		JdbcUtil.updateDataSource(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
		globalEntity.setUpdateTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

		/** 模板引擎所需要的数据Map */
		Map<String, Object> viewMap = new HashMap<String, Object>();
		viewMap.put("updateTime", globalEntity.getUpdateTime());
		viewMap.put("author", globalEntity.getAuthor());
		viewMap.put("entityPackage", globalEntity.getEntityFilePackage());
		viewMap.put("mapperPackage", globalEntity.getMapperFilePackage());
		viewMap.put("servicePackage", globalEntity.getServiceFilePackage());
		viewMap.put("serviceImplPackage", globalEntity.getServiceImplFilePackage());
		viewMap.put("controllerPackage", globalEntity.getControllerFilePackage());

		for (TableEntity tableEntity : tableList) {
			List<FieldEntity> fieldList = FieldEntity.queryFieldList(dataSource.getDbname(), tableEntity);
			for (FieldEntity fieldEntity : fieldList) {
				if("1".equals(fieldEntity.getIsPrimaryKey())){
					viewMap.put("primaryKey", fieldEntity);
					break;
				}
			}
			tableEntity.setFields(fieldList);
			
			List<JoinTableEntity> manyTableList = tableEntity.getJoinTables();
			if(manyTableList!=null&&!manyTableList.isEmpty()){
				for (JoinTableEntity manyTableEntity : manyTableList) {
					List<FieldEntity> manyTablefield = FieldEntity.queryFieldList(dataSource.getDbname(), manyTableEntity);
					for (TableEntity t : tableList) {
						if(manyTableEntity.getTableName().equals(t.getTableName())){
							manyTableEntity.setModuleName(t.getModuleName());
							manyTableEntity.setModuleNameCapi(t.getModuleNameCapi());
						}
					}
					manyTableEntity.setFields(manyTablefield);
				}
			}
			viewMap.put("table", tableEntity);
			
			FreeMarkerUtil.crateFile(viewMap, "entity.java", globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Entity.java",true);
			FreeMarkerUtil.crateFile(viewMap, "entity-example.java",globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Example.java",true);
			FreeMarkerUtil.crateFile(viewMap, "mapper.xml", globalEntity.getMapperXmlFilePath() + "/" + globalEntity.getMapperXmlFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Mapper.xml",true);
			FreeMarkerUtil.crateFile(viewMap, "mapper.java", globalEntity.getMapperFilePath() + "/" + globalEntity.getMapperFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Mapper.java",true);
			FreeMarkerUtil.crateFile(viewMap, "mapper-extend.xml", globalEntity.getMapperXmlFilePath() + "/" + globalEntity.getMapperXmlFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "ExtendMapper.xml",false);
			FreeMarkerUtil.crateFile(viewMap, "mapper-extend.java", globalEntity.getMapperFilePath() + "/" + globalEntity.getMapperFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "ExtendMapper.java",false);
			FreeMarkerUtil.crateFile(viewMap, "service.java", globalEntity.getServiceFilePath() + "/" + globalEntity.getServiceFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Service.java",false);
			FreeMarkerUtil.crateFile(viewMap, "service-impl.java", globalEntity.getServiceImplFilePath() + "/" + globalEntity.getServiceImplFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "ServiceImpl.java",false);
			FreeMarkerUtil.crateFile(viewMap, "controller.java", globalEntity.getControllerFilePath() + "/" + globalEntity.getControllerFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Controller.java",false);
		}
	}

	private static void parseXml(String configXml) {
		try {
			if(StringUtils.isEmpty(configXml)){
				configXml="lazy-config.xml";
			}
			InputStream inputStream = GenerateCode.class.getResourceAsStream("/" + configXml);
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("UTF-8");
			Document document = saxReader.read(inputStream);
			Element rootElement = document.getRootElement();
			dataSource=DataSourceEntity.parseXml(rootElement);
			globalEntity=GlobalEntity.parseXml(rootElement);
			tableList=TableEntity.parseXml(rootElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Throwable {
		parseXml("");
	}
}
