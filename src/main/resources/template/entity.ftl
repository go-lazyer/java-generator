package ${entityPackage};

import java.util.LinkedHashMap;

/**
 * ${moduleNameCn}实体类
 * @author ${author}
 * @date ${updateTime}
 */
public class ${moduleNameCapi}Entity{
    <#list attrList as key> 
	private ${key.attrType} ${key.attribute}; //${key.comment}
    </#list>
    private String columnStr = "*";//需要查询的字段默认为所有
    
    private String condition; //where 条件
    
    private LinkedHashMap <String,String> orderBy;//排序字段
    
    Integer start;
    
    Integer size;
    
    <#list attrList as key> 
	public ${key.attrType} get${key.attribute?cap_first}() {
		return ${key.attribute};
	}
	public void set${key.attribute?cap_first}(${key.attrType} ${key.attribute}) {
		this.${key.attribute} = ${key.attribute};
	}
	</#list>
	public String getColumnStr() {
		return columnStr;
	}
	public void setColumnStr(String columnStr) {
		this.columnStr = columnStr;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public LinkedHashMap <String,String> getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(LinkedHashMap <String,String> orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}

