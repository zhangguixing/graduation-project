<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加角色</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
	<style type="text/css">
		.layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
		@media(max-width:1240px){
			.layui-form-item .layui-inline{ width:100%; float:none; }
		}
	</style>
</head>
<body class="childrenBody" style="margin-top: 5%">
	<form class="layui-form" style="width:80%;" id="erf">
		<!-- 权限提交隐藏域 -->
		<input type="hidden" id="m" name="m"/>
		<div class="layui-form-item">
			<label class="layui-form-label">角色编号</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input userName" id="roleId" name="roleId" readonly="readonly" value="${role.roleId }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色名</label>
			<div class="layui-input-block">
				<input type="text" id="roleName" class="layui-input userName" lay-verify="required" placeholder="请输入角色名" name="roleName" value="${role.roleName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色描述</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入角色描述" class="layui-textarea linksDesc" lay-verify="required" name="roleRemark" >${role.roleRemark }</textarea>
			</div>
		</div>
		<!--权限树xtree  -->
		<div class="layui-form-item">
		<label class="layui-form-label">修改权限：</label>
	      	<div id="xtree" style="width:200px;margin-left: 100px">
	      	</div>
      	</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="editRole">立即提交</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="../../../layui/layui.js"></script>
		<script type="text/javascript" src="../../../js/layui-xtree.js"></script>
	<script type="text/javascript" src="../../page/admin/editRole.js"></script>
</body>
</html>