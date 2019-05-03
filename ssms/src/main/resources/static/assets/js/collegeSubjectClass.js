/*
* 学院collegeId、专业subjectId、班级classId下拉框操作
*/

//渲染学院下拉框
function renderCollegeList() {
    $('#collegeId').html('<option value="">请选择学院</option>');
    getCollegeSubjectClass($('#collegeId'),0);
}
//渲染专业下拉框
function renderSubjectList(collegeId) {
    $('#subjectId').html('<option value="">请选择专业</option>');
    getCollegeSubjectClass($('#subjectId'),collegeId);
}
//渲染班级下拉框
function renderClassList(subjectId) {
    $('#classId').html('<option value="">请选择班级</option>');
    getCollegeSubjectClass($('#classId'),subjectId);
}
// 渲染select,获取列表信息
function getCollegeSubjectClass($select,parentId) {
    $.ajax({
        type: 'get',
        url: '/collegeSubjectClass/'+parentId,
        async: false,
        success: function (res) {
            var dataList = res.data.list;
            $.each(dataList, function (key, val) {
                var option = $("<option>").val(val.id).text(
                    val.name);
                $select.append(option);
            });
            form.render('select');
        }
    });
}
//学院下拉框回显
function selectCollege(collegeId) {
    $.each($('#collegeId option'),function () {
        if($(this).val() == collegeId){
            $(this).attr("selected", true);
            return false;
        }
    });
}
//专业下拉框回显
function selectSubject(subjectId) {
    $.each($('#subjectId option'),function () {
        if($(this).val() == subjectId){
            $(this).attr("selected", true);
            return false;
        }
    });
}
//班级下拉框回显
function selectClass(classId) {
    $.each($('#classId option'),function () {
        if($(this).val() == classId){
            $(this).attr("selected", true);
            return false;
        }
    });
}