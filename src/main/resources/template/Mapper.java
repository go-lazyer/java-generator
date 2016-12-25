package ${packagePath}.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ${packagePath}.entity.${moduleNameCapi}Entity;

public interface ${moduleNameCapi}Mapper {

	/**
	 * 保存${moduleNameCn}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int save(${moduleNameCapi}Entity ${moduleName}Entity);
	
	/**
	 * 选择性的保存${moduleNameCn}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int saveSelective(${moduleNameCapi}Entity ${moduleName}Entity);
	/**
	 * 按主键删除${moduleNameCn}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int deleteByPrimaryKey(String id);
	/**
	 * 按条件删除${moduleNameCn}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int deleteByExample(${moduleNameCapi}Entity ${moduleName}Entity);	
	
	/**
	 * 按主键修改全部字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByPrimaryKey(${moduleNameCapi}Entity ${moduleName}Entity);
	/**
	 * 按主键选择性的修改字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByPrimaryKeySelective(${moduleNameCapi}Entity ${moduleName}Entity);
	
	
	
	/**
	 * 按条件修改全部字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByExample(${moduleNameCapi}Entity ${moduleName}Entity);
	
	/**
	 * 按条件选择性的修改字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public int updateByExampleSelective(${moduleNameCapi}Entity ${moduleName}Entity);
	
	
	
	/**
	 * 按条件统计个数
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public ${moduleNameCapi}Entity countByExample(${moduleNameCapi}Entity ${moduleName}Entity);
	
	
	/**
	 * 根据id查询${moduleNameCn}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public ${moduleNameCapi}Entity queryByPrimaryKey(String id);

	/**
	 * 按条件查询${moduleNameCn}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public List<${moduleNameCapi}Entity> queryByExample(${moduleNameCapi}Entity ${moduleName}Entity);

	
	
}
