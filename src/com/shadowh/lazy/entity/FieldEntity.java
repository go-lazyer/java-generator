package com.shadowh.lazy.entity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.shadowh.lazy.util.JdbcUtil;
import com.shadowh.lazy.util.StringUtil;

/**
 * 字段实体
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class FieldEntity {
	private String column;//表字段
	private String columnDelimit; //如果是mysql关键字加分隔符
	private String columnType;
	private String field;//实体属性
	private String fieldType;
	private String extra;//是否自增
	private String comment;//注释
	private String isPrimaryKey="0";
	private String isNullable;//是否可为空
	private String defaultValue;//默认值
	
	/**
	 * @param paraMap
	 * @return
	 * @author hanchanghong
	 * @throws Exception 
	 * @date 2016年5月2日 下午9:28:20
	 */
	public static  List<FieldEntity> queryFieldList(String dbname, TableEntity tableEntity) throws Exception {
		String sql="select column_name 'column',column_name columnDelimit,data_type columnType,column_comment comment,column_key isPrimaryKey, "
				+ " is_nullable isNullable  ,column_default defaultValue,extra"
				+ " from information_schema.COLUMNS "
				+ " where table_name = '"+tableEntity.getTableName()+"' and table_schema = '"+dbname+"'";
		List<FieldEntity> fieldList = null;
			String mysqlKeywords=IOUtils.toString(FieldEntity.class.getResourceAsStream("/" + "mysql-keywords.txt"));
			String javaKeywords=IOUtils.toString(FieldEntity.class.getResourceAsStream("/" + "java-keywords.txt"));
			fieldList = JdbcUtil.query(sql);
			List<FieldEntity> tempFieldList= new ArrayList<FieldEntity>();
			for (FieldEntity field : fieldList) {
				field.setAttribute( StringUtil.underlineToCamel(field.getColumn()));
				if("PRI".equals(field.getIsPrimaryKey())){
					field.setIsPrimaryKey("1");
				}
				if("int".equals(field.getColumnType())||"tinyint".equals(field.getColumnType())){
					field.setFieldType("Integer");
				}else if ("bigint".equals(field.getColumnType())){
					field.setFieldType("Long");
				}else if ("float".equals(field.getColumnType())){
					field.setFieldType("Float");
				}else if ("double".equals(field.getColumnType())||"decimal".equals(field.getColumnType())){
					field.setFieldType("Double");
				}else{
					field.setFieldType("String");
				}
				
				/**
				 * 强制重写mysql关键字
				 */
				if(StringUtil.isNotEmpty(mysqlKeywords)&&mysqlKeywords.indexOf(","+field.getColumn()+",")!=-1){
					field.setColumnDelimit("`"+field.getColumn()+"`");
				}
				/**
				 * 强制重写java关键字
				 */
				if(StringUtil.isNotEmpty(javaKeywords)&&javaKeywords.indexOf(","+field.getAttribute()+",")!=-1){
					field.setAttribute(field.getAttribute()+"As");
				}
				
				/**
				 * 重写实体属性
				 */
				if(tableEntity.getOverrideColumns()!=null&&!tableEntity.getOverrideColumns().isEmpty()){
					for (Map<String,String> map:tableEntity.getOverrideColumns()) {
						if(field.getColumn().equals(map.get("column-name"))){
							if(StringUtil.isNotEmpty(map.get("field-name"))){
								field.setField(map.get("field-name"));
							}
							if(StringUtil.isNotEmpty(map.get("field-type"))){
								field.setFieldType(map.get("field-type"));
							}
						}
					}
				}
				if(tableEntity.getIgnoreColumns()!=null&&!tableEntity.getIgnoreColumns().isEmpty()){
					if(tableEntity.getIgnoreColumns().contains(field.getColumn())){
						tempFieldList.add(field);
					}
				}else{
					if(tableEntity.getOnlyColumns()!=null&&!tableEntity.getOnlyColumns().isEmpty()){
						if(!tableEntity.getOnlyColumns().contains(field.getColumn())){
							tempFieldList.add(field);
						}
					}
				}
			}
			fieldList.removeAll(tempFieldList);
		return fieldList;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getColumnDelimit() {
		return columnDelimit;
	}

	public void setColumnDelimit(String columnDelimit) {
		this.columnDelimit = columnDelimit;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getAttribute() {
		return field;
	}
	public void setAttribute(String attribute) {
		this.field = attribute;
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

	@Override
	public String toString() {
		return "FieldEntity [column=" + column + ", columnDelimit=" + columnDelimit + ", columnType=" + columnType + ", field=" + field + ", fieldType=" + fieldType + ", extra=" + extra + ", comment=" + comment + ", isPrimaryKey=" + isPrimaryKey + ", isNullable=" + isNullable + ", defaultValue=" + defaultValue + "]";
	}
	
}
