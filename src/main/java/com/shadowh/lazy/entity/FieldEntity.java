package com.shadowh.lazy.entity;

import java.sql.SQLException;
import java.util.List;

import com.shadowh.lazy.util.JdbcUtil;
import com.shadowh.lazy.util.StringUtil;

/**
 * 字段实体
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class FieldEntity {
	private String field;
	private String fieldType;
	private String attribute;
	private String attrType;
	private String extra;//是否自增
	private String comment;
	private String isPrimaryKey="0";
	private String isNullable;
	private String defaultValue;
	
	/**
	 * @param paraMap
	 * @return
	 * @author hanchanghong
	 * @date 2016年5月2日 下午9:28:20
	 */
	public static  List<FieldEntity> queryFieldList(DataSourceEntity dbsEntity, String tableName) {
		
		JdbcUtil jdbcUtil=new JdbcUtil(dbsEntity);
		String sql="select column_name field,data_type fieldType,column_comment comment,column_key isPrimaryKey, "
				+ " is_nullable isNullable  ,column_default defaultValue,extra"
				+ " from information_schema.COLUMNS "
				+ " where table_name = '"+tableName+"' and table_schema = '"+dbsEntity.getDbname()+"'";
		List<FieldEntity> fieldList = null;
		try {
			fieldList = jdbcUtil.query(FieldEntity.class,sql);
			for (FieldEntity field : fieldList) {
				field.setAttribute( StringUtil.underlineToCamel(field.getField()));
				if("PRI".equals(field.getIsPrimaryKey())){
					field.setIsPrimaryKey("1");
				}
				if(field.getFieldType().indexOf("int")!=-1||"decimal".equals(field.getFieldType())){
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
	
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	@Override
	public String toString() {
		return "FieldEntity [field=" + field + ", fieldType=" + fieldType + ", attribute=" + attribute + ", attrType=" + attrType + ", extra=" + extra + ", comment=" + comment + ", isPrimaryKey=" + isPrimaryKey + ", isNullable=" + isNullable + ", defaultValue=" + defaultValue + "]";
	}

	
}
