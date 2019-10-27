$(function(){
    $('input[name="qusPusher"]').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });

    $('input[name="qusRequester"]').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });
    toastr.options.positionClass = 'toast-center-center';
    initBootstrapTable();

});

function initBootstrapTable() {

    $('#questionTable').bootstrapTable({
        url: '${base}/clm/claim/list',         //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        dataType: "json", // 服务器返回的数据类型
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        //contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        queryParams: function (params) {
            var searchParams = {};
            $("#searchForm ._search").each(function () {
                if ("" != $(this).val()) {
                    searchParams[$(this).attr('name')] = $(this).val();
                }
            });

            $('input[name="qusPusher"]:checked').each(function () {
                searchParams['qusPusher']=($(this).val());
            });

            $('input[name="qusRequester"]:checked').each(function () {
                searchParams['qusRequester']=($(this).val());
            });
            searchParams["startNo"] = (params.pageNumber - 1) * params.pageSize;
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                "pageSize": params.pageSize,
                "pageNumber": params.pageNumber,
                "searchParams": searchParams
            };
            return temp;
        },//传递参数（*）
        queryParamsType : "",               //查询参数类型，默认是'limit'，注意：如果想获取页面偏移量offset和页面大小pageSize，参数queryParamsType可不配
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "cid",                     //每一行的唯一标识，一般为主键列
        //showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [{
            checkbox: true
        }, {
            title: '<@spring.message "sys.home.No"/>',
            align: 'center',
            formatter: function (value, row, index) {
                var pageSize = $('#questionTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#questionTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        }, {
            field: 'cid',
            title: 'cid',
            visible: false
        },{
            field: 'nick_name',
            title: '<@spring.message "claim.niclname" />',
            visible: false
        },{
            field: 'name',
            title: '<@spring.message "claim.depatment" />',

        }, {
            field: 'clm_applydate',
            title: '<@spring.message "claim.time" />',//申请时间
            align:"center"
        }, {
            field: 'update_date',
            title: '<@spring.message "clm.updatetime" />',//修改时间
            align:"center"
        },{
            field: 'clm_status',
            title: '<@spring.message "claim.status" />'//状态
        },{
            field: 'remarks',
            title: '<@spring.message "claim.remark" />',
            align:"center"
        },{
            title: '<@spring.message "claim.operation" />',
            align:"center",
            formatter: operateFormatter //自定义方法，添加操作按钮

        },
        ]

    });
}


//操作格式自定义
function operateFormatter(value, row, index) { //赋予的参数
    if(row.qusStatus==0){
        return [
            '<a href="javascript:void(0)" onclick="dt_updateQuestion(\''+ row.id + '\',0)" class="btn btn-warning"><span class="glyphicon glyphicon-pencil" ></span><@spring.message "sys.quizzer.update" /></a>  ',
            '<a href="javascript:void(0)" onclick="dt_updateQuestion(\''+ row.id + '\',1)" class="btn btn-warning"><span class="glyphicon glyphicon-pencil" ></span><@spring.message "sys.question.answer" /></a>',
            '<a href="javascript:void(0)" onclick="dt_remove(\''+ row.id + '\')" class="btn btn-danger"><span class="glyphicon glyphicon-remove" ></span><@spring.message "sys.delete" /></a>  '

        ].join('');
    }else{
        return [
            '<a href="javascript:void(0)" onclick="dt_updateQuestion(\''+ row.id + '\',1)" class="btn btn-warning"><span class="glyphicon glyphicon-pencil" ></span><@spring.message "sys.question.answer" /></a> '
        ].join('');
    }

}//未改的


//增加页面
function addQuestion() {
    layer.open({
        type: 2,
        title: '<@spring.message "claim.apply.add" />',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['100%', '100%'],
        content: '${base}/sys/question/add',
        btn: ['<@spring.message "common.button.confirm" />', '<@spring.message "common.button.cancel" />'],
        yes: function (index, layero) {
            var formSubmit=layer.getChildFrame('form', index);
            var submited = formSubmit.find('#btnConfirm');
            submited.click();
        },
        end: function(){
            var handle = $("#handle").val();
            if( handle == 1){
                re_load();
                toastr.success('<@spring.message "sys.message.addSuccess" />');

            }
        }
    });
}

//修改页面
function dt_updateQuestion(id,da) {
    layer.open({
        type: 2,
        title: '<@spring.message "sys.question.update" />',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['100%', '100%'],
        content: '${base}/sys/question/edit?id='+id+'&da='+da,
        btn: ['<@spring.message "common.button.confirm" />', '<@spring.message "claim.apply.update" />'],
        yes: function (index, layero) {
            // 获取弹出层中的form表单元素
            var formSubmit=layer.getChildFrame('form', index);
            // 获取表单中的提交按钮
            var submited = formSubmit.find('#btnConfirm');
            submited.click();
            toastr.success('<@spring.message "sys.msg.editSuccess" />!');
        },
        end: function(){
            var handle = $("#handle").val();
            if( handle == 2){
                re_load();
            }
        }
    });
}

//删除操作
function dt_remove(id) {
    layer.confirm('<@spring.message "sys.user.message.isDelete" />', {icon: 3, title:'<@spring.message "sys.message.warning" />'}, function (index) {
        $.ajax({
            url: '${base}/sys/question/deleteQus/'+id,
            type: "POST",
            data: {
                'id': id
            },
            success: function (result) {
                if(result.success){
                    $('button[name="refresh"]').click();
                    re_load();
                    toastr.success('<@spring.message "sys.message.deleteSuccess" />');
                    layer.close(index);
                }else{
                    toastr.error(result.message);
                    layer.close(index);
                }
            }
        });
    })
}


/**
 * 批量删除
 */
function questionRemove() {
    var rows = $('#questionTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        toastr.warning("<@spring.message "claim.message.choiceDelete" />!");
        return;
    }

        var deleteindex = layer.msg('<@sprin    layer.confirm("<@spring.message "claim.user.message.isDel" />?", {icon: 3, title:\'<@spring.message "claim.message.warning" />\'}, function (index) {\ng.message "claim.message.inDelete" />',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url: '${base}/sys/question/deleteQusSome',
            type: "POST",
            dataType:"json",
            contentType:"application/json",
            data: JSON.stringify(rows),
            success: function (result) {
                layer.close(deleteindex);
                if(result.success){
                    $('button[name="refresh"]').click();
                    re_load();
                    toastr.success('<@spring.message "claim.message.deleteSuccess" />');
                    layer.close(index);
                }else{
                    console.log(result.message);
                    toastr.error(result.message);
                    layer.close(index);
                }
            }
        });
    }

/**
 * 重新加载表
 */
function re_load() {
    $('#questionTable').bootstrapTable('refresh');
}
//checkbox属性
