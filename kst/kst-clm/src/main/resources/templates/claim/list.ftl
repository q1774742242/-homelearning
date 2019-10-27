<!DOCTYPE html>
<html lang="en">
<head>
    <#import "spring.ftl" as spring>
    <meta charset="UTF-8">
    <title>报销单申请管理</title>
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
        <div class="panel-heading"><@spring.message "claim.condition.query" /></div>
        <div class="panel-body">
            <form class="form-inline" id="searchForm">
                <div class="form-group" style="margin-right:20px"><#--search_eq_qus_title-->
                    <label for="s_type"><@spring.message "claim.applyUser" /></label>
                    <input type="text" class="form-control _search" name="qusTitle" >
                </div>
                <div class="form-group" style="margin-right:20px">
                    <label for="s_label"><@spring.message "claim.status" /></label>
                    <select id="search_level" name="qusFlag" class="form-control selectpicker _search" title="<@spring.message "claim.message.choice" />" data-width="200px">
                        <option value="" ><@spring.message "claim.message.choice" /></option>
                        <#list questionFlag as type>
                            <option value="${type.value}">${type.label}
                            </option>
                        </#list>
                    </select>
                </div>
                <div class="form-group" style="margin-right:10px"><!--部门-->
                    <label for="s_name"><@spring.message "claim.depatment" /></label>
                    <select id="search_level" name="qusStatus" class="form-control selectpicker _search" title="<@spring.message "claim.message.choice" />" data-width="200px">
                        <option value="" ><@spring.message "claim.message.choice" /></option>
                        <#list questionStatus as type>
                            <option value="${type.value}" >${type.label}</option>
                        </#list>
                    </select>
                </div>

                <div class="form-group" style="margin-right:20px"><!--申请状态-->
                    <input type="radio" name="qusPusher" id="qusPusher" value="${loginName}"><label for="qusPusher"><@spring.message "claim.status.loading" /></label>
                    &nbsp;
                    <input type="radio" name="qusRequester" id="qusRequester" value="${loginName}"><label for="qusRequester"><@spring.message "claim.status.pass" /></label>
                </div>

                <button type="button" style="margin-left:20px" onclick="re_load();" class="btn btn-primary"><@spring.message "claim.button.search" /></button>
            </form>
        </div>
    </div>
    <div id="toolbar" class="btn-group">
        <button type="button" id="addBtn" onclick="addQuestion();" class="btn btn-primary" role="dialog"><@spring.message "claim.add" /></button>
        <button type="button" id="delBtn" onclick="questionRemove();"class="btn btn-danger"><@spring.message "claim.button.deleterecord" /></button>
    </div>
    <table id="questionTable"></table>
    <input id="handle" name="handle" value="" hidden="hidden">
</div>
<script src="${base}/static/plugins/jQuery/jquery-3.3.1.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${base}/static/bootstrap/js/bootstrap.js"></script>
<!-- Bootstrap table -->
<script src="${base}/static/plugins/bootstrap-table/js/bootstrap-table.js"></script>
<!-- Bootstrap select -->
<script src="${base}/static/plugins/bootstrap-select/js/bootstrap-select.js"></script>
<script src="${base}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<!-- toastr 信息提示框插件 -->
<script src="${base}/static/plugins/toastr/js/toastr.js"></script>
<script src="${base}/static/plugins/layer/layer.js"></script>
<script src="${base}/static/plugins/iCheck/icheck.min.js"></script>
<script src="${base}/static/plugins/timepicker/bootstrap-datetimepicker.min.js"></script>
<script src="${base}/static/plugins/timepicker/bootstrap-datetimepicker.zh-CN.js">
    <script src="${base}/static/js/Claim.js"></script>
</body>
</html>