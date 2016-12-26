package com.shadowh.lazy;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadowh.lazy.entity.DatabaseEntity;
import com.shadowh.lazy.entity.FieldEntity;
import com.shadowh.lazy.entity.GenCodeEntity;
import com.shadowh.lazy.util.FreeMarkerUtil;
import com.shadowh.lazy.util.JdbcUtil;
import com.shadowh.lazy.util.JsonUtil;
import com.shadowh.lazy.util.StringUtil;

public  class Gencode {
	
	private static ObjectMapper objectMapper = JsonUtil.getObjectMapper();
	/**
	 * @param paraMap
	 * @return
	 * @author hanchanghong
	 * @date 2016年5月2日 下午9:28:20
	 */
	public List<FieldEntity> queryFieldList(DatabaseEntity dbsEntity, String tableName) {
		
		JdbcUtil jdbcUtil=new JdbcUtil(dbsEntity);
		String sql="select column_name field,data_type fieldType,column_comment comment,column_key isPrimaryKey  from information_schema.COLUMNS where table_name = '"+tableName+"' and table_schema = '"+dbsEntity.getName()+"'";
		List<FieldEntity> fieldList = null;
		try {
			fieldList = jdbcUtil.query(FieldEntity.class,sql);
			for (FieldEntity field : fieldList) {
				field.setAttribute( StringUtil.underlineToCamel(field.getField()));
				if("PRI".equals(field.getIsPrimaryKey())){
					field.setIsPrimaryKey("1");
				}
				if(field.getFieldType().indexOf("int")!=-1){
					field.setAttrType("Integer");
				}else{
					field.setAttrType("String");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fieldList;
	}
	/**
	 * 生成代码换行代码地址
	 * @param paraMap
	 * @return
	 * @author hanchanghong
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @date 2016年5月9日 下午2:03:19
	 */
	public String gencode(DatabaseEntity dbEntity,GenCodeEntity gencodeEntity) throws Exception {
		
		gencodeEntity.setUpdateTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		gencodeEntity.setModuleNameCapi(StringUtil.firstUpperCase(gencodeEntity.getModuleName()));
		List<FieldEntity> fieldList = this.queryFieldList(dbEntity, gencodeEntity.getTableName());
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
		
		viewMap.put("attrList",fieldList);  
		FreeMarkerUtil.crateFile(viewMap, "mapper.xml", gencodeEntity.getMapperXmlFilePath()+"/"+gencodeEntity.getMapperXmlFilePackage().replace(".", "/")+"/"+gencodeEntity.getModuleNameCapi()+"Mapper.xml");  
		FreeMarkerUtil.crateFile(viewMap, "mapper.ftl", gencodeEntity.getMapperFilePath()+"/"+gencodeEntity.getMapperFilePackage().replace(".", "/")+"/"+gencodeEntity.getModuleNameCapi()+"Mapper.java");  
//		
		/**
		 * 生成Mapper.java
		 */
		FreeMarkerUtil.crateFile(viewMap, "entity.ftl", gencodeEntity.getEntityFilePath()+"/"+gencodeEntity.getEntityFilePackage().replace(".", "/")+"/"+gencodeEntity.getModuleNameCapi()+"Entity.java");  
		
		return "";
	}

}
