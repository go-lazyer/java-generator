package ${servicePackage};

import java.util.List;

import ${entityPackage}.${table.moduleNameCapi}Entity;
import ${entityPackage}.${table.moduleNameCapi}Example;
<#if table.joinTables??>
 <#list table.joinTables as keys>
import ${entityPackage}.${keys.moduleNameCapi}Example;
 </#list>
</#if>
import org.apache.ibatis.annotations.Param;

public interface ${table.moduleNameCapi}Service {

	/**
	 * 保存${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean save(${table.moduleNameCapi}Entity entity);
	
	/**
	 * 选择性的保存${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean saveSelective(${table.moduleNameCapi}Entity entity);
	/**
	 * 按主键删除${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean deleteByPrimaryKey(String primaryKey);
	/**
	 * 按主键修改全部字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean updateByPrimaryKey(${table.moduleNameCapi}Entity entity);
	
	/**
	 * 按主键选择性的修改字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean updateByPrimaryKeySelective(${table.moduleNameCapi}Entity entity);
	
	
	/**
	 * 根据id查询${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public ${table.moduleNameCapi}Entity queryByPrimaryKey(String primaryKey);


}
