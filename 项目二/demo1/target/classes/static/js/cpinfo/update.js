var count=0;
var fimg="";
var fid=0;
$(function(){
	fid=$("#fid").val()
	$("#fname").focus();
	getone()
	getcount();
	getcname();
	gettype();
	update();
});


//验证重名

function getcount(){
	$("#fname").blur(function(){
		var fname=$("#fname").val();
		
		$.ajax({
			url:"/cpinfo.do?method=verifyfname",
			data:{fname,fid},
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
	$.ajax({
		url:"/cpinfo.do?method=uppc",
		data:{fid},
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#cid").append($("<option value='"+xx.cid+"'>"+xx.cname+"</option>"));
			
	
		});
			
		}
	});
	

}
	
function getone(){
	$.ajax({
		url:"/cpinfo.do?method=one",
		data:{fid},
		dataType: "json",
		type:'post',
		success: function(data){
			$("#fname").val(data.fname);
			$("#finprice").val(data.finprice);
			$("#foutprice").val(data.foutprice);
			$("#faddress").val(data.faddress);
			
			 fimg=data.fimg
		
			 getimg();	
		}
	});
	

}



function gettype(){
	
	$("#fmodel").empty();
	$.ajax({
		url:"/cpinfo.do?method=upptype",
		data:{fid},
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#fmodel").append($("<option value='"+xx.model+"'>"+xx.fmodel+"</option>"));
			
	
		});
			
		}
	});

}



function update(){
	
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
	  	    	url:"/cpinfo.do?method=upp",
	  	    	secureuri:false,
	  	    	fileElementId:['ximg'],
	  	    	data:{'fname':fname,'cid':cid,'fmodel':fmodel,'faddress':faddress,'foutprice':foutprice,'finprice':finprice,'fcount':fcount,'fid':fid},
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



function getimg(){
	
	
	  var img='<img src="//localhost:9090/'+fimg+'" width="600px" height="500px"/>';
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