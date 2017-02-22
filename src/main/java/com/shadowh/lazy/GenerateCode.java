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
		viewMap.put("mapperPackage", globalEntity.getMapperFilePackage());

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
			
			FreeMarkerUtil.crateFile(viewMap, "entity.ftl", globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + ".java",true);
			FreeMarkerUtil.crateFile(viewMap, "extend.ftl", globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Entity.java",false);
			FreeMarkerUtil.crateFile(viewMap, "example.ftl",globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Example.java",true);
			FreeMarkerUtil.crateFile(viewMap, "mapper.xml", globalEntity.getMapperXmlFilePath() + "/" + globalEntity.getMapperXmlFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Mapper.xml",true);
			FreeMarkerUtil.crateFile(viewMap, "mapper.ftl", globalEntity.getMapperFilePath() + "/" + globalEntity.getMapperFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Mapper.java",true);
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
//		parseXml("");
	}
}
