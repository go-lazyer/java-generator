package com.shadowh.lazy.util;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
@SuppressWarnings("all")
public class PropertiesUtil {  
	private static String configFile="config.properties";
    /** 
     * 获取某个properties文件中的某个key对应的value值 
     * @param fileName 
     * @param key 
     * @return 返回key说对应的value值 
     * */  
    public static String getStringValue(String fileName, String key,String defaultValue){  
        CompositeConfiguration config = new CompositeConfiguration();
        PropertiesConfiguration pc = null;
        String value="";
        try {  
            pc = new PropertiesConfiguration(fileName);  
            config.addConfiguration(pc);  
            value = config.getString(key,defaultValue).trim();  
        } catch (ConfigurationException e) {  
            e.printStackTrace();  
        } finally {
        	return value;  
        } 
    }  
    public static String getStringValue(String key,String defaultValue){  
        return PropertiesUtil.getStringValue(configFile, key,defaultValue);  
    }
    public static String getStringValue(String key){  
        return PropertiesUtil.getStringValue(key,"");  
    }
	public static int getIntValue(String fileName, String key,int defaultValue){  
    	CompositeConfiguration config = new CompositeConfiguration();
    	PropertiesConfiguration pc = null;
    	int value=0;
    	try {  
    		pc = new PropertiesConfiguration(fileName);  
    		config.addConfiguration(pc);  
    		value = config.getInt(key, defaultValue);
    	} catch (ConfigurationException e) {  
    		e.printStackTrace();  
    	} finally {
    		return value;  
    	} 
    }
    public static int getIntValue(String key,int defaultValue){  
    	return PropertiesUtil.getIntValue(configFile, key, defaultValue);  
    }
    public static int getIntValue(String key){  
    	return PropertiesUtil.getIntValue(key, 0);  
    }
    /** 
     * 获取某个properties文件中的某个key对应的value值(值是个数组) 
     * @param fileName 
     * @param key 
     * @param delimiter 
     * @return 返回key说对应的value数组值(使用时遍历数组值后要加.trim()) 
     * */  
    public static String[] getPropertiesValues(String fileName, String key, char delimiter){  
        CompositeConfiguration config = new CompositeConfiguration();  
        PropertiesConfiguration pc = null;  
            if(!Character.isWhitespace(delimiter)){  
               AbstractConfiguration.setDefaultListDelimiter(delimiter);  
            }  
            try {
				pc = new PropertiesConfiguration(fileName);
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}  
            config.addConfiguration(pc);  
  
            String[] filevalues = config.getStringArray(key);  
            return filevalues;  
    }  
}