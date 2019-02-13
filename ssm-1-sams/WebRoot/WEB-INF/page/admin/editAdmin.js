var $;
var $form;
var form;
//var areaData = address;
layui.config({
	base : "js/"
}).use(['form','layer','jquery','laydate'],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		laydate = layui.laydate;
		$ = layui.jquery;
		form = layui.form;
		//$form=$('form');
		//初始化省
		//loadProvince();
		
		laydate.render({
			elem: '#birthday' //指定元素
			,max: 'new Date()'
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


 	form.on("submit(editAdmin)",function(data){
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		var msg;
        $.ajax({
            type: "post",
            url: "/sys/updAdmin",
            data:data.field,
            dataType:"json",
            success:function(d){
                if(d.code===0){
                    msg="修改成功！";
                }else{
                    msg=d.msg;
                }
            }
        });
 		setTimeout(function(){
 			top.layer.close(index);
 			top.layer.msg(msg);
 			// layer.closeAll("iframe");
 			//刷新父页面
	 		parent.location.reload();
        },2000);
 		return false;
 	})
	
})