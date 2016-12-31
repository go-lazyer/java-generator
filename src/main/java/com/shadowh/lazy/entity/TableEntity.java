package com.shadowh.lazy.entity;

import java.sql.SQLException;
import java.util.List;

import com.shadowh.lazy.util.JdbcUtil;
import com.shadowh.lazy.util.StringUtil;

/**
 * 表实体
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class TableEntity {
	private String id;
	private String tableName;
	private String moduleName;
	private String moduleNameCapi;
	private List<FieldEntity> fieldList;
	private List<JoinTableEntity> joinTableList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
		if(StringUtil.isNotEmpty(moduleName)){
			this.moduleNameCapi=StringUtil.firstUpperCase(moduleName);
		}
	}


	public String getModuleNameCapi() {
		return moduleNameCapi;
	}

	public void setModuleNameCapi(String moduleNameCapi) {
		this.moduleNameCapi = moduleNameCapi;
	}

	public List<FieldEntity> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FieldEntity> fieldList) {
		this.fieldList = fieldList;
	}

	public List<JoinTableEntity> getJoinTableList() {
		return joinTableList;
	}

	public void setJoinTableList(List<JoinTableEntity> joinTableList) {
		this.joinTableList = joinTableList;
	}

	
}
