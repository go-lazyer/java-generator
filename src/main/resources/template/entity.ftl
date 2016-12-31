package ${entityPackage};

/**
 * ${moduleName}实体类
 * @author ${author}
 * @date ${updateTime}
 */
public class ${moduleNameCapi}Entity{
    <#list attrList as key> 
	private ${key.attrType} ${key.attribute}; //${key.comment}
    </#list>

	public void initDefaultValue() {
	<#list attrList as key> 
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
    
    <#list attrList as key> 
	public ${key.attrType} get${key.attribute?cap_first}() {
		return ${key.attribute};
	}
	public void set${key.attribute?cap_first}(${key.attrType} ${key.attribute}) {
		this.${key.attribute} = ${key.attribute};
	}
	</#list>
}

