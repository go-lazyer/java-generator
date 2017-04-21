package ${mapperPackage};

import java.util.List;

import ${entityPackage}.${table.moduleNameCapi}Entity;
import ${entityPackage}.${table.moduleNameCapi}Example;
<#if table.joinTables??>
 <#list table.joinTables as keys>
import ${entityPackage}.${keys.moduleNameCapi}Example;
 </#list>
</#if>
import org.apache.ibatis.annotations.Param;

public interface ${table.moduleNameCapi}ExtendMapper {

}
