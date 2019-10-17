var count=0;
var aid=0;

$(function(){
	$("#aname").focus();
	aid=$("#aid").val();
	getone();
	getcount();
	
	upp();
});


//验证重名

function getcount(){
	$("#aname").blur(function(){
		var aname=$("#aname").val();
		
		$.ajax({
			url:"/car.do?method=verifyaname",
			data:{aname:aname,aid:aid},
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
			layer.tips('对不起，凭证不为空',   '#aname',      {tips:[2,'red']});
			
		}else{
			$("#btn").focus();
		}
		
		
		
	});
	
	

	
	
	
}




	
	
	


function getone(){
	$.ajax({
		url:"/car.do?method=one",
		data:{aid:aid},
		dataType: "json",
		type:'post',
		success: function(data){
			
			$("#aname").val(data.aname);
		}
	});
	
	
	
	
}



function upp(){
	
	$("#btn").click(function(){
		var aname=$("#aname").val();
	
		

		if (aname.length==0) {
			layer.tips('对不起，凭证不为空',   '#aname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#aname',      {tips:[2,'red']});
		}else {
			  var x='aname='+aname+"&aid="+aid;
	  	    $.post("/car.do?method=upp",x,function(mydata){
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