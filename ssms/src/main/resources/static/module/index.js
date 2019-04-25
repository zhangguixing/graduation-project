/** EasyWeb iframe v3.0.1 data:2018-10-17 */
layui.define(['layer', 'admin', 'element'], function (exports) {
    var $ = layui.jquery;
    var layer = layui.layer;
    var admin = layui.admin;
    var element = layui.element;

    var index = {
        // 加载主体部分
        loadView: function (param) {
            var menuPath = param.menuPath;
            var menuName = param.menuName;

            if (!menuPath) {
                console.error('url不能为空');
                return;
            }

            // 判断选项卡是否已添加
            var flag = false;
            $('.layui-layout-admin .layui-body .layui-tab .layui-tab-title>li').each(function () {
                if ($(this).attr('lay-id') === menuPath) {
                    flag = true;
                    return false;
                }
            });
            // 没有则添加
            if (!flag) {
                element.tabAdd('admin-pagetabs', {
                    id: menuPath,
                    title: menuName ? menuName : '无标题',
                    content: '<iframe src="' + menuPath + '" frameborder="0" class="admin-iframe"></iframe>'
                });
            }
            // 切换到该选项卡
            element.tabChange('admin-pagetabs', menuPath);
            // 移动设备切换页面隐藏侧导航
            if (document.body.clientWidth <= 750) {
                admin.flexible(true);
            }
        },
        // 打开新页面
        openTab: function (param) {
            var url = param.url;
            var title = param.title;

            index.loadView({
                menuPath: url,
                menuName: title
            });
        },
        // 关闭选项卡
        closeTab: function (url) {
            element.tabDelete('admin-pagetabs', url);
        }
    };

    // 监听侧导航栏点击事件
    element.on('nav(admin-side-nav)', function (elem) {
        var $that = $(elem);
        var menuUrl = $that.attr('lay-href');
        if (menuUrl && menuUrl != 'javascript:;') {
            var menuName = $that.text();
            index.loadView({
                menuPath: menuUrl,
                menuName: menuName
            });
        }
    });

    // tab选项卡切换监听
    element.on('tab(admin-pagetabs)', function (data) {
        var layId = $(this).attr('lay-id');

        admin.rollPage('auto');  // 自动滚动
        admin.activeNav(layId);  // 设置导航栏选中
        $('.layui-table-tips-c').trigger('click');  // 切换tab关闭表格内浮窗

        // 解决切换tab滚动条时而消失的问题
        var $iframe = $('.layui-layout-admin .layui-body .layui-tab-content .layui-tab-item.layui-show .admin-iframe')[0];
        if ($iframe) {
            $iframe.style.height = "99%";
            $iframe.scrollWidth;
            $iframe.style.height = "100%";
        }
    });

    exports('index', index);
});
