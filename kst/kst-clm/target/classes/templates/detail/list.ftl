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
    <!--jquery地址&ndash;&gt;
    <script src="${base}static/jquery/jquery-3.4.1.min.js"/>
    <!--引用layui地址-->
    <script src="${base}static/layui/layui.js"/>


</head>
<body>
<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default">
        <div class="panel-heading"><@spring.message "sys.condition.query" /></div>
        <div class="panel-body">
            <form class="form-inline" id="searchForm">
                <div class="form-group" style="margin-right:20px"><#--search_eq_qus_title-->
                    <label for="s_type"><@spring.message "sys.question.title" /></label>
                    <input type="text" class="form-control _search" name="qusTitle" >
                </div>
                <div class="form-group" style="margin-right:20px">
                    <label for="s_label"><@spring.message "sys.question.flag" /></label>
                    <select id="search_level" name="qusFlag" class="form-control selectpicker _search" title="<@spring.message "sys.message.choice" />" data-width="200px">
                        <option value="" ><@spring.message "sys.message.choice" /></option>
                        <#list questionFlag as type>
                            <option value="${type.value}">${type.label}
                            </option>
                        </#list>
                    </select>
                </div>
                <div class="form-group" style="margin-right:10px">
                    <label for="s_name"><@spring.message "sys.question.status" /></label>
                    <select id="search_level" name="qusStatus" class="form-control selectpicker _search" title="<@spring.message "sys.message.choice" />" data-width="200px">
                        <option value="" ><@spring.message "sys.message.choice" /></option>
                        <#list questionStatus as type>
                            <option value="${type.value}" >${type.label}</option>
                        </#list>
                    </select>
                </div>

                <div class="form-group" style="margin-right:20px">
                    <input type="checkbox" name="qusPusher" id="qusPusher" value="${loginName}"><label for="qusPusher"><@spring.message "sys.question.raise" /></label>
                    &nbsp;
                    <input type="checkbox" name="qusRequester" id="qusRequester" value="${loginName}"><label for="qusRequester"><@spring.message "sys.questions.answer" /></label>
                </div>

                <button type="button" style="margin-left:20px" onclick="re_load();" class="btn btn-primary"><@spring.message "common.button.search" /></button>
            </form>
        </div>
    </div>
    <div id="toolbar" class="btn-group">
        <button type="button" id="addBtn" onclick="addQuestion();" class="btn btn-primary" role="dialog"><@spring.message "common.button.add" /></button>
        <button type="button" id="delBtn" onclick="questionRemove();"class="btn btn-danger"><@spring.message "common.button.deleteInBatches" /></button>
    </div>
    <table id="questionTable"></table>
    <input id="handle" name="handle" value="" hidden="hidden">
</div>

</body>
</html>