// 加载admin、index模块，设置index.html中元素事件
layui.use(['layer', 'element', 'admin', 'index'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var admin = layui.admin;
    var index = layui.index;

    // 默认加载第一个菜单
    index.loadView({
        menuPath: $('.layui-side .layui-nav .layui-nav-item a[lay-href!="javascript:;"]:first').attr('lay-href'),
        menuName: '<i class="layui-icon layui-icon-home"></i>'
    });

    // 消息点击事件
    $('#btnMessage').click(function () {
        admin.popupRight({
            type: 2,
            content: 'tpl/message'
        });
    });

    // 修改密码点击事件
    $('#setPsw').click(function () {
        admin.open({
            type: 2,
            title: '修改密码',
            area: ['380px', '290px'],
            content: 'tpl/password'
        });
    });

    // 本地便签点击事件
    $('#btnNote').click(function () {
        admin.popupRight({
            id: 'layer-note',
            type: 2,
            title: '本地便签',
            closeBtn: false,
            content: 'tpl/note'
        });
    });

    // 退出登录点击事件
    $('#btnLogout').click(function () {
        top.layer.confirm('确定退出登录？', {
            skin: 'layui-layer-admin'
        }, function () {
            location.replace('/logout');
        });
    });
});