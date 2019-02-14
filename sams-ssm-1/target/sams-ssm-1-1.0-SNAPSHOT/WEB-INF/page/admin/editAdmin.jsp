<jsp:useBean id="ad" scope="request" type="com.jefflee.entity.shiro.TbAdmin"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>编辑管理员</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
<style type="text/css">
.layui-form-item .layui-inline {
	width: 33.333%;
	float: left;
	margin-right: 0;
}

@media ( max-width :1240px) {
	.layui-form-item .layui-inline {
		width: 100%;
		float: none;
	}
}
</style>
</head>
<body class="childrenBody">
	<form class="layui-form" style="width: 80%;">
		<!-- 管理员id -->
		<input type="hidden" name="id" value="${ad.id}"/>
		<div class="layui-form-item">
			<label class="layui-form-label">登录名</label>
			<div class="layui-input-block">
				<input type="text" id="username" class="layui-input userName"
					lay-verify="required" readonly="readonly" name="username" value="${ad.username }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block">
				<input type="text" name="fullname" class="layui-input userName"
					lay-verify="required" placeholder="请输入姓名" value="${ad.fullname }">
			</div>
		</div>
		<%--<div class="layui-form-item">--%>
			<%--<label class="layui-form-label">邮箱</label>--%>
			<%--<div class="layui-input-block">--%>
				<%--<input type="text" name="eMail" id="eMail" class="layui-input userName"--%>
					<%--lay-verify="email" placeholder="请输入邮箱" value="${ad.eMail }">--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block">
			<c:if test="${ad.sex=='0' }">
				<input type="radio" name="sex" value="1" title="男" >
				<input type="radio" name="sex" value="0" title="女" checked> 
				<input type="radio" name="sex" value="3" title="保密">
			</c:if>
			<c:if test="${ad.sex=='1' }">
				<input type="radio" name="sex" value="1" title="男" checked>
				<input type="radio" name="sex" value="0" title="女" > 
				<input type="radio" name="sex" value="3" title="保密">
			</c:if>
			<c:if test="${ad.sex=='2' }">
				<input type="radio" name="sex" value="1" title="男" >
				<input type="radio" name="sex" value="0" title="女"> 
				<input type="radio" name="sex" value="3" title="保密" checked>
			</c:if>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">出生日期</label>
			<div class="layui-input-block">
				<input type="text" id="birthday" class="layui-input userName"
					name="birthday" lay-verify="required" placeholder="请输入出生日期" value="${ad.birthday }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">地址</label>
			<div class="layui-input-block">
				<input type="text" name="address" class="layui-input userName" lay-verify="required" placeholder="请输入地址" value="${ad.address }">
			</div>
		</div> 
		<div class="layui-form-item">
			<label class="layui-form-label">手机号</label>
			<div class="layui-input-block">
				<input type="text" name="phone" class="layui-input userName"
					lay-verify="phone" placeholder="请输入手机号" value="${ad.phone }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">分配角色</label>
			<div class="layui-input-block">
				<select name="roleId">
					<option value="">请选择</option>
					<c:forEach items="${roles }" var="r">
						<c:if test="${ad.roleId==r.roleId }">
							<option value="${r.roleId }" selected>${r.roleName }</option>
						</c:if>
						<c:if test="${ad.roleId!=r.roleId }">
							<option value="${r.roleId }">${r.roleName }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="editAdmin">立即保存</button>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="../../../layui/layui.js"></script>
	<script type="text/javascript" src="../../page/admin/editAdmin.js"></script>
</body>
</html>