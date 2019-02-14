<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>用户列表</title>
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
	<blockquote class="layui-elem-quote news_search">
		<form  class="layui-form">
		<div>
			</div>
			<div class="layui-inline">
				<input type="text" id="operation" class="layui-input userName"
					name="operation" placeholder="请输入需要查询的操作" value="">
			</div>
			<div class="layui-inline">
				<input type="text" id="createTimeStart" class="layui-input userName"
					name="createTimeStart" placeholder="操作时间(开始)" value="">
			</div>		
			<div class="layui-inline">
				<input type="text" id="createTimeEnd" class="layui-input userName"
					name="createTimeEnd" placeholder="操作时间(结束)" value="">
			</div>	
			<a class="layui-btn search_btn" lay-submit="" data-type="search" lay-filter="search">查询</a>
			<div class="layui-inline">
				（支持模糊查询）
			</div>
	</blockquote>
	</form>
	<div class="layui-form">
	  	<table id="logList" lay-filter="logList"></table>
	</div>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/html" id="sexTpl">
 		 {{#  if(d.sex === '0'){ }}
   		 <span style="color: #F581B1;">女</span>
  		{{#  } else if(d.sex === '1'){ }}
   		 	男
		{{#  } else{ }}
   		 	保密
  		{{#  } }}
	</script>
	<script>
	layui.config({
		base : "js/"
	}).use(['form','layer','jquery','table','laydate'],function(){
		var form = layui.form,table = layui.table,
			layer = parent.layer === undefined ? layui.layer : parent.layer,laydate = layui.laydate
			$ = layui.jquery,
			nowTime = new Date().valueOf(),max = null, active = {
				search: function(){
			        var operation=$("#operation"),createTimeStart=$("#createTimeStart"),createTimeEnd=$("#createTimeEnd");
			        //执行重载
			        table.reload('logList', {
			          page: {
			            curr: 1 //重新从第 1 页开始
			          }
			          ,where: {
			            //key: {
			              operation:operation.val(),
			              createTimeStart:createTimeStart.val(),
			              createTimeEnd:createTimeEnd.val()
			            //}
			          }
			        });
			      }
			    };
			    
		var start = laydate.render({
	        elem: '#createTimeStart',
	        type: 'datetime',
	        max: nowTime,
	        btns: ['clear', 'confirm'],
	        done: function(value, date){
	            endMax = end.config.max;
	            end.config.min = date;
	            end.config.min.month = date.month -1;
	        }
	    });
	    var end = laydate.render({
	        elem: '#createTimeEnd',
	        type: 'datetime',
	        max: nowTime,
	        done: function(value, date){
	            if($.trim(value) == ''){
	                var curDate = new Date();
	                date = {'date': curDate.getDate(), 'month': curDate.getMonth()+1, 'year': curDate.getFullYear()};
	            }
	            start.config.max = date;
	            start.config.max.month = date.month -1;
	        }
	    })
		
		//加载页面数据
		table.render({
			id:'logList'
		    ,elem: '#logList'
		    ,url: 'getLogList' //数据接口
		    ,limit:10//每页默认数
		    ,limits:[10,20,30,40]
		    ,cols: [[ //表头
		      {field:'id', title: 'ID',width:70,sort:true}
	          ,{field:'operation', title: '操作'}
	          ,{field:'method', title: '请求路径'}
	          ,{field:'params', title: '请求参数'}
	          ,{field:'ip', title: 'ip'}
	          ,{field:'username', title: '操作人'}
	          ,{field:'createTime', title: '评论时间',templet: '<div>{{ formatTime(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>',sort:true}
		    ]]
				,page: true //开启分页
		  });
		

		//查询
	 	$(".search_btn").click(function(){
			var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		})

	})

	//格式化时间
	function formatTime(datetime,fmt){
		if(datetime==null||datetime==0){
			return "";
		}
		if (parseInt(datetime)==datetime) {
		    if (datetime.length==10) {
		      datetime=parseInt(datetime)*1000;
		    } else if(datetime.length==13) {
		      datetime=parseInt(datetime);
		    }
		  }
		  datetime=new Date(datetime);
		  var o = {
		  "M+" : datetime.getMonth()+1,                 //月份   
		  "d+" : datetime.getDate(),                    //日   
		  "h+" : datetime.getHours(),                   //小时   
		  "m+" : datetime.getMinutes(),                 //分   
		  "s+" : datetime.getSeconds(),                 //秒   
		  "q+" : Math.floor((datetime.getMonth()+3)/3), //季度   
		  "S"  : datetime.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		  fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		  if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;
	}
	</script>
</body>
</html>