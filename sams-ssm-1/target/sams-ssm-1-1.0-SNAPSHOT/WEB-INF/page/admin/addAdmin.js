var $;
var $form;
var form;
layui.config({
	base : "js/"
}).use(['form','layer','jquery','laydate'],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,laydate = layui.laydate;
		$ = layui.jquery;
		form = layui.form;
		laydate.render({
			elem: '#birthday' //指定元素
			,max: 'new Date()'
		});
		
		//自定义验证规则
		form.verify({ 
			pass: [/(.+){6,16}$/, '密码必须6到16位']
			,repass: function(value){
				var repassvalue = $('#password').val();
				if(value != repassvalue){
					return '两次输入的密码不一致!';
				}
			}
		});
		
		$("#username").blur(function(){
			$.ajax({
	            type: "get",
	            url: "checkAdminName/"+$("#username").val(),
	            success:function(data){
	            	if(data.code!=0){
	            		top.layer.msg(data.msg);
	            		$("#username").val("");
	            		$("#username").focus();
	            	}
	            }
	        });
		});
		
		// $("#eMail").blur(function(){
		// 	$.ajax({
		// 		type: "post",
		// 		url: "checkAdminByEmail/"+$("#eMail").val(),
		// 		success:function(data){
		// 			if(data.code!=0){
		// 				top.layer.msg(data.msg);
		// 				$("#eMail").val("");
		// 				$("#eMail").focus();
		// 			}
		// 		}
		// 	});
		// });

 	form.on("submit(addAdmin)",function(data){
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		var msg;
 		$.ajax({
    		type: "post",
            url: "insAdmin",
            data:data.field,
			dataType:"json",
			success:function(d){
				if(d.code===0){
		        	msg="添加成功！";
				}else{
		        	msg=d.msg;
				}
			}
        });
 		setTimeout(function(){
 			top.layer.close(index);
 			top.layer.msg(msg);
 			layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },2000);
 		return false;
 	})
	
})