var count=0;

$(function(){
	$("#fid").focus();
	getfname();
	getvalue();
	add();
});


//验证重名

function getcount(){
	$("#aname").blur(function(){
		var aname=$("#aname").val();
		
		$.ajax({
			url:"/car.do?method=getaname",
			data:{aname:aname},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
				
				if (count>0) {
				
					count=1;
					layer.tips('对不起，此名称已经被使用',   '#aname',      {tips:[2,'red']});
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (aname.length==0) {
			layer.tips('对不起，服务方式不为空',   '#aname',      {tips:[2,'red']});
			
		}
		else{
			$("#btn").focus();
		}
		
		
		
	});
	
	

	
	
	
}



//产品下拉框

function getfname(){
	$("#fid").empty();
	$("#fid").append($("<option value='0'>----请选择产品-----</option>"));
	$.ajax({
		url:"/cp.do?method=getfname",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#fid").append($("<option value='"+xx.fid+"'>"+xx.fname+"</option>"));
			
	
		});
			
		}
	});
	
	
	
	
	
}
	
function  getvalue(){
	$("#gtime").blur(function(){
		var gtime=$("#gtime").val();
		if (gtime.length==0) {
			layer.tips('对不起，情选择时间',   '#gtime',      {tips:[2,'red']});
		} else {
			$("#gcount").focus();
		}
		
	});
	$("#gcount").blur(function(){
		var gcount=$("#gcount").val();
		if (gcount.length==0) {
			layer.tips('对不起，情选数量',   '#gcount',      {tips:[2,'red']});
		} else {
			$("#btn").focus();
		}
		
	});
	
	

}





function add(){
	
	$("#btn").click(function(){
		var fid=$("#fid").val();
		var	gcount=$("#gcount").val();
		var gtime=$("#gtime").val();
		

		if (fid==0) {
			layer.tips('对不起，请选择产品',   '#fid',      {tips:[2,'red']});
		}else if (gcount.length==0) {
			layer.tips('对不起，数量不为空',   '#gcount',      {tips:[2,'red']});
		}else if (gtime.length==0) {
			layer.tips('对不起，时间不为空',   '#gtime',      {tips:[2,'red']});
		}else {
			  var x='fid='+fid+"&gcount="+gcount+"&gtime="+gtime;
	  	    $.post("/cp.do?method=add",x,function(mydata){
		    	  if (mydata==1) {
		    		
		    		  var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
		    		  parent.layer.close(index);
		    		  parent.layer.msg('增加成功',{icon : 1,time : 3000});
		    		  location.reload();
					}
		      },'json');
	  	
	  	}
	})
		
		

	
}