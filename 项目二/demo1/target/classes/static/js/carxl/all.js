$(function(){
	getAllByPage();
	myadd();
	
	
});
function getAllByPage()
{
      $('#tb_departments').bootstrapTable({
            url:'/carxl.do?method=all',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            dataType:'json',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            pagination: true,                   //是否显示分页（*）
            
            
            queryParams: function (params) {
                var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit : params.limit,   //页面大小(每一页要显示多少条)
                offset: params.offset  //从数据库第几条记录开始 
                };
             return temp;
            },//传递参数（*）
            
            
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5,10, 25, 50, 100],       //可供选择的每页的行数（*）
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            
            height: $(window).height()-100,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "xid",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'xid',
                title: '类别编号'
            }, {
                field: 'aname',
                title: '汽车类别名称'
            }, 
            {
                field: 'xname',
                title: '汽车系列名称'
            }, 
             {
            	 title : '操作',  
                 field : 'xid',  
                 align : 'center',
                 formatter : function(value, row, index) {  
                	 var e = '<a href="#"  onclick="edit(\''+ value + '\')">编辑</a> ';  
                     var d = '<a href="#"  onclick="del(\''+ value + '\')">删除</a> ';  
                    return e + d;
                  }
             }
            ]});
}

function edit(obj)
{
	 layer.open({
			type : 2,
			title : '修改',
			fix : false,
			shadeClose : true,
			maxmin : true,
			area : [ '50%', '500px' ],
			content :  "/carxl.do?method=myone&xid="+obj,
			skin : 'layui-layer-lan',
			shift : 4, // 0-6的动画形式，-1不开启
			end : function() {
				 window.location.reload();
				getAllByPage();
			}
		});
}



function myadd()
{
	
	$("#btn_add").click(function(){
		layer.open({
			type : 2,
			title : '增加',
			fix : false,
			shadeClose : true,
			maxmin : true,
			area : [ '50%', '500px' ],
			content :  "/carxl.do?method=myadd",
			skin : 'layui-layer-grey',
			shift : 4, // 0-6的动画形式，-1不开启
			end : function() {
				 window.location.reload();
				getAllByPage();
			}
		});
	});
	
}
