layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','table'],function(){
	var form = layui.form,table = layui.table;
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
		
		//数据表格
		table.render({
			id:'roleList',
		    elem: '#roleList'
		    ,url: 'getRoleList' //数据接口
		    ,cellMinWidth: 80
		    ,limit:10//每页默认数
		    ,limits:[10,20,30,40]
		    ,cols: [[ //表头
              {type:'checkbox'}
              ,{field:'roleId', title: 'ID', sort: true}
              ,{field:'roleName', title: '角色名'}
              ,{field:'roleRemark', title: '角色描述'}
              //,{field:'roleName', title: '角色名',edit: 'text'}
              ,{title: '操作',toolbar: '#barEdit'}
		    ]]
				,page: true //开启分页
		  });
		
		//监听单元格编辑
		  /*table.on('edit(test)', function(obj){
		    var value = obj.value //得到修改后的值
		    ,data = obj.data //得到所在行所有键值
		    ,field = obj.field; //得到字段
		    setTimeout(function(){
	        	$.ajax({
	                type: "POST",
	                url: "saveRole",
	                data:{'roleId':data.roleId,'roleName':value},
	            });
	        },1000);
		    layer.msg('角色名更改为：'+ value,{icon: 1});
		  });*/
		
		//监听工具条
		  table.on('tool(roleList)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'del'){
		    	if(data.roleName=='超级管理员'){
		    		layer.msg("不允许操作此角色！",{icon: 5});
		    		return;
		    	}
		      layer.confirm('真的删除行么', function(index){
		    	  $.ajax({
		    		  url:'delRole/'+data.roleId,
		    		  type : "get",
		    		  success : function(d){
		    			  if(d.code==0){
		    				  //obj.del();
		    				  table.reload('roleList', {})
		    			  }else{
		    				  layer.msg("权限不足，联系超管！",{icon: 5});
		    			  }
		    		  }
		    	  })
		        layer.close(index);
		      });
		    } else if(obj.event === 'edit'){
		    	if(data.roleName=='超级管理员'){
		    		layer.msg("不允许操作此角色！",{icon: 5});
		    		return;
		    	}
		       layui.layer.open({
		    	  type: 2,
		    	  title:"编辑角色",
				  area: ['420px', '500px'],
		    	  content:"editRole?roleId="+data.roleId+"&roleName="+data.roleName+"&roleRemark="+data.roleRemark, //这里content是一个普通的String
                  success : function(layero, index){
                      layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                          tips: 3
                      });
                  }
		      })
		    }
		  });
		  
	//添加角色
	$(".roleAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加角色",
			type : 2,
			content : "addRole",
			success : function(layero, index){
				layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})
	
	//批量删除角色
	$(".batchDel").click(function(){
		var checkStatus = table.checkStatus('roleList')
	      ,data = checkStatus.data,roleStr='',flag=false;
//	      layer.alert(JSON.stringify(data));
		
		if(data.length>0){
			$.each(data, function (n, value) {
				  //避免选择不允许操作角色
	              if(value.roleName=='超级管理员'){
	            	  flag=true;
	            	  layer.msg('"超级管理员"不允许删除！',{icon: 5});
	            	  return;
	              }
	              roleStr+=value.roleId+',';
	          });
			//包含不允许操作角色，结束方法
			  if(flag){
				  return;
			  }
			  roleStr=roleStr.substring(0,roleStr.length-1);
			  layer.confirm('真的要删除<strong>'+data.length+'</strong>条数据吗？', function(index){
				//调用删除接口
				  $.ajax({
			    		  url:'delRoles/'+roleStr,//接口地址
			    		  type : "get",
			    		  success : function(d){
			    			  if(d.code==0){
			    				  //删除成功，刷新父页面
			    				  parent.location.reload();
			    			  }else{
			    				  layer.msg("权限不足，联系超管！",{icon: 5});
			    			  }
			    		  }
			    	  })
			  });
		}else{
			layer.msg("请选择要操作的数据！");
		}
		
	})
	
})
