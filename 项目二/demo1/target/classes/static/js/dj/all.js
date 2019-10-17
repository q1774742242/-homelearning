$(function(){
	getAllByPage();
	myadd();


	
});
function getAllByPage()
{
      $('#tb_departments').bootstrapTable({
            url:'/grade.do?method=all',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            dataType:'json',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            pagination: true,                   //是否显示分页（*）
            editable:true,//开启编辑模式
            striped: true,
            cache: false,
            
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
            uniqueId: "did",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'did',
                title: '等级编号',
                
                align:"center",order:"asc",
                sortable:"true",
            }, {
                field: 'dname',
                title: '等级名称',
                align:"center",
                type: 'text',
                
               
              
            }, {
                field: 'djf',
                title: '等级积分',
                align:"center",
             
                
            },
            {
                field: 'dmoneychange',
                title: '兑换比例',
                align:"center",
               
             
            },
            {
                field: 'dzk',
                title: '兑换折扣',
                align:"center",
               
          
                
            },
             {
            	 title : '操作',  
                 field : 'did',  
                 edit:false,
                 align : 'center',
                 formatter : function(value, row, did) {  
                	 var e = '<a href="#"  onclick="edit(\''+ value + '\')">编辑</a> ';  
                     var d = '<a href="#"  onclick="del(\''+ value + '\')">删除</a> ';  
                    return e + d;
                  }
             }
            ]
            
          
            });
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
			content :  "/grade.do?method=one&did="+obj,
			skin : 'layui-layer-grey',
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
			content :  "/grade.do?method=myadd",
			skin : 'layui-layer-grey',
			shift : 4, // 0-6的动画形式，-1不开启
			end : function() {
				 window.location.reload();
				getAllByPage();
			}
		});
	});
	
}

function deleAll(){
	$("#btn_delete").click(function(){
		//返回删除的记录编号(数组)
		var data=$.map($("#tb_departments").bootstrapTable("getSelections"),function(row){
			
		return row.did;
		});
		if (data.length==0) {
			layer.msg("请至少选择一个数据");
		}else {
			for (var i = 0; i < data.length; i++) {
				var did=data[i];
				del(did);
				
			}
		}
		
		
		
		

	})

}

/*function del(obj){
	$.ajax({
		url:"/grade.do?method=dele",
		data:{did:obj},
		dataType: "json",
		type:'post',
		success: function(data){
			if (data==1) {
	
			layer.msg('删除成功',{icon : 1,time : 3000});
			 location.reload();
			}
			
			
		
		}
	});
	
	
	
	
	
}*/
