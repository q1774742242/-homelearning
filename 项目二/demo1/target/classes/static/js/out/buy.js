
var rid=0;

$(function(){
	rid=$("#rid").val();

	getAllByPage();
	Toorder();
	
	
	
});
function getAllByPage()
{
      $('#tb_departments').bootstrapTable({
            url:'/out.do?method=order&rid='+rid,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            dataType:'json',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            pagination: true,                   //是否显示分页（*）
            
            
            queryParams: function (params) {
                var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                page: params.limit,   //页面大小(每一页要显示多少条)
                begin: params.offset  //从数据库第几条记录开始 
                };
             return temp;
            },//传递参数（*）a
            
            
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5,10, 25, 50, 100],       //可供选择的每页的行数（*）
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            
            height: $(window).height()-100,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "tid",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'tid',
                title: '订单编号'
            }, 
            {
                field: 'rcard',
                title: '会员卡号'
            }, 
            {
                field: 'rname',
                title: '会员姓名'
            },

            {
                field: 'cname',
                title: '产品类型'
            },
            {
                field: 'fname',
                title: '产品名称'
            },

            {
                field: 'xcount',
                title: '购买数量'
            },
            {
                field: 'count',
                title: '小计'
            },
       
            {
                field: 'uname',
                title: '经办人'
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


//结账
function Toorder()
{	
	$("#btn").click(function(){
	
		
		var test=/\d+.\d+/g;
		var txt=$("#xmoney").text();
		var xmoney=txt.match(test);
		xmoney=parseFloat(xmoney);
		alert(typeof(xmoney));
		$.ajax({
			url:"/out.do?method=buy",
			data:{xmoney:xmoney},
			dataType: "json",
			type:'post',
			success:function(data){
				
				if (data==1) {
					 var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
		    		  parent.layer.close(index);
		    		  window.location.reload();
		    		  parent.layer.msg('下单成功',{icon : 1,time : 3000});
		    		  $("#tb_departments").bootstrapTable('refresh');
					
				}else{
					 var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
		    		  parent.layer.close(index);
		    		  window.location.reload();
					  parent.layer.msg('余额不足，下单失败',{icon : 2,time : 3000});
		    		  $("#tb_departments").bootstrapTable('refresh');
					
				}
				
			}
		});
		
		
		
		
	})
	

}





