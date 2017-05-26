package ${entityPackage};
import java.util.List;
/**
 * ${table.moduleName}参数类
 * @author ${author}
 * @date ${updateTime}
 */
public class ${table.moduleNameCapi}Param{
    <#list table.fields as key> 
	private ${key.fieldType} ${key.field}; //${key.comment}
    </#list>
    
    <#list table.fields as key> 
	public ${key.fieldType} get${key.field?cap_first}() {
		return ${key.field};
	}
	public void set${key.field?cap_first}(${key.fieldType} ${key.field}) {
		this.${key.field} = ${key.field};
	}
	</#list>
	
}

