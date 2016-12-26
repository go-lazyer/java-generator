package com.shadowh.lazy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

/**
 * 访问FreeMarker的工具类
 * 
 * @author xiangyang.li
 * 
 */
public class FreeMarkerUtil {

	private static final Logger logger = Logger.getLogger(FreeMarkerUtil.class);

	/** 是否已初始化 */
	private static boolean isInit = false;

	/** 模版所在路径 */
	private static String templatePath = null;

	/** 编码格式 UTF-8 */
	private static final String ENCODING = "UTF-8";

	/** FreeMarker配置 */
	private static Configuration config = new Configuration(new Version("2.3.23"));

	/** 路径分割符 */
	public static final String PATH_SEPARATOR = "/";

	/**
	 * 初始化FreeMarker配置
	 * @param applicationPath 应用所在路径
	 */
	public static void initFreeMarker() {
		if (!(isInit)) {
			try {
				// 加载模版
				config.setClassForTemplateLoading(FreeMarkerUtil.class,"/template");
				// 设置文件编码为UTF-8
				config.setEncoding(Locale.getDefault(), ENCODING);
				isInit = true;
			} catch (Exception e) {
				logger.error("初始化FreeMarker配置出错", e);
			}
		}
	}

	/**
	 * 据数据及模板生成文件
	 * @param data 一个Map的数据结果集
	 * @param templateFileName ftl模版路径(已默认为WEB-INF/templates,文件名相对此路径)
	 * @param outFileName 生成文件名称(可带路径)
	 * @param isAbsPath 是否绝对路径
	 */
	public static void crateFile(Map<String, Object> data, String templateFileName, String outFileName) {
		if (!isInit) {
			System.out.println("FreeMarker模板引擎未初始化,请确认已经调用initFreeMarker()方法对其进行了初始化");
		}
		Writer out = null;
		try {
			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			Template template = config.getTemplate(templateFileName, ENCODING);

			outFileName = new StringBuffer(outFileName).toString();
			String outPath = outFileName.substring(0, outFileName.lastIndexOf(PATH_SEPARATOR));
			// 创建文件目录
			FileUtils.forceMkdir(new File(outPath));
			File outFile = new File(outFileName);
			out = new OutputStreamWriter(new FileOutputStream(outFile), ENCODING);

			// 处理模版
			template.process(data, out);
			out.flush();
			logger.info("由模板文件" + templateFileName + "生成" + outFileName + " 成功.");
		} catch (Exception e) {
			logger.error("由模板文件" + templateFileName + "生成" + outFileName + " 出错.", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("关闭Write对象出错", e);
			}
		}
	}

}