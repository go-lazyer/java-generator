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
public class JoinTableEntity {
	private String tableId;//关联的表id
	private String foreignKey;//外键
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}
	@Override
	public String toString() {
		return "JoinTableEntity [tableId=" + tableId + ", foreignKey=" + foreignKey + "]";
	}
	
	
}
