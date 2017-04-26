package ${entityPackage};
import java.util.List;
/**
 * ${table.moduleName}视图类
 * @author ${author}
 * @date ${updateTime}
 */
public class ${table.moduleNameCapi}View{
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

}

