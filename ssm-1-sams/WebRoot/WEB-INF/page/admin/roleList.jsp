<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>文章列表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../../css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="../../../css/list.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote list_search">
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal roleAdd_btn" ><i class="layui-icon">&#xe608;</i>   添加角色</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-danger batchDel" data-type="delCheckData"><i class="layui-icon">&#xe640;</i>批量删除</a>
		</div>
		<div class="layui-inline">
			<div class="layui-form-mid layui-word-aux"></div>
		</div>
	</blockquote>
	<!-- 数据表格 -->
	<table id="roleList" class="roleList" lay-filter="roleList"></table>
	<script type="text/javascript" src="../../../layui/layui.js"></script>
	<script type="text/javascript" src="../../page/admin/roleList.js"></script>
	<script type="text/html" id="barEdit">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
</body>
</html>