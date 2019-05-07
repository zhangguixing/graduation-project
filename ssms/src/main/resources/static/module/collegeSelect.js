/*
* 学院collegeId、专业subjectId、班级classId下拉框操作
*/

layui.define(["layer","jquery","form"],function (exports) {
    var form = layui.form;
    var $ = layui.jquery;

    var obj = {
        //渲染年级下拉框
        renderGradeList:function() {
            $('#gradeId').html('<option value="">请选择年级</option>');
            $.ajax({
                url:'/grade/list',
                type:'GET',
                success:function(res){
                    if(res.code == 200){
                        $.each(res.data,function (index, grade) {
                            $('#gradeId').append($('<option></option>').val(grade.id).text(grade.name))
                        })
                        form.render("select");
                    }
                }
            });
        },
        //渲染学院下拉框
        renderCollegeList:function() {
            $('#collegeId').html('<option value="" selected="selected">请选择学院</option>');
            $('#subjectId').html('<option value="" selected="selected">请选择专业</option>');
            $('#classId').html('<option value="" selected="selected">请选择班级</option>');
            this.getCollegeSubjectClass($('#collegeId'),0);
        },
        //渲染专业下拉框
        renderSubjectList:function(collegeId) {
            $('#subjectId').html('<option value="" selected="selected">请选择专业</option>');
            $('#classId').html('<option value="" selected="selected">请选择班级</option>');
            if(collegeId!=""){
                this.getCollegeSubjectClass($('#subjectId'),collegeId);
            }
        },
        //渲染班级下拉框
        renderClassList:function(subjectId) {
            $('#classId').html('<option value="" selected="selected">请选择班级</option>');
            if(subjectId!=""){
                this.getCollegeSubjectClass($('#classId'),subjectId);
            }
        },
        //渲染学年下拉框
        renderSchoolYearByUser:function(){
            var gradeName = null;
            $.ajax({
                url:'/grade/personGrade',
                type:'GET',
                async:false,
                success:function (res) {
                    if(res.code == 200){
                        if(res.data != null){
                            gradeName = res.data;
                        }
                    }
                }
            });
            if(gradeName != null){
                this.renderSchoolYearList(gradeName);
                form.render('select');
            }
        },
        //渲染学年下拉框
        renderSchoolYearList:function(gradeName){
            $('#schoolYearId').html('<option value="">请选择学年</option>');
            var grade = parseInt(gradeName.substring(0,gradeName.length-1));
            var year = new Date().getFullYear();//获取当前年
            for (var i=grade;i<year&&i-grade<4;i++){
                var schoolYear = (i)+'-'+(i+1);
                $('#schoolYearId').append('<option value="'+schoolYear+'">'+schoolYear+'</option>')
            }
            form.render('select');
        },
        // 渲染select,获取列表信息
        getCollegeSubjectClass:function($select,parentId) {
            $.ajax({
                type: 'get',
                url: '/system/college/getCollegeSubjectClassByParentId/'+parentId,
                async: false,
                success: function (res) {
                    var dataList = res.data;
                    $.each(dataList, function (key, val) {
                        var option = $("<option>").val(val.id).text(
                            val.name);
                        $select.append(option);
                    });
                    form.render('select');
                }
            });
        },
        //学院下拉框回显
        selectCollege:function(collegeId) {
            $.each($('#collegeId option'),function () {
                if($(this).val() == collegeId){
                    $(this).attr("selected", true);
                    return false;
                }
            });
        },
        //专业下拉框回显
        selectSubject:function(subjectId) {
            $.each($('#subjectId option'),function () {
                if($(this).val() == subjectId){
                    $(this).attr("selected", true);
                    return false;
                }
            });
        },
        //班级下拉框回显
        selectClass:function(classId) {
            $.each($('#classId option'),function () {
                if($(this).val() == classId){
                    $(this).attr("selected", true);
                    return false;
                }
            });
        }
        //获取用户列表
        // getUserList:function(collegeId,subjectId,classId,personType){
        //     var data;
        //     $.ajax({
        //         url:'/system/user/view',
        //         type:'GET',
        //         async:false,
        //         success:function(res){
        //             if(res.code==200){
        //                 data = res.data;
        //             }
        //         }
        //     });
        //     return data;
        // }
    }
    exports("collegeSelect",obj);
});