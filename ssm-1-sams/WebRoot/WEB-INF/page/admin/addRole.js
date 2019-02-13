var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
		
		var xtree = new layuiXtree({
		      elem: 'xtree1'  //(必填) 放置xtree的容器id，不带#号
		      , form: form    //(必填) layui 的 from
		      , data: 'xtreedata'  //(必填) 数据接口，需要返回指定结构json字符串
		      , ckall: true   //启动全选功能，默认false
		      , ckallback: function () {}//全选框状态改变后执行的回调函数
		});
		
 	form.on("submit(editRole)",function(data){
 		var list=xtree.GetChecked()
 		//菜单id
 		var mStr="";
 		for(var i=0;i<list.length;i++){
 			mStr+=list[i].value+",";
 			if(xtree.GetParent(list[i].value)!=null){
 				mStr+=xtree.GetParent(list[i].value).value+",";
 				if(xtree.GetParent(xtree.GetParent(list[i].value).value)!=null){
 	 				mStr+=xtree.GetParent(xtree.GetParent(list[i].value).value).value+",";
 				}
 			}else{
 				mStr+=list[i].value+",";
 			}
 		}
 		//去除字符串末尾的‘,’
 		mStr=mStr.substring(0,mStr.length-1)
 		var m=$("#m");
 		//将权限字符串写进隐藏域
 		m.val(mStr)
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		var msg;
 		$.ajax({
            type: "POST",
            url: "insRole",
            data:$("#arf").serialize(),
            success:function(d){
				if(d.code==0){
					msg="添加成功";
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
 	
 	//角色名唯一性校验
 	$("#roleName").blur(function(){
 		$.ajax({
            type: "get",
            url: "checkRoleName?roleName="+$("#roleName").val(),
            success:function(data){
            	if(data.code!=0){
            		top.layer.msg(data.msg);
            		$("#roleName").val("");
            		$("#roleName").focus();
            	}
            }
        });
 	})
	
})