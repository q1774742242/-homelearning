$(function(){
	getAllByPage();
	myadd();
	
	
});
function getAllByPage()
{
      $('#tb_departments').bootstrapTable({
            url:'/cp.do?method=all',         //请求后台的URL（*）
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
            uniqueId: "fid",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'fid',
                title: '库存编号'
            }, 
            {
                field: 'fname',
                title: '产品名称'
            }, 
            {
                field: 'cname',
                title: '产品类别'
            },
          
            {
                field: 'fimg',
                title: '产品图片',
                align : 'center',
				formatter : function(value, row, index) {
					var url=row.rimg;
				
					if(row.fimg!=null){
						var e = '<div class="myimg"><a class = "view"  href="javascript:void(0)" id="'+index+'"><span class="glyphicon glyphicon-picture" aria-hidden="true"></span>&nbsp;详情</a></div>';
						
						
					}
								
					return e;
				},

				events: 'operateEvents'//定义点击之后的事件
            },
            {
                field: 'faddress',
                title: '产地'
            },
            {
                field: 'foutprice',
                title: '出售价'
            },
            {
                field: 'finprice',
                title: '进价'
            },
        
            
            {
           	 	title : '产品入库记录',  
                field : 'fid',  
                align : 'center',
                formatter : function(value, row, index) {  
               	 var e = '<a  target="Conframe"   href="/cp.do?method=index1&fid='+ value + '">入库记录</a> ';  
                   
                   return e;
                 }
            },
            {
           	 	title : '产品出库记录',  
                field : 'fid',  
                align : 'center',
                formatter : function(value, row, index) {  
               	 var e = '<a target="Conframe" href="/out.do?method=index2&fid='+ value + '">出库记录</a> ';  
                   
                   return e;
                 }
            },
            
            ]});
      
      
      window.operateEvents = {
    		  
    		      'click .view': function (e, value, row, index) {
    		      console.log(row);
    		      /* var i = row.companyImage.indexOf("webapps");
    		  var url = row.companyImage.substring(i+7,row.companyImage.length); */
    		  var url = row.fimg;
    		  var img='<img src="//localhost:9090/'+url+'" width="600px" height="500px"/>';
    		
    		  layer.open({
    			  
    	
    		            type: 1,
    		            title: false,
    		            closeBtn: 0,
    		            area: ['600px', '500px'],
    		            skin: 'layui-layer-nobg', //没有背景色
    		            shadeClose: true,
    		            content: img
    		          });
    		      },
    		  };
    	
}


//查看详情



function allf(obj)
{

		$.ajax({
			url:"/cp.do?method=index1",
			data:{fid:obj},
			dataType: "json",
			type:'post',
		
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
			content :  "/cpinfo.do?method=myadd",
			skin : 'layui-layer-grey',
			shift : 4, // 0-6的动画形式，-1不开启
			end : function() {
				 window.location.reload();
				getAllByPage();
			}
		});
	});
	
}
