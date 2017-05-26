###lazy-generator JAVA代码自动生成的插件
####一.插件介绍
	改插件是基于 mybatis+mysql+eclipse的代码自动生成工具，旨在帮助程序源脱离枯燥的DAO层编辑
	
####二.插件安装
    1.下载插件 https://coding.net/u/hanchanghong/p/lazy-generator/git/tree/master/plugins，放到 eclipse 的plugins文件夹中，重启eclipse
    2.下载配置文件https://coding.net/u/hanchanghong/p/lazy-generator/git/blob/master/src/lazy-config.xml， 放到项目的配置文件目录中。
    3.在lazy-config.xml 中修改相关信息。
    4.右键配置文件，Lazy --> Generator
    5.generator success!

####三.配置文件介绍
	<?xml version="1.0" encoding="UTF-8" ?>
	    <lazy-conf>
	    	<properties>
	    		<property name="author" value="lazy" /><!--代码生成的作者 -->
	    	</properties>
	    	<!--数据源配置-->
	    	<data-source >
	    		<property name="driver" value="com.mysql.jdbc.Driver"></property>
	    		<property name="url" value="jdbc:mysql://localhost/test?useUnicode=true&amp;characterEncoding=utf8&amp;allowMultiQueries=true&amp;zeroDateTimeBehavior=convertToNull" />
	    		<property name="username" value="root" />
	    		<property name="password" value="123" />
	    	</data-source>
	    	
	    	<!--各种文件的生成目录。如果不配置则该文件不会生成。
	    		其中entity，mapper，mapper-xml每次执行都是覆盖，所以不要修改这些文件，如果要现有sql满足不了需求，lazy提供了ExtendMapper拓展的mapper文件供编辑，
	    		不要忘了添加到mybatis-config.xml中
	    		service，service-impl，controller只会生成一次，如果手动创建了就不会自动生成。
	    	-->
	    	<entity file-package="com.shadowh.test.entity" file-path="src/main/java" />
	    	<mapper file-package="com.shadowh.test.mapper" file-path="src/main/java" />
	    	<mapper-xml file-package="mapper" file-path="src/main/resources" />
	    	<service file-package="com.shadowh.test.service" file-path="src/main/java" />
	    	<service-impl file-package="com.shadowh.test.service" file-path="src/main/java" />
	    	<controller file-package="com.shadowh.test.controller" file-path="src/main/java" />
	    	
	     	<table table-name="l_student"><!--学生表-->
	    		<property name="module-name" value="student"/>
	    	</table>
	     	<table table-name="l_projector"><!--投影仪表-->
	    		<property name="module-name" value="projector"/>
	    	</table>
	    	<!--lazy-generator  -->
	     	<table table-name="l_classroom"><!-- 教室表 -->
	    		<property name="module-name" value="classroom"/>
	    		<!-- 表关联 -->
	    		<join-table>
	    			<!--教室和学生是一对多关系 foreign-key 为学生表中的教室id-->
	    			<property table-name="l_student" foreign-key="classroom_id" type="one-to-many"/>
	    			<!--教室和投影仪是一对一关系 foreign-key 为投影仪表中的教室id-->
	    			<property table-name="l_projector" foreign-key="classroom_id" type="one-to-one"/>
	    		</join-table>
	    	</table>
	    </lazy-conf>
	    
####四.生成文件介绍
    |--src/main/java
        |----com.shadowh.test.controller
            |---ClassroomController.java			//首次生成，可编辑
            |---ProjectorController.java
            |---StudentController.java
        |----com.shadowh.test.entity
            |---ClassroomEntity.java				//实体类
            |---ClassroomExample.java				//查询类，用于查询
            |---ClassroomParam.java					//传参列，用于接口接受参数
            |---ClassroomView.java					//视图类，用于生成接口数据
            |---ProjectorEntity.java
            |---ProjectorExample.java
            |---ProjectorParam.java
            |---ProjectorView.java
            |---StudentEntity.java
            |---StudentExample.java
            |---StudentParam.java
            |---StudentView.java
        |----com.shadowh.test.mapper
            |---ClassroomExtendMapper.java			//扩展mapper 用于扩展SQL 首次生成，可编辑
            |---ClassroomMapper.java				//基础的SQL,每次都覆盖，禁止编辑
            |---ProjectorExtendMapper.java
            |---ProjectorMapper.java
            |---StudentExtendMapper.java
            |---StudentMapper.java
        |----com.shadowh.test.service
            |---ClassroomService.java				//扩展mapper 用于扩展SQL 首次生成，可编辑
            |---ClassroomServiceImpl.java			//扩展mapper 用于扩展SQL 首次生成，可编辑
            |---ProjectorService.java
            |---ProjectorServiceImpl.java
            |---StudentService.java
            |---StudentServiceImpl.java	
    |--src/main/resources
        |---mapper
            |---ClassroomExtendMapper.xml			//扩展mapper 用于扩展SQL 首次生成，可编辑
            |---ClassroomMapper.xml					//基础的SQL,每次都覆盖，禁止编辑
            |---ProjectorExtendMapper.xml
            |---ProjectorMapper.xml
            |---StudentExtendMapper.xml
            |---StudentMapper.xml		 