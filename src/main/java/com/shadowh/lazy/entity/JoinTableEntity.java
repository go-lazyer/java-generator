package com.shadowh.lazy.entity;

import java.util.List;

/**
 * 表实体
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class JoinTableEntity {
	private String tableName;//一对多的表
	private String foreignKey;//外键
	private List<FieldEntity> fieldList;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}
	
	public List<FieldEntity> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<FieldEntity> fieldList) {
		this.fieldList = fieldList;
	}
	@Override
	public String toString() {
		return "JoinTableEntity [tableName=" + tableName + ", foreignKey=" + foreignKey + ", fieldList=" + fieldList + "]";
	}
	
}
