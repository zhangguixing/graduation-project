/*
* 学院collegeId、专业subjectId、班级classId下拉框操作
*/

layui.define(["layer", "jquery", "form"], function (exports) {
    var form = layui.form;
    var $ = layui.jquery;

    var obj = {
        //渲染年级下拉框
        renderGradeList: function () {
            $('#gradeId').html('<option value="">请选择年级</option>');
            $.ajax({
                url: '/grade/list',
                type: 'GET',
                async: false,
                success: function (res) {
                    if (res.code == 200) {
                        $.each(res.data, function (index, grade) {
                            $('#gradeId').append($('<option>').val(grade.id).text(grade.name))
                        })
                        form.render("select");
                    }
                }
            });
        },
        //渲染学院下拉框
        renderCollegeList: function () {
            $('#collegeId').html('<option value="" selected="selected">请选择学院</option>');
            $('#subjectId').html('<option value="" selected="selected">请选择专业</option>');
            $('#classId').html('<option value="" selected="selected">请选择班级</option>');
            this.getCollegeSubjectClass($('#collegeId'), 0);
        },
        //渲染专业下拉框
        renderSubjectList: function (collegeId) {
            $('#subjectId').html('<option value="" selected="selected">请选择专业</option>');
            $('#classId').html('<option value="" selected="selected">请选择班级</option>');
            if (collegeId != undefined && collegeId != "") {
                this.getCollegeSubjectClass($('#subjectId'), collegeId);
            }
        },
        //渲染班级下拉框
        renderClassList: function (subjectId) {
            $('#classId').html('<option value="" selected="selected">请选择班级</option>');
            if (subjectId != undefined && subjectId != "") {
                this.getCollegeSubjectClass($('#classId'), subjectId);
            }
        },
        //渲染学年下拉框
        renderSchoolYearByUser: function () {
            var gradeName = null;
            $.ajax({
                url: '/grade/personGrade',
                type: 'GET',
                async: false,
                success: function (res) {
                    if (res.code == 200) {
                        if (res.data != null) {
                            gradeName = res.data;
                        }
                    }
                }
            });
            if (gradeName != null && gradeName != undefined) {
                this.renderSchoolYearList(gradeName);
                form.render('select');
            }
        },
        //渲染学年下拉框
        renderSchoolYearList: function (gradeName) {
            $('#schoolYearId').html('<option value="">请选择学年</option>');
            var grade = parseInt(gradeName.substring(0, gradeName.length - 1));
            var year = new Date().getFullYear();//获取当前年
            for (var i = grade; i < year && i - grade < 4; i++) {
                var schoolYear = (i) + '-' + (i + 1);
                $('#schoolYearId').append('<option value="' + schoolYear + '">' + schoolYear + '</option>')
            }
            form.render('select');
        },
        // 渲染select,获取列表信息
        getCollegeSubjectClass: function ($select, parentId) {
            $.ajax({
                type: 'get',
                url: '/system/college/getCollegeSubjectClassByParentId/' + parentId,
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
        selectCollege: function (collegeId) {
            $.each($('#collegeId option'), function () {
                if ($(this).val() == collegeId) {
                    $(this).attr("selected", true);
                    return false;
                }
            });
        },
        //专业下拉框回显
        selectSubject: function (subjectId) {
            $.each($('#subjectId option'), function () {
                if ($(this).val() == subjectId) {
                    $(this).attr("selected", true);
                    return false;
                }
            });
        },
        //班级下拉框回显
        selectClass: function (classId) {
            $.each($('#classId option'), function () {
                if ($(this).val() == classId) {
                    $(this).attr("selected", true);
                    return false;
                }
            });
        },
        //班级下拉框回显
        selectGrade: function (gradeId) {
            $.each($('#gradeId option'), function () {
                if ($(this).val() == gradeId) {
                    $(this).attr("selected", true);
                    return false;
                }
            });
        },
        //学年下拉框回显
        selectSchoolYear: function (schoolYear) {
            $.each($('#schoolYearId option'), function () {
                if ($(this).val() == schoolYear) {
                    $(this).attr("selected", true);
                    return false;
                }
            });
        },
        //学期下拉框回显
        selectSemester: function (semester) {
            $.each($('#semesterId option'), function () {
                if ($(this).val() == semester) {
                    $(this).attr("selected", true);
                    return false;
                }
            });
        },
        //回显学院、专业、班级、年级
        reshowAll: function (collegeId, subjectId, classId, gradeId, schoolYear, semester, isDisabled) {
            //回显学院
            this.selectCollege(collegeId);
            this.renderSubjectList(collegeId);
            $('#subjectDiv').show();
            //回显专业
            this.selectSubject(subjectId);
            this.renderClassList(subjectId);
            $('#classDiv').show();
            if (classId != null) {
                //回显班级
                this.selectClass(classId);
                this.renderGradeList(classId);
                $('#gradeDiv').show();
                if (gradeId != null) {
                    //回显年级
                    this.selectGrade(gradeId);
                    var gradeName = $('#gradeId option:selected').text();
                    this.renderSchoolYearList(gradeName);
                    $('#schoolYearDiv').show();
                    if (schoolYear != null) {
                        //回显学年
                        this.selectSchoolYear(schoolYear);
                        $('#semesterDiv').show();
                        if (semester != null) {
                            //回显学期
                            this.selectSemester(semester);
                        }
                    }
                }
            }
            //设置不可用
            if (isDisabled == true) {
                $('#collegeId').attr("disabled", true);
                $('#subjectId').attr("disabled", true);
                if (classId != null) {
                    $('#classId').attr("disabled", true);
                    if (gradeId != null) {
                        $('#gradeId').attr("disabled", true);
                        if (schoolYear != null) {
                            $('#schoolYearId').attr("disabled", true);
                            if (semester != null) {
                                $('#semesterId').attr("disabled", true);
                            }
                        }
                    }
                }
            }
            form.render('select');
        }
    }
    exports("collegeSelect", obj);
});