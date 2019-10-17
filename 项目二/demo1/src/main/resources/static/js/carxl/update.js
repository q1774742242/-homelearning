var count=0;
var aid=0;
 var xid=0;
$(function(){
	
	
	xid=$("#xid").val();
	getaid()
	getone();
	getcount();
	
	upp();
});


//验证重名

function getcount(){
	$("#xname").blur(function(){
		var xname=$("#xname").val();
		
		$.ajax({
			url:"/carxl.do?method=verifyxname",
			data:{xname:xname,xid:xid},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
			
				if (count>0) {
				
					count=1;
					layer.tips('对不起，此名称已经被使用',   '#xname',      {tips:[2,'red']});
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (xname.length==0) {
			layer.tips('对不起，凭证不为空',   '#xname',      {tips:[2,'red']});
			
		}else{
			$("#btn").focus();
		}
		
		
		
	});
	
	

	
	
	
}


//下拉框
function getaid(){
	$("#select").empty();
	$.ajax({
		url:"/carxl.do?method=allx",
		data:{xid:xid},
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#select").append($("<option value='"+xx.aid+"'>"+xx.aname+"</option>"));
			
	
		});
			
		}
	});
	
	
}

	
	
	


function getone(){
	$.ajax({
		url:"/carxl.do?method=one",
		data:{xid:xid},
		dataType: "json",
		type:'post',
		success: function(data){
			
			$("#xname").val(data.xname);
		}
	});
	
	
	
	
}



function upp(){
	
	$("#btn").click(function(){
		var xname=$("#xname").val();
		var aid=$("#select").val();
		

		if (xname.length==0) {
			layer.tips('对不起，凭证不为空',   '#xname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#xname',      {tips:[2,'red']});
		}else {
			  var x='xname='+xname+"&aid="+aid+"&xid="+xid;
	  	    $.post("/carxl.do?method=upp",x,function(mydata){
		    	  if (mydata==1) {
		    		
		    		  var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
		    		  parent.layer.close(index);
		    		  parent.layer.msg('修改成功',{icon : 1,time : 3000});
		    		  location.reload();
					}
		      },'json');
	  	
	  	}
	})
		
		

	
}