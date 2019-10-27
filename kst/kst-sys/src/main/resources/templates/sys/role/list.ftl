<!DOCTYPE html>
<html>
<head>
    <#import "spring.ftl" as spring />
    <title>用户管理</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${base}/static/bootstrap/css/bootstrap.min.css">
    <!-- Bootstrap table -->
    <link rel="stylesheet" href="${base}/static/plugins/bootstrap-table/css/bootstrap-table.css">
    <!-- Bootstrap select -->
    <link rel="stylesheet" href="${base}/static/plugins/bootstrap-select/css/bootstrap-select.css"/>
    <!-- toastr信息提示框插件 -->
    <link rel="stylesheet" href="${base}/static/plugins/toastr/css/toastr.css">
</head>
<body>
<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default">
        <div class="panel-heading"><@spring.message "sys.home.queryCondition" /></div>
        <div class="panel-body">
            <form class="form-inline" id="searchForm">
                <div class="form-group" style="margin-right:20px">
                    <label for="s_name"><@spring.message "sys.role.name" /></label>
                    <input type="text" class="form-control _search" name="search_like_name" >
                </div>
                <button type="button" style="margin-left:20px" onclick="reloadRoleTable();" class="btn btn-primary"><@spring.message "common.button.search" /></button>
            </form>
        </div>
    </div>

    <div id="toolbar" class="btn-group">
        <button type="button" id="addBtn" onclick="addRole();" class="btn btn-primary" role="dialog">
            <span class="glyphicon glyphicon-plus" ></span><@spring.message "sys.role.addRole" />
        </button>
        <button type="button" id="delBtn" onclick="batchRemove();"class="btn btn-danger">
            <span class="glyphicon glyphicon-remove" ></span><@spring.message "common.button.deleteInBatches" />
        </button>
    </div>

    <table id="roleTable"></table>
    <input id="handle" name="handle" value="" hidden="hidden">
</div><!-- /.panel-body -->

