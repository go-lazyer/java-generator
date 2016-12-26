package com.shadowh.lazy;

import org.junit.Test;

import com.shadowh.lazy.entity.DatabaseEntity;
import com.shadowh.lazy.entity.GenCodeEntity;

public  class GencodeConfig {
	
	public static void main(String[] args) {
		DatabaseEntity dbEntity= new DatabaseEntity();
		dbEntity.setHost("localhost");
		dbEntity.setPort("3306");
		dbEntity.setName("admin");
		dbEntity.setUsername("root");
		dbEntity.setPassword("123");
		GenCodeEntity genCodeEntity = new GenCodeEntity();
		genCodeEntity.setAuthor("hanchanghong");
		genCodeEntity.setTableName("tb_admin");
		genCodeEntity.setModuleName("admin");
		genCodeEntity.setModuleNameCn("admin");
		
		genCodeEntity.setEntityFilePackage("com.shadow.entity");
		genCodeEntity.setEntityFilePath("src/main/java");
		
		genCodeEntity.setMapperFilePackage("com.shadow.mapper");
		genCodeEntity.setMapperFilePath("src/main/java");
		
		genCodeEntity.setMapperXmlFilePackage("mapper");
		genCodeEntity.setMapperXmlFilePath("src/main/resources");
		GenCode gencode = new GenCode();
		try {
			gencode.gencode(dbEntity,genCodeEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void Test(){

		DatabaseEntity dbEntity= new DatabaseEntity();
		dbEntity.setHost("localhost");
		dbEntity.setPort("3306");
		dbEntity.setName("admin");
		dbEntity.setUsername("root");
		dbEntity.setPassword("123");
		GenCodeEntity genCodeEntity = new GenCodeEntity();
		genCodeEntity.setAuthor("hanchanghong");
		genCodeEntity.setTableName("tb_admin");
		genCodeEntity.setModuleName("admin");
		genCodeEntity.setModuleNameCn("admin");
		
		genCodeEntity.setEntityFilePackage("com.shadow.entity");
		genCodeEntity.setEntityFilePath("src/main/java");
		
		genCodeEntity.setMapperFilePackage("com.shadow.mapper");
		genCodeEntity.setMapperFilePath("src/main/java");
		
		genCodeEntity.setMapperXmlFilePackage("mapper");
		genCodeEntity.setMapperXmlFilePath("src/main/resources");
		GenCode gencode = new GenCode();
		try {
			gencode.gencode(dbEntity,genCodeEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
}
