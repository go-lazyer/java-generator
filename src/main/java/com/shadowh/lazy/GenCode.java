package com.shadowh.lazy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.shadowh.lazy.entity.DatabaseEntity;
import com.shadowh.lazy.entity.GenCodeEntity;
import com.shadowh.lazy.entity.TableEntity;
import com.shadowh.lazy.util.FreeMarkerUtil;
import com.shadowh.lazy.util.StringUtil;

public  class GenCode {
	
	/**
	 * 生成代码
	 * @date 2016年5月9日 下午2:03:19
	 */
	public String gencode(DatabaseEntity dbEntity,GenCodeEntity gencodeEntity) throws Exception {
		
		gencodeEntity.setUpdateTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		gencodeEntity.setModuleNameCapi(StringUtil.firstUpperCase(gencodeEntity.getModuleName()));

		/** 模板引擎所需要的数据Map */  
		Map<String,Object> viewMap = new HashMap<String, Object>();  
		viewMap.put("updateTime",gencodeEntity.getUpdateTime());
		viewMap.put("moduleNameCn",gencodeEntity.getModuleNameCn());
		viewMap.put("author",gencodeEntity.getAuthor());
		viewMap.put("moduleName",gencodeEntity.getModuleName());
		viewMap.put("moduleNameCapi", gencodeEntity.getModuleNameCapi());
		viewMap.put("entityPackage",gencodeEntity.getEntityFilePackage());
		viewMap.put("mapperPackage",gencodeEntity.getMapperFilePackage());
		viewMap.put("tableName",gencodeEntity.getTableName());
		
		TableEntity table=new TableEntity();
		List<TableEntity> fieldList = table.queryFieldList(dbEntity, gencodeEntity.getTableName());
		viewMap.put("attrList",fieldList);  
		FreeMarkerUtil.crateFile(viewMap, "mapper.xml", gencodeEntity.getMapperXmlFilePath()+"/"+gencodeEntity.getMapperXmlFilePackage().replace(".", "/")+"/"+gencodeEntity.getModuleNameCapi()+"Mapper.xml");  
		FreeMarkerUtil.crateFile(viewMap, "mapper.ftl", gencodeEntity.getMapperFilePath()+"/"+gencodeEntity.getMapperFilePackage().replace(".", "/")+"/"+gencodeEntity.getModuleNameCapi()+"Mapper.java");  
		/**
		 * 生成Mapper.java
		 */
		FreeMarkerUtil.crateFile(viewMap, "entity.ftl", gencodeEntity.getEntityFilePath()+"/"+gencodeEntity.getEntityFilePackage().replace(".", "/")+"/"+gencodeEntity.getModuleNameCapi()+"Entity.java");  
		
		return "";
	}

}
