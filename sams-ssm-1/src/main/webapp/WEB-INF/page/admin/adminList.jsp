<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>管理员列表</title>
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
<input type="hidden" id="adminId" value="<shiro:principal property="id"/>"/>
<blockquote class="layui-elem-quote list_search">
	<div class="layui-inline">
		<a class="layui-btn layui-btn-normal adminAdd_btn" ><i class="layui-icon">&#xe608;</i>   添加用户</a>
	</div>
	<div class="layui-inline">
		<a class="layui-btn layui-btn-normal adminAdd_btn" ><i class="layui-icon">&#xe608;</i>   批量添加</a>
	</div>
	<div class="layui-inline">
		<a class="layui-btn layui-btn-danger batchDel"><i class="layui-icon">&#xe640;</i>批量删除</a>
	</div>
	<div class="layui-input-inline">
		<input type="text" id="fullname" value="" placeholder="请输入名字查询" class="layui-input search_input">
	</div>
	<div class="layui-input-inline layui-form">
		<select name="role" class="" id="role">
			<option value="-1">全部角色</option>
			<%--<option value="1">未激活</option>--%>
			<%--<option value="2">正常</option>--%>
			<%--<option value="3">禁用</option>--%>
		</select>
	</div>
	<a class="layui-btn search_btn" lay-submit="" data-type="search" lay-filter="search">查询</a>
	<div class="layui-inline">
		（支持模糊查询）
	</div>
	<div class="layui-inline">
		<div class="layui-form-mid layui-word-aux"></div>
	</div>
</blockquote>
<!-- 数据表格 -->
<table id="adminList" lay-filter="test"></table>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="../../page/admin/adminList.js"></script>
<script type="text/html" id="barEdit">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="sexTpl">
	{{#  if(d.sex === '0'){ }}
	<span style="color: #F581B1;">女</span>
	{{#  } else if(d.sex === '1'){ }}
	男
	{{#  } else{ }}
	保密
	{{#  } }}
</script>
</body>
</html>