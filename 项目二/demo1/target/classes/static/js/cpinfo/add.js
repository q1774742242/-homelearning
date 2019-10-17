var count=0;

$(function(){
	$("#fname").focus();
	
	getcount();
	getcname();
	gettype();
	add();
});


//验证重名

function getcount(){
	$("#fname").blur(function(){
		var fname=$("#fname").val();
		
		$.ajax({
			url:"/cpinfo.do?method=getfname",
			data:{fname:fname},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
				
				if (count>0) {
				
					count=1;
					layer.tips('对不起，此名称已经被使用',   '#fname',      {tips:[2,'red']});
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (fname.length==0) {
			layer.tips('对不起，产品名称不为空',   '#fname',      {tips:[2,'red']});
			
		}
		else{
			$("#cid").focus();
		}
		
		
		
	});
	
	

	
	
	
}



//下拉框
function getcname(){
	$("#cid").empty();
	$("#cid").append($("<option value='0'>----请选择产品类型-----</option>"));
	$.ajax({
		url:"/cpinfo.do?method=allc",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#cid").append($("<option value='"+xx.cid+"'>"+xx.cname+"</option>"));
			
	
		});
			
		}
	});
	

}
	

//产品单位下拉框

function gettype(){
	
	$("#fmodel").empty();
	$("#fmodel").append($("<option value='0'>----请选择产品类型-----</option>"));
	$.ajax({
		url:"/cpinfo.do?method=alltype",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#fmodel").append($("<option value='"+xx.model+"'>"+xx.fmodel+"</option>"));
			
	
		});
			
		}
	});

}



function add(){
	
	$("#faddress").blur(function(){
		var faddress=$("#faddress").val();
		if (faddress.length==0) {
			
			layer.tips('对不起，产品地址不为空',   '#faddress',      {tips:[2,'red']});			
		} else {
			$("#foutprice").focus();
		}

	})
	
	$("#foutprice").blur(function(){
		var foutprice=$("#foutprice").val();
		if (foutprice.length==0) {
			
			layer.tips('对不起，产品首家不为空',   '#foutprice',      {tips:[2,'red']});			
		} else {
			$("#finprice").focus();
		}
		
		
		
		
	})
	
	
	$("#finprice").blur(function(){
		var finprice=$("#finprice").val();
		if (finprice.length==0) {
			
			layer.tips('对不起，产品进价不为空',   '#finprice',      {tips:[2,'red']});			
		} else {
			$("#ximg").focus();
		}

		
	});
	
	
	
	
	$("#btn").click(function(){
		var fname=$("#fname").val();
		var finprice=$("#finprice").val();
		var foutprice=$("#foutprice").val();
		var faddress=$("#faddress").val();
		var cid=$("#cid").val();
		var fmodel=$("#fmodel").text();
	
		var ximg=$("#ximg").val();
		var fcount=$("#fcount").val();
		
		if (fname.length==0) {
			layer.tips('对不起，服务方式不为空',   '#fname',      {tips:[2,'red']});
		}else if (cid.length==0) {
			layer.tips('对不起，请选择产品所属类型',   '#cid',      {tips:[2,'red']});
		}else if (fmodel.length==0) {
			layer.tips('对不起，请选择产品计量单位',   '#fmodel',      {tips:[2,'red']});
		}else if (faddress.length==0) {
			layer.tips('对不起，请填写产品生产地址',   '#faddress',      {tips:[2,'red']});
		}else if (finprice.length==0) {
			layer.tips('对不起，请选择产品进价',   '#finprice',      {tips:[2,'red']});
		}else if (foutprice.length==0) {
			layer.tips('对不起，请选择产品售价',   '#foutprice',      {tips:[2,'red']});
		}else if (ximg.length==0) {
			layer.tips('对不起，请选择照片',   '#ximg',      {tips:[2,'red']});
		}
		else {
			$.ajaxFileUpload({
	  	    	url:"/cpinfo.do?method=add",
	  	    	secureuri:false,
	  	    	fileElementId:['ximg'],
	  	    	data:{'fname':fname,'cid':cid,'fmodel':fmodel,'faddress':faddress,'foutprice':foutprice,'finprice':finprice,'fcount':fcount},
	  	    	dataType: "json",
	  	    	success: function(data,status){
	  	    		if (data==1) {	
	  	    		 var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
			    		  parent.layer.close(index);
			    		  parent.layer.msg('增加成功',{icon : 1,time : 3000});
			    		  location.reload();
					}
	  	    		
	  	    	
	  	    	}
	  	    	});
	  	
	  	}
		
	})
		
		

	
}