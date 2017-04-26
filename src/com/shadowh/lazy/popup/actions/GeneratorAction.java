package com.shadowh.lazy.popup.actions;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.joda.time.DateTime;

import com.shadowh.lazy.entity.DataSourceEntity;
import com.shadowh.lazy.entity.FieldEntity;
import com.shadowh.lazy.entity.GlobalEntity;
import com.shadowh.lazy.entity.JoinTableEntity;
import com.shadowh.lazy.entity.TableEntity;
import com.shadowh.lazy.util.FreeMarkerUtil;
import com.shadowh.lazy.util.JdbcUtil;

public class GeneratorAction implements IObjectActionDelegate {
	private static DataSourceEntity dataSource;
	private static GlobalEntity globalEntity;
	private static List<TableEntity> tableList;
	private Shell shell;
	private IFile iFile;
	/**
	 * Constructor for Action1.
	 */
	public GeneratorAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		try {
			if(iFile==null){
				return;
			}
		    File file = iFile.getLocation().toFile();
		    String projectPath = iFile.getProject().getLocation().toString();
		    
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("UTF-8");
			Document document = saxReader.read(new FileInputStream(file));
			Element rootElement = document.getRootElement();
			dataSource=DataSourceEntity.parseXml(rootElement);
			globalEntity=GlobalEntity.parseXml(rootElement);
			tableList=TableEntity.parseXml(rootElement);
			if(StringUtils.isEmpty(dataSource.getDbname())||StringUtils.isEmpty(dataSource.getPassword())||StringUtils.isEmpty(dataSource.getUrl())||StringUtils.isEmpty(dataSource.getUsername())){
				MessageDialog.openInformation(this.shell,"Lazy","Database source error !");
				return;
			}
			JdbcUtil.init(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
			globalEntity.setUpdateTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

			Map<String, Object> viewMap = new HashMap<String, Object>();
			viewMap.put("updateTime", globalEntity.getUpdateTime());
			viewMap.put("author", globalEntity.getAuthor());
			viewMap.put("entityPackage", globalEntity.getEntityFilePackage());
			viewMap.put("mapperPackage", globalEntity.getMapperFilePackage());
			viewMap.put("servicePackage", globalEntity.getServiceFilePackage());
			viewMap.put("serviceImplPackage", globalEntity.getServiceImplFilePackage());
			viewMap.put("controllerPackage", globalEntity.getControllerFilePackage());

			for (TableEntity tableEntity : tableList) {
				List<FieldEntity> fieldList = FieldEntity.queryFieldList(dataSource.getDbname(), tableEntity);
				for (FieldEntity fieldEntity : fieldList) {
					if("1".equals(fieldEntity.getIsPrimaryKey())){
						viewMap.put("primaryKey", fieldEntity);
						break;
					}
				}
				tableEntity.setFields(fieldList);
				
				List<JoinTableEntity> manyTableList = tableEntity.getJoinTables();
				if(manyTableList!=null&&!manyTableList.isEmpty()){
					for (JoinTableEntity manyTableEntity : manyTableList) {
						List<FieldEntity> manyTablefield = FieldEntity.queryFieldList(dataSource.getDbname(), manyTableEntity);
						for (TableEntity t : tableList) {
							if(manyTableEntity.getTableName().equals(t.getTableName())){
								manyTableEntity.setModuleName(t.getModuleName());
								manyTableEntity.setModuleNameCapi(t.getModuleNameCapi());
							}
						}
						manyTableEntity.setFields(manyTablefield);
					}
				}
				viewMap.put("table", tableEntity);
				if(StringUtils.isNotEmpty(globalEntity.getEntityFilePackage())){
					FreeMarkerUtil.crateFile(viewMap, "entity.ftl",projectPath+"/"+globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Entity.java",true);
					FreeMarkerUtil.crateFile(viewMap, "entity-view.ftl",projectPath+"/"+ globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "View.java",false);
					FreeMarkerUtil.crateFile(viewMap, "entity-param.ftl",projectPath+"/"+ globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Param.java",false);
					FreeMarkerUtil.crateFile(viewMap, "entity-example.ftl",projectPath+"/"+globalEntity.getEntityFilePath() + "/" + globalEntity.getEntityFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Example.java",true);
				}
				if(StringUtils.isNotEmpty(globalEntity.getMapperXmlFilePackage())){
					FreeMarkerUtil.crateFile(viewMap, "mapper-xml.ftl", projectPath+"/"+globalEntity.getMapperXmlFilePath() + "/" + globalEntity.getMapperXmlFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Mapper.xml",true);
					FreeMarkerUtil.crateFile(viewMap, "mapper-extend-xml.ftl", projectPath+"/"+globalEntity.getMapperXmlFilePath() + "/" + globalEntity.getMapperXmlFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "ExtendMapper.xml",false);
				}
				if(StringUtils.isNotEmpty(globalEntity.getMapperFilePackage())){
					FreeMarkerUtil.crateFile(viewMap, "mapper-java.ftl", projectPath+"/"+globalEntity.getMapperFilePath() + "/" + globalEntity.getMapperFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Mapper.java",true);
					FreeMarkerUtil.crateFile(viewMap, "mapper-extend-java.ftl", projectPath+"/"+globalEntity.getMapperFilePath() + "/" + globalEntity.getMapperFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "ExtendMapper.java",false);
				}
				if(StringUtils.isNotEmpty(globalEntity.getServiceFilePackage())){
					FreeMarkerUtil.crateFile(viewMap, "service.ftl", projectPath+"/"+globalEntity.getServiceFilePath() + "/" + globalEntity.getServiceFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Service.java",false);
					FreeMarkerUtil.crateFile(viewMap, "service-impl.ftl", projectPath+"/"+globalEntity.getServiceImplFilePath() + "/" + globalEntity.getServiceImplFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "ServiceImpl.java",false);
				}
				if(StringUtils.isNotEmpty(globalEntity.getControllerFilePackage())){
					FreeMarkerUtil.crateFile(viewMap, "controller.ftl", projectPath+"/"+globalEntity.getControllerFilePath() + "/" + globalEntity.getControllerFilePackage().replace(".", "/") + "/" + tableEntity.getModuleNameCapi() + "Controller.java",false);
				}
			}
			iFile.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
			MessageDialog.openInformation(this.shell,"Lazy","Generator success !");
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		iFile=(IFile)((IStructuredSelection)selection).getFirstElement();
	}
}
