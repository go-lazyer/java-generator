package com.shadowh.lazy.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("all")
public class JdbcUtil {
	private static DataSource dataSource;
	private static Log logger = LogFactory.getLog(JdbcUtil.class);
	public static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static QueryRunner queryRunner;
	private static String driverName =  "com.mysql.jdbc.Driver"; 
	static{
        try {
			String url = PropertiesUtil.getStringValue("db.url");  
			String username = PropertiesUtil.getStringValue("db.user");  
			String password = PropertiesUtil.getStringValue("db.pass");
			init(url,username,password);
		} catch (Exception e) {
			logger.error("config.properties file cat not find ");
		}
	}
	
	public static void updateDataSource(String url,String username,String password) {
		init(url,username,password);
	}
	
	private static void init(String url,String username,String password){
        try {  
            BasicDataSource basicDataSource = new BasicDataSource();  
            basicDataSource.setUrl(url);  
            basicDataSource.setUsername(username);  
            basicDataSource.setPassword(password);  
            basicDataSource.setDriverClassName(driverName);  
            basicDataSource.setInitialSize(30);  
            basicDataSource.setMaxActive(10);  
            basicDataSource.setMaxIdle(20);  //最大闲置个数  
            basicDataSource.setMaxWait(1000);  //最大等待时间  
            dataSource=basicDataSource;
            queryRunner = new QueryRunner(dataSource);
            logger.error("Connection database success!");
        } catch (Exception e) {  
            logger.error("Connection database error!");
        } 
	}
	// 获取共享变量
	public static ThreadLocal<Connection> getThreadLocal() {
		return threadLocal;
	}
	/**
	 * 获取数据源
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	/**
	 * 开启事务
	 * @author hanchanghong
	 * @date 2014年10月11日 上午10:12:38
	 */
	public static void startTransaction() {
		// 首先获取当前线程的连接 if(conn ==           
		// null){//如果连接为空 conn =            
		// getConnection(); //从连接池中获取连接     
		// container.set(conn);//将此连接放在当前线程上
		Connection conn = threadLocal.get();
		try {
			if (conn == null) {
				conn = dataSource.getConnection();
				threadLocal.set(conn);
			}
			conn.setAutoCommit(false);// 开启事务
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 结束事务
	 * @author hanchanghong
	 * @date 2014年10月11日 上午10:12:38
	 */
	public static void stopTransaction() {
		// 首先获取当前线程的连接 if(conn ==           
		// null){//如果连接为空 conn =            
		// getConnection(); //从连接池中获取连接     
		// container.set(conn);//将此连接放在当前线程上
		Connection conn = threadLocal.get();
		try {
			if (conn != null) {
				conn.setAutoCommit(false);// 开启事务
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 提交事务
	 * @author hanchanghong
	 * @date 2014年10月11日 上午10:12:38
	 */
	public static void commit() {
		Connection conn = threadLocal.get();// 从当前线程上获取连接
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 回滚事务
	 * @author hanchanghong
	 * @date 2014年10月11日 上午10:19:25
	 */
	public static void rollback() {
		Connection conn = threadLocal.get();// 检查当前线程是否存在连接
		if (conn != null) {
			try {
				conn.rollback();// 回滚事务
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	/**
	 * 关闭连接
	 * @author hanchanghong
	 * @date 2014年10月11日 上午10:19:25
	 */
	public static void close() {
		Connection conn = threadLocal.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				threadLocal.remove();// 从当前线程移除连接 切记
			}
		}
	}

	/**
	 * 获取数据库连接
	 * @return 数据库连接 连接方式（连接池 错c3p0）
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static void setDataSource(BasicDataSource dataSource) {
		dataSource = dataSource;
	}

	/**
	 * 增删改 无事务
	 * @author hanchanghong
	 * @throws SQLException 
	 * @date 2014年10月11日 下午4:16:54
	 */
	public static int update(String sql) throws SQLException{
		return update(sql,null);
	}
	
	
	/**
	 * 增删改 无事务
	 * @author hanchanghong
	 * @throws SQLException 
	 * @date 2014年10月11日 下午4:16:54
	 */
	public static int update(String sql,Object[] params) throws SQLException{
		if(params==null){
			return queryRunner.update(sql);
		}else{
			return queryRunner.update(sql, params);
		}
	}
	/**
	 * 批量增删改 无事务
	 * @author hanchanghong
	 * @throws SQLException 
	 * @date 2014年10月11日 下午4:16:54
	 */
	public static int[] batch(String sql, Object[][] params) throws SQLException {
		return queryRunner.batch(sql, params);
	}
	

	/**
	 * 增删改 有事务
	 * @author hanchanghong
	 * @date 2014年10月11日 下午4:17:38
	 */
	public static int update(Connection conn, String sql) throws Exception {
		return queryRunner.update(conn, sql);
	}

	

	/**
	 * 查询记录总数
	 * @author hanchanghong
	 * @throws SQLException 
	 * @date 2014年10月11日 下午4:25:46
	 */
	public static long queryCount(String sql) throws SQLException {
		logger.info(sql);
		long count = (long)queryRunner.query(sql, new ScalarHandler(1));
		return count;
	}

	
	/**
	 * 下面三个方法为一组，返回值为List<Map<String, Object>>
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> query(String sql) throws SQLException {
		return query(sql, null);
	}
	public List<Map<String, Object>> query(String sql, Object param) throws SQLException {
		return query(sql, new Object[] { param });
	}
	public static List<Map<String, Object>> query(String sql, Object[] params) throws SQLException {
		logger.info(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (params == null) {
			list = (List<Map<String, Object>>) queryRunner.query(sql,new MapListHandler());
		} else {
			list = (List<Map<String, Object>>) queryRunner.query(sql,new MapListHandler(), params);
		}
		return list;
	}

	
	
	/**
	 * 下面三个方法为一组，返回值为List<javaBean>
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> query(Class<T> entityClass, String sql) throws SQLException {
		return query(entityClass, sql, null);
	}
	public static <T> List<T> query(Class<T> entityClass, String sql, Object param) throws SQLException {
		return query(entityClass, sql, new Object[] { param });
	}
	public static <T> List<T> query(Class<T> entityClass, String sql, Object[] params) throws SQLException {
		logger.info(sql);
		List<T> list = new ArrayList<T>();
		if (params == null) {
			list = (List<T>) queryRunner.query(sql, new BeanListHandler(entityClass));
		} else {
			list = (List<T>) queryRunner.query(sql, new BeanListHandler(entityClass), params);
		}
		return list;
	}

	/**
	 * 下面三个方法为一组，返回单条数据 返回值为Map<String, Object>
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> queryFirst(String sql) throws SQLException {
		return queryFirst(sql, null);
	}
	public static Map<String, Object> queryFirst(String sql, Object param) throws SQLException {
		return queryFirst(sql, new Object[] { param });
	}
	public static  Map<String, Object> queryFirst(String sql, Object[] params) throws SQLException {
		Map<String, Object> map = null;
		if (params == null) {
			map = (Map<String, Object>) queryRunner.query(sql,new MapHandler());
		} else {
			map = (Map<String, Object>) queryRunner.query(sql,new MapHandler(), params);
		}
		return map;
	}
	
	
	
	
	/**
	 * 下面三个方法为一组，返回单条数据 返回值为javaBean
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static <T> T queryFirst(Class<T> entityClass, String sql) throws SQLException {
		return queryFirst(entityClass, sql, null);
	}

	public static <T> T queryFirst(Class<T> entityClass, String sql, Object param) throws SQLException {
		return queryFirst(entityClass, sql, new Object[] { param });
	}

	public static <T> T queryFirst(Class<T> entityClass, String sql, Object[] params) throws SQLException {
		Object object = null;
		if (params == null) {
			logger.info(sql);
			object = queryRunner.query(sql, new BeanHandler(entityClass));
		} else {
			logger.info("sql = {"+sql+"};params size = {"+params.length+"}");
			object = queryRunner.query(sql, new BeanHandler(entityClass),params);
		}
		return (T) object;
	}


	/**
	 * 用于获取结果集中第一行某个字段的数据并转换成Object。
	 * @param sql
	 * @param columnName  字段
	 * @return
	 */
	public static Object findBy(String sql, String columnName) {
		return findBy(sql, columnName, null);
	}
	public static Object findBy(String sql, String columnName, Object param) {
		return findBy(sql, columnName, new Object[] { param });
	}
	public static Object findBy(String sql, String columnName, Object[] params) {
		Object object = null;
		try {
			if (params == null) {
				object = queryRunner.query(sql, new ScalarHandler(columnName));
			} else {
				object = queryRunner.query(sql, new ScalarHandler(columnName),
						params);
			}
		} catch (SQLException e) {
		}
		return object;
	}

	
	
	/**
	 * 用于获取结果集中第一行第几列的数据并转换成Object。
	 * @param sql
	 * @param columnIndex  列数
	 * @return
	 */
	public static Object findBy(String sql, int columnIndex) {
		return findBy(sql, columnIndex, null);
	}
	public static Object findBy(String sql, int columnIndex, Object param) {
		return findBy(sql, columnIndex, new Object[] { param });
	}
	public static Object findBy(String sql, int columnIndex, Object[] params) {
		Object object = null;
		try {
			if (params == null) {
				object = queryRunner.query(sql, new ScalarHandler(columnIndex));
			} else {
				object = queryRunner.query(sql, new ScalarHandler(columnIndex),params);
			}
		} catch (SQLException e) {
		}
		return object;
	}
}
