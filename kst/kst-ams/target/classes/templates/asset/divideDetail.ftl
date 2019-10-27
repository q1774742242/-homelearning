<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>资产分摊详情</title>
    <meta name="viewport" content="width=device-width"/>
    <link rel="stylesheet" href="${base}/static/bootstrap/css/bootstrap.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="${base}/static/plugins/iCheck/all.css">
    <link rel="stylesheet" href="${base}/static/plugins/bootstrap-table/css/bootstrap-table.css">
    <!-- toastr信息提示框插件 -->
    <link rel="stylesheet" href="${base}/static/plugins/toastr/css/toastr.css">
    <link rel="stylesheet" href="${base}/static/AdminLTE/css/AdminLTE.css">

    <style>
        .form-group {
            height: 55px;
        }
    </style>
</head>
<body>

<!-- jQuery 3.3.1 -->
<script src="${base}/static/plugins/jQuery/jquery-3.3.1.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${base}/static/bootstrap/js/bootstrap.js"></script>
<script src="${base}/static/plugins/bootstrap-table/js/bootstrap-table.js"></script>
<script src="${base}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${base}/static/plugins/iCheck/icheck.min.js"></script>
<script src="${base}/static/plugins/table-export/tableExport.js"></script>
<!-- Bootstrap Validator -->
<script src="${base}/static/plugins/bootstrapvalidator-master/bootstrapValidator.min.js"></script>
<!-- toastr 信息提示框插件 -->
<script src="${base}/static/plugins/toastr/js/toastr.js"></script>

<script src="${base}/static/plugins/layer/layer.js"></script>

<div id="toolbar">

</div>
<div class="box box-info" style="margin-bottom:0px;">
    <table class="table">
        <thead>
        <tr>
            <th style="text-align: center">购买日期</th>
            <th style="text-align: center">资产编号</th>
            <th style="text-align: center">资产名</th>
            <th style="text-align: center">数量</th>
            <th style="text-align: center">单价</th>
            <th style="text-align: center">年限</th>
        </tr>
        </thead>
        <tbody>
        <tr style="text-align: center">
            <td>${assetInfo.buyDate?string('yyyy-MM-dd')}</td>
            <td>${assetInfo.assetInputNo}</td>
            <td>${assetInfo.name}</td>
            <td>${assetInfo.amount}</td>
            <td>${assetInfo.price}</td>
            <td>${assetInfo.useLife}</td>
        </tr>
        </tbody>
    </table>
    <table id="assetTable" style="text-align: center" class="table">
    </table>
</div>

<script type="text/javascript">
    $(function () {
        initBootstrapTable()
    });

    //加载table
    function initBootstrapTable() {
        var assetNo = "${assetInfo.assetInputNo}"
        $('#assetTable').bootstrapTable({
                url: '${base}/ams/asset/divideDetail/' + assetNo,         //请求后台的URL（*）
                method: 'post',                      //请求方式（*）
                striped: true,                      //是否显示行间隔色
                dataType: "json",                   // 服务器返回的数据类型
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: false,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParamsType: "",               //查询参数类型，默认是'limit'，注意：如果想获取页面偏移量offset和页面大小pageSize，参数queryParamsType可不配
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                showColumns: false,                  //是否显示所有的列
                showRefresh: false,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                //showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                toorbar: "#toorbar",
                columns: [{
                    title: '序号',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var pageSize = $('#assetTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#assetTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                    }
                }, {
                    field: 'divideYymm',
                    title: '日期',
                    align: 'center',
                    formatter:function (value) {
                        return value.substring(0,4)+"-"+value.substring(4,6);
                    }
                }, {
                    field: 'monthProvision',
                    title: '每月计提',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return value.toFixed(2).toLocaleString();
                    }
                }, {
                    field: 'provisionValue',
                    title: '本月计提',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return value.toFixed(2).toLocaleString();
                    }
                }, {
                    field: 'provisionAllValue',
                    title: '已提折旧',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return value.toFixed(2).toLocaleString();
                    }
                }, {
                    field: 'balanceValue',
                    title: '账面余额',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return value.toFixed(2).toLocaleString();
                    }
                }]
            }
        );
    }

</script>
</body>
</html>