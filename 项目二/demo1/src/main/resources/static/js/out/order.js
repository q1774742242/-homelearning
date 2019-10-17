var rid=0;

$(function(){
	
	select();
	
	myadd();
	buy();
	
});

function getAllByPage()
{
		
      $('#tb_departments').bootstrapTable({
            url:'/out.do?method=allorder&rid='+rid,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            dataType:'json',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            pagination: true,                   //是否显示分页（*）
            
            
            queryParams: function (params) {
                var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                page : params.limit,   //页面大小(每一页要显示多少条)
                begin: params.offset  //从数据库第几条记录开始 
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
                title: '会员编号'
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
                field: 'foutprice',
                title: '出售价格'
            },
            {
                field: 'lastprice',
                title: '折扣价格'
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
                field: 'ttime',
                title: '消费时间'
            },
            {
                field: 'uname',
                title: '经办人'
            },
            {
           	 title : '操作',  
                field : 'tid',  
                align : 'center',
                formatter : function(value, row, index) {  
               	 var e = '<a href="#"  onclick="edit(\''+ value + '\')">编辑</a> ';  
                    var d = '<a href="#"  onclick="del(\''+ value + '\')">删除</a> ';  
                   return e + d;
                 }
            }
           
            
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

function edit(obj)
{
	

	 layer.open({
			type : 2,
			title : '修改',
			fix : false,
			shadeClose : true,
			maxmin : true,
			area : [ '50%', '750px' ],
			content :  "/out.do?method=myone&tid="+obj,
			skin : 'layui-layer-grey',
			shift : 4, // 0-6的动画形式，-1不开启
			end : function() {
				$("#tb_departments").bootstrapTable('refresh');
				getAllByPage();
			}
		});
}

//删除

function del(obj)
{
	$.ajax({
		url:"/out.do?method=dele",
		data:{tid:obj},
		dataType: "json",
		type:'post',
		success: function(data){
			if (data==1) {	
 	    		
		    		layer.msg('删除成功',{icon : 1,time : 3000});
		    		$("#tb_departments").bootstrapTable('refresh');
		    		getAllByPage();
				}

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
			area : [ '50%', '750px' ],
			content :  "/out.do?method=myadd&rid="+rid,
			skin : 'layui-layer-grey',
			shift : 4, // 0-6的动画形式，-1不开启
			end : function() {
				
				$("#tb_departments").bootstrapTable('refresh');
				getAllByPage();
			}
		});
	});

	
	
}


function buy()
{
	
	$("#btn_buy").click(function(){
		var tt=$.map($('#tb_departments').bootstrapTable('getSelections'),function(row){
			return row.tid;
		});
		
		
		layer.open({
			type : 2,
			title : '购买商品',
			fix : false,
			shadeClose : true,
			maxmin : true,
			area : [ '50%', '800px' ],
			content :  "/out.do?method=mybuy&tt="+tt+"&rid="+rid,
			skin : 'layui-layer-grey',
			shift : 4, // 0-6的动画形式，-1不开启
			end : function() {
				$("#tb_departments").bootstrapTable('refresh');
				getAllByPage();
			}
		});
	});

	
	
}


/*************************************************/

function select(){
	
	$("#btn1").click(function(){
		
		var rcard=$("#rcard1").val();
		if (rcard.length==0) {
			$("#more").css("display","none");
			//$("#tb_departments").empty();
			$("#tb_departments").bootstrapTable("destroy");
		
		} else {
			$.ajax({
				url:"/chong.do?method=getcard",
				data:{rcard:rcard},
				dataType: "json",
				type:'post',
				success: function(data){
					count=data;
					
					if (count==0) {
						layer.msg('此卡号不存在',{icon : 2,time : 3000});
						$("#more").css("display","none");
						$("#rcard1").val("");
						$("#tb_departments").bootstrapTable("destroy");
					
					} else {
						
						$.ajax({
							url:"/chong.do?method=getselect",
							data:{rcard:rcard},
							dataType: "json",
							type:'post',
							success: function(data){
								
								$("#card").html(data.rcard);
								//
								$("#rcard").val(data.rcard);
								$("#name").text(data.rname);
								 rimg=data.rimg;
							
								var sex=data.rsex;
								if (sex==0) {
									$("#sex").html("男");
								} else {
									$("#sex").html("女");
								}
								$("#rid").val(data.rid);
								$("#psw").html(data.rpsw);
								$("#did").html(data.dname);
								$("#birthday").html(data.rbirthday);
								$("#tel").html(data.rtel);
								$("#cnum").html(data.rmoney);//余额
								$("#statu").html(data.rstatus);
								$("#code").html(data.rcode);
								$("#carnum").html(data.rcarnum);//车牌
								$("#cname").html(data.aname);
								$("#carxl").html(data.xname);
								$("#way").html(data.rway);//会员卡号
								$("#address").html(data.raddress);
								
								$("#cardtype").html(data.zname);//证件类型
								$("#cardnum").html(data.rnum);
								$("#time").html(data.rtime);
								$("#remark").html(data.rremark);
								 rid=$("#rid").val();
								
								
								
								//销毁表内的内容
								$("#tb_departments").bootstrapTable('destroy');
								getAllByPage();
								
								
								getimg();
							
								
								
							
								
							}
							
								
						});
						
						$("#more").css("display","block");
					}

					}
				
				
				})
				
				
		}
		
		
	});
	
}


/********************************************/
function getimg(){
	
	
	  var img='<img src="//localhost:9090/'+rimg+'" width="600px" height="500px"/>';
	  	$("#bt").click(function(){
		layer.open({
			            type: 1,
			            title: false,
			            closeBtn: 0,
			            area: ['600px', '500px'],
			            skin: 'layui-layer-nobg', //没有背景色
			            shadeClose: true,
			            content: img
			          });
	})
	
		
} 	



