package ${entityPackage};
import java.util.List;
/**
 * ${table.moduleName}实体类
 * @author ${author}
 * @date ${updateTime}
 */
public class ${table.moduleNameCapi}Entity{
    <#list table.fields as key> 
	private ${key.attrType} ${key.attribute}; //${key.comment}
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

	public void initDefaultValue() {
	<#list table.fields as key> 
		if(${key.attribute}==null){
			<#if key.attrType = "Integer"> 
			${key.attribute} = 0; 
			<#elseif  key.fieldType = "datetime"> 
			${key.attribute} = "0000-00-00 00:00:00";
			<#elseif  key.fieldType = "date"> 
			${key.attribute} = "0000-00-00";
			<#elseif  key.fieldType = "timestamp"> 
			${key.attribute} = "0000-00-00 00:00:00";
			<#elseif  key.fieldType = "enum"> 
			${key.attribute} = "0";
			<#elseif  key.attrType = "String"> 
			${key.attribute} = "";
			</#if>
		}
	</#list>
	}
    
    <#list table.fields as key> 
	public ${key.attrType} get${key.attribute?cap_first}() {
		return ${key.attribute};
	}
	public void set${key.attribute?cap_first}(${key.attrType} ${key.attribute}) {
		this.${key.attribute} = ${key.attribute};
	}
	</#list>
	
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
	@Override
	public String toString() {
		return "${table.moduleName} ["<#list table.fields as key>+"${key.attribute}=" + ${key.attribute} + ","  </#list><#if table.joinTables??><#list table.joinTables as key><#if key.type=="one-to-one"> +"${key.moduleName}Entity=" + ${key.moduleName}Entity + ","<#elseif key.type=="one-to-many">+"${key.moduleName}List=" + ${key.moduleName}List + ","</#if></#list></#if> + "]";
	}

}

