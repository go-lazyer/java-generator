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
    
    public static ${table.moduleNameCapi}View convert(${table.moduleNameCapi}Entity ${table.moduleName}Entity){
		${table.moduleNameCapi}View ${table.moduleName}View= new ${table.moduleNameCapi}View();
	<#list table.fields as key> 
		${table.moduleName}View.set${key.field?cap_first}(${table.moduleName}Entity.get${key.field?cap_first}());
    </#list>
		return ${table.moduleName}View;
	}
    
    <#list table.fields as key> 
	public ${key.fieldType} get${key.field?cap_first}() {
		return ${key.field};
	}
	public void set${key.field?cap_first}(${key.fieldType} ${key.field}) {
		this.${key.field} = ${key.field};
	}
	</#list>
	

}

