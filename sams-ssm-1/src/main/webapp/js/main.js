layui.config({
    base : "js/"
}).use(['form','element','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element,
        $ = layui.jquery;

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('info'));
    myChart.showLoading({
        text: '正在努力加载中...'
    });

    var categories = [];
    var values = [];

    // 同步执行
    $.ajaxSettings.async = false;

    // 加载数据
    $.get('../main/dataAccessGender', function (json) {
        categories=json.categories;
        values = json.values;
    });
    option = {
        title : {
            text: '学生性别占比',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: categories
        },
        series : [
            {
                name: '学生',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:values,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.hideLoading();


    //用户数
    $.get("../main/getAdminTotal",
        function(data){
            $(".userTotal").text(data.length);
        }
    )

    //学生数
    $.get("../main/getStudentTotal",
        function(data){
            $(".studentTotal").text(data.length);
        }
    )

    //老师数
    $.get("../main/getTeacherTotal",
        function(data){
            $(".teacherTotal").text(data.length);
        }
    );

    //系统基本参数
    if(window.sessionStorage.getItem("systemParameter")){
        var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
        fillParameter(systemParameter);
    }else{
        $.ajax({
            url : "../json/systemParameter.json",
            type : "get",
            dataType : "json",
            success : function(data){
                fillParameter(data);
            }
        })
    }

    //填充数据方法
    function fillParameter(data){
        //判断字段数据是否存在
        function nullData(data){
            if(data == '' || data == "undefined"){
                return "未定义";
            }else{
                return data;
            }
        }
        $(".version").text(nullData(data.version));      //当前版本
        $(".author").text(nullData(data.author));        //开发作者
        $(".homePage").text(nullData(data.homePage));    //网站首页
        $(".server").text(nullData(data.server));        //服务器环境
        $(".dataBase").text(nullData(data.dataBase));    //数据库版本
        $(".maxUpload").text(nullData(data.maxUpload));    //最大上传限制
    }

})


