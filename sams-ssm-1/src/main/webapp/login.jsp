<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>登录--教务系统</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="css/login.css" media="all" />
    <script type="text/javascript">
        if(window !=top){
            top.location.href=location.href;
        }
    </script>
</head>
<body>


<div class="video_mask"></div>
<div class="login">
    <h1>登录教务系统</h1>
    <form class="layui-form" id="form">
        <div class="layui-form-item">
            <input class="layui-input" name="username" placeholder="用户名" value="admin" lay-verify="required" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="password" placeholder="密码" value="123456"  lay-verify="required" type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" style="width: 140px;" name="vcode" placeholder="验证码" lay-verify="required" type="text" autocomplete="off" maxlength="4">
            <div class="code"><img id="captcha" src="sys/vcode" width="100" height="36" onclick="refreshCode(this)"></div>
        </div>
        <button class="layui-btn login_btn" lay-submit="" lay-filter="login" id="btn">登录</button>
    </form>
</div>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</body>
</html>