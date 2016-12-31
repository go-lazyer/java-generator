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

	public ${moduleNameCapi}Entity() {
		super();
	}
	
	
	public ${moduleNameCapi}Entity(Boolean defaultValue) {
		if(defaultValue){
		<#list attrList as key> 
			<#if key.attrType = "Integer"> 
			this.${key.attribute} = 0; 
			<#elseif  key.fieldType = "datetime"> 
			this.${key.attribute} = "0000-00-00 00:00:00";
			<#elseif  key.fieldType = "date"> 
			this.${key.attribute} = "0000-00-00";
           <#elseif  key.fieldType = "timestamp"> 
			this.${key.attribute} = "0000-00-00 00:00:00";
		   <#elseif  key.fieldType = "enum"> 
			this.${key.attribute} = "0";
		   <#elseif  key.attrType = "String"> 
			this.${key.attribute} = "";
		   </#if>
		</#list>
		}
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

