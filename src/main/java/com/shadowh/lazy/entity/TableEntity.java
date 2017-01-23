package com.shadowh.lazy.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;

import com.shadowh.lazy.util.StringUtil;

/**
 * 表实体
 * @author hanchanghong
 * @date 2016年9月18日 上午10:05:13
 */
public class TableEntity {
	private String tableName;
	private String moduleName;
	private String moduleNameCapi;
	private List<FieldEntity> fields;
	private List<JoinTableEntity> joinTables;
	private Map<String,String> extendFields;
	private List<String> ignoreColumns;
	private List<String> onlyColumns;
	private List<Map<String,String>> overrideColumns;

	public static List<TableEntity> parseXml(Element rootElement){
		/**
		 * 获取table 先通过属性获取，再通过子元素获取
		 */
		List<Element> tablesElements = rootElement.elements("table");
		List<TableEntity> tableList = new ArrayList<TableEntity>();
		if (tablesElements != null) {
			for (Element tableElement : tablesElements) {
				TableEntity tableEntity = new TableEntity();
				List<Attribute> tableAttributes = tableElement.attributes();
				for (Attribute attribute : tableAttributes) {
					switch (attribute.getName()) {
					case "table-name":
						tableEntity.setTableName(attribute.getValue());
						break;
					case "module-name":
						tableEntity.setModuleName(attribute.getValue());
						break;
					default:
						break;
					}
				}

				List<Element> tableElements = tableElement.elements("property");
				for (Element element : tableElements) {
					Attribute nameAttribute = element.attribute("name");
					Attribute valueAttribute = element.attribute("value");
					switch (nameAttribute.getValue()) {
					case "table-name":
						tableEntity.setTableName(valueAttribute.getValue());
						break;
					case "module-name":
						tableEntity.setModuleName(valueAttribute.getValue());
						break;
					default:
						break;
					}
				}
				Element joinTableElement = tableElement.element("join-table");
				if(joinTableElement!=null){
					List<JoinTableEntity> joinTableList= new ArrayList<JoinTableEntity>();
					List<Element> joinTableElements = joinTableElement.elements("property");
					for (Element element : joinTableElements) {
						JoinTableEntity manyTableEntity= new JoinTableEntity();
						List<Attribute> attributes = element.attributes();
						for (Attribute attribute : attributes) {
							switch (attribute.getName()) {
							case "table-name":
								manyTableEntity.setTableName(attribute.getValue());
								break;
							case "foreign-key":
								manyTableEntity.setForeignKey(attribute.getValue());
								break;
							case "type":
								manyTableEntity.setType(attribute.getValue());
								break;
							default:
								break;
							}
						}
						
						joinTableList.add(manyTableEntity);
					}
					tableEntity.setJoinTables(joinTableList);
				}
				
				/**
				 * 扩展的属性
				 */
				Element extendFieldsElement = tableElement.element("extend-fields");
				if(extendFieldsElement!=null){
					Map<String,String> extendFieldsMap=new HashMap<String,String>();
					List<Element> propertyElements = extendFieldsElement.elements("property");
					for (Element element : propertyElements) {
						Attribute fieldNameAttribute = element.attribute("field-name");
						Attribute fieldTypeAttribute = element.attribute("field-type");
						extendFieldsMap.put(fieldNameAttribute.getValue(), fieldTypeAttribute.getValue());
					}
					tableEntity.setExtendFields(extendFieldsMap);
				}
				/**
				 * 忽略的字段
				 */
				Element ignoreColumnsElement = tableElement.element("ignore-columns");
				if(ignoreColumnsElement!=null){
					List<String> ignoreColumns=new ArrayList<String>();
					List<Element> propertyElements = ignoreColumnsElement.elements("property");
					for (Element element : propertyElements) {
						Attribute columnNameAttribute = element.attribute("column-name");
						ignoreColumns.add(columnNameAttribute.getValue());
					}
					tableEntity.setIgnoreColumns(ignoreColumns);
				}
				/**
				 * 保留的字段
				 *  ignore-columns和only-columns 只能选其一 两个都配置以ignore-columns为准
				 */
				if(tableEntity.getIgnoreColumns()==null||tableEntity.getIgnoreColumns().isEmpty()){
					Element onlyColumnsElement = tableElement.element("only-columns");
					if(onlyColumnsElement!=null){
						List<String> onlyColumns=new ArrayList<String>();
						List<Element> propertyElements = onlyColumnsElement.elements("property");
						for (Element element : propertyElements) {
							Attribute fieldNameAttribute = element.attribute("column-name");
							onlyColumns.add(fieldNameAttribute.getValue());
						}
						tableEntity.setOnlyColumns(onlyColumns);
					}
				}
				
				
				/**
				 * 重写的字段
				 */
				Element overrideColumnsElement = tableElement.element("override-columns");
				if(overrideColumnsElement!=null){
					List<Map<String,String>> overrideColumns=new ArrayList<Map<String,String>>();
					List<Element> propertyElements = overrideColumnsElement.elements("property");
					for (Element element : propertyElements) {
						Map<String,String> overrideColumnMap= new HashMap<String,String>();
						List<Attribute> attributes = element.attributes();
						for (Attribute attribute : attributes) {
							overrideColumnMap.put(attribute.getName(),attribute.getValue());
						}
						overrideColumns.add(overrideColumnMap);
					}
					tableEntity.setOverrideColumns(overrideColumns);
				}
				
				tableList.add(tableEntity);
			}
			System.out.println(tableList);
		}

		return tableList;
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
		if(StringUtils.isNotEmpty(moduleName)){
			this.moduleNameCapi=StringUtil.firstUpperCase(moduleName);
		}
	}


	public String getModuleNameCapi() {
		return moduleNameCapi;
	}

	public void setModuleNameCapi(String moduleNameCapi) {
		this.moduleNameCapi = moduleNameCapi;
	}

	public List<FieldEntity> getFields() {
		return fields;
	}

	public void setFields(List<FieldEntity> fields) {
		this.fields = fields;
	}

	public List<JoinTableEntity> getJoinTables() {
		return joinTables;
	}

	public void setJoinTables(List<JoinTableEntity> joinTables) {
		this.joinTables = joinTables;
	}

	public Map<String, String> getExtendFields() {
		return extendFields;
	}

	public void setExtendFields(Map<String, String> extendFields) {
		this.extendFields = extendFields;
	}

	public List<String> getIgnoreColumns() {
		return ignoreColumns;
	}

	public void setIgnoreColumns(List<String> ignoreColumns) {
		this.ignoreColumns = ignoreColumns;
	}

	public List<String> getOnlyColumns() {
		return onlyColumns;
	}

	public void setOnlyColumns(List<String> onlyColumns) {
		this.onlyColumns = onlyColumns;
	}

	public List<Map<String, String>> getOverrideColumns() {
		return overrideColumns;
	}

	public void setOverrideColumns(List<Map<String, String>> overrideColumns) {
		this.overrideColumns = overrideColumns;
	}

	@Override
	public String toString() {
		return "TableEntity [tableName=" + tableName + ", moduleName=" + moduleName + ", moduleNameCapi=" + moduleNameCapi + ", fields=" + fields + ", joinTables=" + joinTables + ", extendFields=" + extendFields + ", ignoreColumns=" + ignoreColumns + ", onlyColumns=" + onlyColumns + ", overrideColumns=" + overrideColumns + "]";
	}

}
