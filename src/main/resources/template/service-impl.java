package ${serviceImplPackage};

import java.util.List;

import ${entityPackage}.${table.moduleNameCapi}Entity;
import ${mapperPackage}.${table.moduleNameCapi}Mapper;
import ${mapperPackage}.${table.moduleNameCapi}ExtendMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shadowh.piano.service.CourseService;

@Service
public class ${table.moduleNameCapi}ServiceImpl implements ${table.moduleNameCapi}Service{

	@Autowired
	private ${table.moduleNameCapi}Mapper ${table.moduleName}Mapper;
	@Autowired
	private ${table.moduleNameCapi}ExtendMapper ${table.moduleName}ExtendMapper;
	
	/**
	 * 保存${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean save(${table.moduleNameCapi}Entity entity){
		int num = ${table.moduleName}Mapper.save(entity);
		return num>0?true:false;
	}
	
	/**
	 * 选择性的保存${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean saveSelective(${table.moduleNameCapi}Entity entity){
		int num = ${table.moduleName}Mapper.saveSelective(entity);
		return num>0?true:false;
	}
	/**
	 * 按主键删除${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean deleteByPrimaryKey(String primaryKey){
		int num = ${table.moduleName}Mapper.deleteByPrimaryKey(primaryKey);
		return num>0?true:false;
	}
	/**
	 * 按主键修改全部字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean updateByPrimaryKey(${table.moduleNameCapi}Entity entity){
		int num = ${table.moduleName}Mapper.updateByPrimaryKey(entity);
		return num>0?true:false;
	}
	
	/**
	 * 按主键选择性的修改字段
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public boolean updateByPrimaryKeySelective(${table.moduleNameCapi}Entity entity){
		int num = ${table.moduleName}Mapper.updateByPrimaryKeySelective(entity);
		return num>0?true:false;
	}
	
	
	/**
	 * 根据id查询${table.moduleName}
	 * @author ${author}
	 * @date ${updateTime}
	 */
	public ${table.moduleNameCapi}Entity queryByPrimaryKey(String primaryKey){
		return ${table.moduleName}Mapper.queryByPrimaryKey(primaryKey);
	}


}
