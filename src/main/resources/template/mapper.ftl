package ${mapperPackage};

import java.util.List;

import ${entityPackage}.${table.moduleNameCapi}Entity;
import ${entityPackage}.${table.moduleNameCapi}Example;
import org.apache.ibatis.annotations.Param;

public interface ${table.moduleNameCapi}Mapper {

	/**
	 * 保存${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int save(${table.moduleNameCapi}Entity entity);
	
	/**
	 * 选择性的保存${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int saveSelective(${table.moduleNameCapi}Entity entity);
	/**
	 * 按主键删除${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int deleteByPrimaryKey(String primaryKey);
	/**
	 * 按条件删除${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int deleteByExample(${table.moduleNameCapi}Example example);	
	
	/**
	 * 按主键修改全部字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByPrimaryKey(${table.moduleNameCapi}Entity entity);
	
	/**
	 * 按主键选择性的修改字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByPrimaryKeySelective(${table.moduleNameCapi}Entity entity);
	
	/**
	 * 按条件修改全部字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByExample(@Param("entity") ${table.moduleNameCapi}Entity entity,@Param("example") ${table.moduleNameCapi}Example example);
	
	/**
	 * 按条件选择性的修改字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByExampleSelective(@Param("entity") ${table.moduleNameCapi}Entity entity,@Param("example") ${table.moduleNameCapi}Example example);
	
	
	
	/**
	 * 按条件统计个数
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public Integer countByExample(${table.moduleNameCapi}Example example);
	
	
	/**
	 * 根据id查询${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public ${table.moduleNameCapi}Entity queryByPrimaryKey(String primaryKey);

	/**
	 * 按条件查询${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public List<${table.moduleNameCapi}Entity> queryByExample(${table.moduleNameCapi}Example example);
	
}