<!-- jQuery 3.3.1 -->
<script src="${base}/static/plugins/jQuery/jquery-3.3.1.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${base}/static/bootstrap/js/bootstrap.js"></script>
<!-- Bootstrap table -->
<script src="${base}/static/plugins/bootstrap-table/js/bootstrap-table.js"></script>
<!-- Bootstrap select -->
<script src="${base}/static/plugins/bootstrap-select/js/bootstrap-select.js"></script>
<script src="${base}/static/plugins/bootstrap-table/locale/bootstrap-table-<@spring.message "sys.message.local" />.js"></script>
<!-- toastr 信息提示框插件 -->
<script src="${base}/static/plugins/toastr/js/toastr.js"></script>
<script src="${base}/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        //初始化 toastr 弹窗位置
        toastr.options.positionClass = 'toast-center-center';

        //初始化Table
        initBootstrapTable();
    });

    //初始化Table
    function initBootstrapTable() {
        $('#roleTable').bootstrapTable({
            url: '${base}/sys/role/list',         //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            dataType: "json", // 服务器返回的数据类型
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
                debugger;
                console.log(searchParams);
                var sorts = {};
                var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    "pageSize" : params.pageSize,
                    "pageNumber" : params.pageNumber,
                    "searchParams": searchParams,
                    "sorts": sorts
                };
                return temp;
            },//传递参数（*）
            //queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求
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
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            //showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                title: '<@spring.message "sys.home.No" />',
                align: 'center',
                formatter: function (value, row, index) {
                    var pageSize = $('#roleTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#roleTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                    return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                }
            }, {
                field: 'name',
                title: '<@spring.message "sys.role.name" />'
            }, {
                field: 'remarks',
                title: '<@spring.message "sys.role.remarks" />'
            }, {
                field: 'operate',
                title: '<@spring.message "sys.user.operation" />',
                align:"center",
                formatter: operateFormatter //自定义方法，添加操作按钮
            },
            ]
            /*responseHandler: function(res) {
                if(res.state == 0){
                    return {
                        "total": res.data.total,//总页数
                        "rows": res.data.rows   //数据
                    };

                }
            }*/
        });
    };

    //操作格式自定义
    function operateFormatter(value, row, index) {//赋予的参数
        return [
            '<a href="javascript:void(0)" onclick="dt_update(\''+ row.id + '\')" class="btn btn-warning"><span class="glyphicon glyphicon-pencil" ></span><@spring.message "common.button.edit" /></a>  ',
            '<a href="javascript:void(0)" onclick="dt_remove(\''+ row.id + '\')" class="btn btn-danger"><span class="glyphicon glyphicon-remove" ></span><@spring.message "common.button.delete" /></a>  '
        ].join('');
    }

    /**
     * 打开新增框
     */
    function addRole() {
        $("#handle").attr("value",0);
        // iframe层
        layer.open({
            type: 2,
            title: '<@spring.message "sys.role.addRole"/>',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['80%', '80%'],
            content: '${base}/sys/role/add',
            btn: ['<@spring.message "common.button.confirm" />', '<@spring.message "common.button.cancel" />'],
            yes: function (index, layero) { //
            var formSubmit=layer.getChildFrame('form', index);
            var submited = formSubmit.find('#btnConfirm');
            submited.click();
        },
            end: function(){
                var handle = $("#handle").val();
                if( handle == 1){
                    toastr.success('<@spring.message "sys.role.addSuccess"/>!');
                }
            }
        });
    }

    /**
     * 打开编辑框
     * @param id
     */
    function dt_update(id) {
        $("#handle").attr("value",0);
        // iframe层
        layer.open({
            type: 2,
            title: '<@spring.message "sys.role.editRole" />',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['80%', '80%'],
            content: '${base}/sys/role/edit/'+id,
            btn: ['<@spring.message "common.button.confirm" />', '<@spring.message "common.button.cancel" />'],
            yes: function (index, layero) { //
            debugger;
            // 获取弹出层中的form表单元素
            var formSubmit=layer.getChildFrame('form', index);
            // 获取表单中的提交按钮（在我的表单里第一个button按钮就是提交按钮，使用find方法寻找即可）
            var submited = formSubmit.find('#btnConfirm');
            // 触发点击事件，会对表单进行验证，验证成功则提交表单，失败则返回错误信息
            submited.click();
            //layer.close(index);//这块是点击确定关闭这个弹出层
        },
            end: function(){
                var handle = $("#handle").val();
                if( handle == 2){
                    toastr.success('<@spring.message "sys.role.editSuccess" />');
                }
            }
        });
    }

    /**
     * 删除某条记录
     * @param url
     * @param id
     */
    function dt_remove(id) {
        layer.confirm('<@spring.message "sys.user.message.isDelete" />', {icon: 3, title:'<@spring.message "sys.message.warning" />'}, function (index) {
            $.ajax({
                url: '${base}/sys/role/delete',
                type: "POST",
                data: {
                    'id': id
                },
                success: function (result) {
                    if(result.success){
                        $('button[name="refresh"]').click();
                        toastr.success('<@spring.message "sys.message.deleteSuccess"/>');
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
    function batchRemove() {
        var rows = $('#roleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
        if (rows.length == 0) {
            toastr.warning("<@spring.message "sys.message.choiceDelete" />");
            return;
        }
        //var ids = '';
        //遍历所有选择的行数据，取每条数据对应的ID
        /*$.each(rows, function (i, row) {
            if(row['id'] === 1){
                layer.msg("不能删除超级管理员");
                return false;
            }
        });*/

        layer.confirm("<@spring.message "sys.user.message.isDelete" />", {icon: 3, title:'<@spring.message "sys.message.warning" />'}, function (index) {
            var deleteindex = layer.msg('<@spring.message "sys.message.inDelete" />',{icon: 16,time:false,shade:0.8});
            $.ajax({
                url: '${base}/sys/role/deleteSome',
                type: "POST",
                dataType:"json",
                contentType:"application/json",
                data: JSON.stringify(rows),
                success: function (result) {
                    layer.close(deleteindex);
                    if(result.success){
                        $('button[name="refresh"]').click();
                        toastr.success('<@spring.message "sys.message.deleteSuccess" />');
                        layer.close(index);
                    }else{
                        console.log(result.message);
                        toastr.error(result.message);
                        layer.close(index);
                    }
                }
            });
        })
    }

    /**
     * 重新加载表
     */
    function reloadRoleTable() {
        $('#roleTable').bootstrapTable('refresh');
    }


</script>
</body>
</html>