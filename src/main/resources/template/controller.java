package ${controllerPackage};

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ${servicePackage}.${table.moduleNameCapi}Service;

@Controller
public class ${table.moduleNameCapi}Controller {
	@Resource
	private ${table.moduleNameCapi}Service ${table.moduleName}Service;
	
	@RequestMapping(value = "/index")
	public void index(HttpServletRequest request, HttpServletResponse response) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
