<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎页</title>
</head>
<body>

<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
	<p style="font-size: 50px; line-height: 60px; height: 60px;">${systemInfo.schoolName}</p>
	<p style="font-size: 25px; line-height: 30px; height: 30px;">欢迎使用学生成绩管理系统</p>
  	<p>开发人员：张贵兴</p>
  	<p>学校官网地址:<a href="http://www.hebeinu.edu.cn">http://www.hebeinu.edu.cn</a></p>
  	<hr />
  	<h2>系统环境</h2>
  	<p>系统环境：Windows</p>
	<p>开发工具：Eclipse/idea</p>
	<p>Java版本：JDK 1.8</p>
	<p>服务器：tomcat 7.0</p>
	<p>数据库：MySQL 5.1</p>
	<p>系统采用技术： Springboot+Spring+Mybatis+Jsp+druid+EasyUI+jQuery+Ajax</p>
</div>
</body>
</html>