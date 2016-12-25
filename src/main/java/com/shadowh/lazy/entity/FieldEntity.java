package com.shadowh.lazy.entity;

/**
 * 表实体
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class FieldEntity {
	private String field;
	private String fieldType;
	private String attribute;
	private String attrType;
	private String comment;
	private String isPrimaryKey="0";
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
