<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>系统首页</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="../../css/main.css" media="all" />
	<script type="text/javascript" src="../../js/echarts.js"></script>
</head>
<body class="childrenBody" style="margin:1%">
<blockquote class="layui-elem-quote">
	<p>欢迎使用教务管理系统</p>
</blockquote>
<fieldset class="layui-elem-field layui-field-title">
	<legend>信息统计</legend>
</fieldset>
<div>
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>
		<tr>
			<th><strong>统计</strong></th>
			<th><strong>数量</strong></th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>用户总数</td>
			<td class="userTotal"></td>
		</tr>
		<tr>
			<td>学生总数</td>
			<td class="studentTotal"></td>
		</tr>
		<tr>
			<td>老师总数</td>
			<td class="teacherTotal"></td>
		</tr>
		</tbody>
	</table>
</div>
<fieldset class="layui-elem-field layui-field-title">
	<legend>学生性别占比</legend>
</fieldset>
<div id="info" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

</script>
<div class="sysNotice col">
	<blockquote class="layui-elem-quote title">系统基本参数</blockquote>
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col>
		</colgroup>
		<tbody>
		<tr>
			<td>当前版本</td>
			<td class="version"></td>
		</tr>
		<tr>
			<td>开发作者</td>
			<td class="author"></td>
		</tr>
		<tr>
			<td>网站首页</td>
			<td class="homePage"></td>
		</tr>
		<tr>
			<td>服务器环境</td>
			<td class="server"></td>
		</tr>
		<tr>
			<td>数据库版本</td>
			<td class="dataBase"></td>
		</tr>
		<tr>
			<td>最大上传限制</td>
			<td class="maxUpload"></td>
		</tr>
		</tbody>
	</table>

</div>
</div>

<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="../../js/main.js"></script>
</body>
</html>