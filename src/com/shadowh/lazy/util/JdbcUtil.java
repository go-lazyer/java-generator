package com.shadowh.lazy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shadowh.lazy.entity.FieldEntity;  
  
public class JdbcUtil {  
  
  
    private static String driver="com.mysql.jdbc.Driver";  
  
  
    private static Connection connection;  
  
    private static PreparedStatement pstmt;  
  
    private static ResultSet resultSet;  
  
    /** 
     * 获取数据库连接 
     * @return 数据库连接 
     */  
    public static void init(String url,String username,String password) {  
        try {  
            Class.forName(driver); // 注册驱动  
            connection = DriverManager.getConnection(url, username, password); // 获取连接  
        } catch (Exception e) {  
            throw new RuntimeException("get connection error!", e);  
        }  
    }  
  
    public static List<FieldEntity> query(String sql)  throws SQLException {  
        List<FieldEntity> list = new ArrayList<FieldEntity>();  
        try {
			pstmt = connection.prepareStatement(sql);  
			resultSet = pstmt.executeQuery();  
			ResultSetMetaData metaData = resultSet.getMetaData();  
			while (resultSet.next()) {  
				FieldEntity fieldEntity= new FieldEntity();
				fieldEntity.setColumn(resultSet.getString("column"));
				fieldEntity.setColumnDelimit(resultSet.getString("columnDelimit"));
				fieldEntity.setColumnType(resultSet.getString("columnType"));
				fieldEntity.setComment(resultSet.getString("comment"));
				fieldEntity.setIsPrimaryKey(resultSet.getString("isPrimaryKey"));
				fieldEntity.setIsNullable(resultSet.getString("isNullable"));
				fieldEntity.setDefaultValue(resultSet.getString("defaultValue"));
				fieldEntity.setExtra(resultSet.getString("extra"));
			    list.add(fieldEntity);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {  
			closeConn();
        } 
        return list;  
    }  
  
    /** 
     * 释放资源 
     */  
    public static void closeConn() {  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (pstmt != null) {  
            try {  
                pstmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (connection != null) {  
            try {  
                connection.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    public static void main(String[] args) {  
    	String url="jdbc:mysql://localhost:3306/admin?useUnicode=true&amp;characterEncoding=utf8&amp;allowMultiQueries=true&amp;zeroDateTimeBehavior=convertToNull";
    	String sql="select column_name 'column',column_name columnDelimit,data_type columnType,column_comment comment,column_key isPrimaryKey, "
				+ " is_nullable isNullable  ,column_default defaultValue,extra"
				+ " from information_schema.COLUMNS "
				+ " where table_name = 'tb_admin' and table_schema = 'admin'";
    	JdbcUtil.init(url, "root", "123");
        try {  
            List<FieldEntity> result = JdbcUtil.query(sql);  
            for (FieldEntity m : result) {  
                System.out.println(m);  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
        }  
    }  
} 