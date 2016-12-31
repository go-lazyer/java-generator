package ${mapperPackage};

import java.util.List;

import ${entityPackage}.${moduleNameCapi}Entity;
import ${entityPackage}.${moduleNameCapi}Example;
import org.apache.ibatis.annotations.Param;

public interface ${moduleNameCapi}Mapper {

	/**
	 * 保存${moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int save(${moduleNameCapi}Entity entity);
	
	/**
	 * 选择性的保存${moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int saveSelective(${moduleNameCapi}Entity entity);
	/**
	 * 按主键删除${moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int deleteByPrimaryKey(String primaryKey);
	/**
	 * 按条件删除${moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int deleteByExample(${moduleNameCapi}Example example);	
	
	/**
	 * 按主键修改全部字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByPrimaryKey(${moduleNameCapi}Entity entity);
	
	/**
	 * 按主键选择性的修改字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByPrimaryKeySelective(${moduleNameCapi}Entity entity);
	
	/**
	 * 按条件修改全部字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByExample(@Param("entity") ${moduleNameCapi}Entity entity,@Param("example") ${moduleNameCapi}Example example);
	
	/**
	 * 按条件选择性的修改字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByExampleSelective(@Param("entity") ${moduleNameCapi}Entity entity,@Param("example") ${moduleNameCapi}Example example);
	
	
	
	/**
	 * 按条件统计个数
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public Integer countByExample(${moduleNameCapi}Example example);
	
	
	/**
	 * 根据id查询${moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public ${moduleNameCapi}Entity queryByPrimaryKey(String primaryKey);

	/**
	 * 按条件查询${moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public List<${moduleNameCapi}Entity> queryByExample(${moduleNameCapi}Example example);
	
}
