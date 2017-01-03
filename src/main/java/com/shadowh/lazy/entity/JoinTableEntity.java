package com.shadowh.lazy.entity;

import java.util.List;

import com.shadowh.lazy.util.StringUtil;

/**
 * 表实体
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class JoinTableEntity {
	private String tableName;//一对多的表
	private String foreignKey;//外键
	private String type="one-to-many";//对应关系"one-to-many" one-to-one
	private String moduleName;
	private String moduleNameCapi;
	private List<FieldEntity> fields;
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
	

	public List<FieldEntity> getFields() {
		return fields;
	}
	public void setFields(List<FieldEntity> fields) {
		this.fields = fields;
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
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "JoinTableEntity [tableName=" + tableName + ", foreignKey=" + foreignKey + ", moduleName=" + moduleName + ", moduleNameCapi=" + moduleNameCapi + ", fields=" + fields + "]";
	}

}
