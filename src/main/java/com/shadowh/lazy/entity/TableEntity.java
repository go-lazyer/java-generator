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
	private String field;
	private String fieldType;
	private String attribute;
	private String attrType;
	private String comment;
	private String isPrimaryKey="0";
	
	/**
	 * @param paraMap
	 * @return
	 * @author hanchanghong
	 * @date 2016年5月2日 下午9:28:20
	 */
	public List<TableEntity> queryFieldList(DatabaseEntity dbsEntity, String tableName) {
		
		JdbcUtil jdbcUtil=new JdbcUtil(dbsEntity);
		String sql="select column_name field,data_type fieldType,column_comment comment,column_key isPrimaryKey  from information_schema.COLUMNS where table_name = '"+tableName+"' and table_schema = '"+dbsEntity.getName()+"'";
		List<TableEntity> fieldList = null;
		try {
			fieldList = jdbcUtil.query(TableEntity.class,sql);
			for (TableEntity field : fieldList) {
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
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getIsPrimaryKey() {
		return isPrimaryKey;
	}
	public void setIsPrimaryKey(String isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	@Override
	public String toString() {
		return "FieldEntity [field=" + field + ", fieldType=" + fieldType + ", attribute=" + attribute + ", attrType="
				+ attrType + ", comment=" + comment + ", isPrimaryKey=" + isPrimaryKey + "]";
	}
	
}
