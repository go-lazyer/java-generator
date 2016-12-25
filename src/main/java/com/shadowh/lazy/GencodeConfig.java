package com.shadowh.lazy;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

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
		genCodeEntity.setEntityPath("com.hanchanghong.entity");
		
		Gencode gencode = new Gencode();
		try {
			gencode.gencode(dbEntity,genCodeEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
