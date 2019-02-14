layui.config({
    base : "js/"
}).use(['form','layer','jquery','laypage','table','laytpl'],function(){
    var form = layui.form,table = layui.table;
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        active = {
            search: function () {
                // var nickname = $('#nickname'), sex = $('#sex option:selected'), status = $('#status option:selected');
                // createTimeStart = $("#createTimeStart"), createTimeEnd = $("#createTimeEnd");
                var fullname = $('#fullname'), roleId = $('#role option:selected');
                //执行重载
                table.reload('adminList', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        //key: {
                        // nickname: nickname.val(),
                        // sex: sex.val(),
                        // status: status.val(),
                        // createTimeStart: createTimeStart.val(),
                        // createTimeEnd: createTimeEnd.val()
                        //}
                        fullname:fullname.val(),
                        roleId:roleId.val()
                    }
                });
            }
        };
    //数据表格
    table.render({
        id:'adminList',
        elem: '#adminList'
        ,url: 'getAdminList' //数据接口
        ,cellMinWidth: 80
        ,limit:10//每页默认数
        ,limits:[10,20,30,40]
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field:'id', title: 'ID', sort: true}
            ,{field:'username', title: '学号/工号'}
            ,{field:'fullname', title: '名字'}
            // ,{field:'eMail', title: '邮箱'}
            ,{field:'sex', title: '性别',templet: '#sexTpl'}
            ,{field:'birthday', title: '出生日期',templet: '<div>{{ formatTime(d.birthday,"yyyy-MM-dd")}}</div>'}
            ,{field:'address', title: '地址'}
            ,{field:'phone', title: '联系方式'}
            ,{field:'roleName', title: '角色'}
            ,{title: '操作',toolbar: '#barEdit'}
        ]]
        ,page: true //开启分页
    });
    //监听工具条
    table.on('tool(test)', function(obj){
        var data = obj.data,adminId=$("#adminId").val();
        if(obj.event === 'del'){
            if(data.roleName==='超级管理员'){
                layer.msg("不允许操作此角色！",{icon: 5});
                return;
            }
            if(data.id===adminId){
                layer.msg("不允许删除自己！",{icon: 5});
                return;
            }
            layer.confirm('真的删除行么', function(index){
                $.ajax({
                    url:'delAdminById/'+data.id,
                    type : "get",
                    success : function(d){
                        if(d.code===0){
                            //obj.del();
                            table.reload('adminList', {})
                        }else{
                            layer.msg("权限不足，联系超管！",{icon: 5});
                        }
                    }
                })
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            if(data.roleName==='超级管理员'){
                layer.msg("不允许操作此角色！",{icon: 5});
                return;
            }
            if(data.id===adminId){
                layer.msg("不允许编辑自己！",{icon: 5});
                return;
            }
            layui.layer.open({
                type: 2,
                title:"编辑用户",
                area: ['410px', '480px'],
                content:"editAdmin/"+data.id, //这里content是一个普通的String
                success : function(){
                    layui.layer.tips('点击此处返回管理员列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }
            })
        }
    });


    //查询
    $(".search_btn").click(function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    })

    //添加用户
    $(".adminAdd_btn").click(function(){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : "addAdmin",
            success : function(layero, index){
                layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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

    //批量删除用户
    $(".batchDel").click(function(){
        var checkStatus = table.checkStatus('adminList')
            ,data = checkStatus.data,adminStr='',flag=false,adminId=$("#adminId").val();
//	      layer.alert(JSON.stringify(data));
        if(data.length>0){
            $.each(data, function (n, value) {
                //避免选择不允许操作角色
                if(value.roleName=='超级管理员'){
                    flag=true;
                    layer.msg('"超级管理员"不允许被删除！',{icon: 5});
                    return;
                }
                if(value.id+""==adminId){
                    flag=true;
                    layer.msg('不允许删除自己！',{icon: 5});
                    return;
                }
                adminStr+=value.id+',';
            });
            //包含不允许操作角色，结束方法
            if(flag){
                return;
            }
            adminStr=adminStr.substring(0,adminStr.length-1);
            layer.confirm('真的要删除<strong>'+data.length+'</strong>条数据吗？', function(index){
                //调用删除接口
                $.ajax({
                    url:'delAdmins/'+adminStr,//接口地址
                    type : "get",
                    success : function(d){
                        if(d.code==0){
                            //删除成功，刷新父页面
                            parent.location.reload();
                        }else{
                            layer.msg("删除错误，稍后再试！",{icon: 5});
                        }
                    }
                })
            });
        }else{
            layer.msg("请选择要操作的数据！");
        }

    })

    queryRole();

})

//加载角色
function queryRole() {
    var $ = layui.jquery;
    var form = layui.form;
    $.ajax({
        type:'get',
        url:'/sys/getRole',
        dataType:'json',
        success : function (data) {
            var root = document.getElementById("role");
            for (var  i = 0; i < data.length; i++){
                var option = document.createElement("option");
                option.setAttribute("value", data[i].roleId);
                option.innerText = data[i].roleName;
                root.appendChild(option);
                form.render('select');
            }
        }
    });
}

//格式化时间
function formatTime(datetime,fmt){
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
