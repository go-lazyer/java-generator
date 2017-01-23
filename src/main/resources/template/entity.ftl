package ${entityPackage};
import java.util.List;
/**
 * ${table.moduleName}实体类
 * @author ${author}
 * @date ${updateTime}
 */
public class ${table.moduleNameCapi}Entity{
    <#list table.fields as key> 
	private ${key.fieldType} ${key.field}; //${key.comment}
    </#list>
    <#if table.joinTables??>
    <#list table.joinTables as key>
    <#if key.type=="one-to-one">
    private ${key.moduleNameCapi}Entity ${key.moduleName}Entity;
    <#elseif key.type=="one-to-many">
    private List<${key.moduleNameCapi}Entity> ${key.moduleName}List;
    </#if>
    </#list>
    </#if>
    <#if table.extendFields??>
    <#list table.extendFields?keys as key> 
	private ${table.extendFields[key]} ${key};
    </#list>
    </#if>
    
    private String updateColumns = null;//自定义更新
    
    
	public void initDefaultValue() {
	<#list table.fields as key> 
		if(${key.field}==null){
			<#if key.fieldType = "Integer"> 
			${key.field} = 0; 
			<#elseif  key.columnType = "datetime"> 
			${key.field} = "0000-00-00 00:00:00";
			<#elseif  key.columnType = "date"> 
			${key.field} = "0000-00-00";
			<#elseif  key.columnType = "timestamp"> 
			${key.field} = "0000-00-00 00:00:00";
			<#elseif  key.columnType = "enum"> 
			${key.field} = "0";
			<#elseif  key.fieldType = "String"> 
			${key.field} = "";
			</#if>
		}
	</#list>
	}
    
    <#list table.fields as key> 
	public ${key.fieldType} get${key.field?cap_first}() {
		return ${key.field};
	}
	public void set${key.field?cap_first}(${key.fieldType} ${key.field}) {
		this.${key.field} = ${key.field};
	}
	</#list>
	
	<#if table.extendFields??>
    <#list table.extendFields?keys as key> 
	public ${table.extendFields[key]} get${key?cap_first}() {
		return ${key};
	}
	public void set${key?cap_first}(${table.extendFields[key]} ${key}) {
		this.${key} = ${key};
	}
    </#list>
    </#if>
	
    <#if table.joinTables??>
    <#list table.joinTables as key>
    <#if key.type=="one-to-one">
	public ${key.moduleNameCapi}Entity get${key.moduleNameCapi}Entity() {
		return ${key.moduleName}Entity;
	}
	public void set${key.moduleNameCapi}Entity(${key.moduleNameCapi}Entity ${key.moduleName}Entity) {
		this.${key.moduleName}Entity = ${key.moduleName}Entity;
	}    
    <#elseif key.type=="one-to-many">
	public List<${key.moduleNameCapi}Entity> get${key.moduleNameCapi}List() {
		return ${key.moduleName}List;
	}
	public void set${key.moduleNameCapi}List(List<${key.moduleNameCapi}Entity> ${key.moduleName}List) {
		this.${key.moduleName}List = ${key.moduleName}List;
	}
    </#if>
    </#list>
    </#if>
    
	
	public String getUpdateColumns() {
		return updateColumns;
	}

	public void setUpdateColumns(String updateColumns) {
		this.updateColumns = updateColumns;
	}
    
    
	@Override
	public String toString() {
		return "${table.moduleName} ["<#list table.fields as key>+"${key.field}=" + ${key.field} + ","  </#list><#if table.joinTables??><#list table.joinTables as key><#if key.type=="one-to-one"> +"${key.moduleName}Entity=" + ${key.moduleName}Entity + ","<#elseif key.type=="one-to-many">+"${key.moduleName}List=" + ${key.moduleName}List + ","</#if></#list></#if> + "]";
	}

}

