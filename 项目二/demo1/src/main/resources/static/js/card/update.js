var count=0;
var zid=0;

$(function(){
	$("#zname").focus();
	zid=$("#zid").val();
	getone();
	getcount();
	
	upp();
});


//验证重名

function getcount(){
	$("#zname").blur(function(){
		var zname=$("#zname").val();
		
		$.ajax({
			url:"/card.do?method=verifyzname",
			data:{zname:zname,zid:zid},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
			
				if (count>0) {
				
					count=1;
					layer.tips('对不起，此名称已经被使用',   '#zname',      {tips:[2,'red']});
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (zname.length==0) {
			layer.tips('对不起，凭证不为空',   '#zname',      {tips:[2,'red']});
			
		}else{
			$("#btn").focus();
		}
		
		
		
	});
	
	

	
	
	
}




	
	
	


function getone(){
	$.ajax({
		url:"/card.do?method=one",
		data:{zid:zid},
		dataType: "json",
		type:'post',
		success: function(data){
			
			$("#zname").val(data.zname);
		}
	});
	
	
	
	
}



function upp(){
	
	$("#btn").click(function(){
		var zname=$("#zname").val();
	
		

		if (zname.length==0) {
			layer.tips('对不起，凭证不为空',   '#zname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#zname',      {tips:[2,'red']});
		}else {
			  var x='zname='+zname+"&zid="+zid;
	  	    $.post("/card.do?method=upp",x,function(mydata){
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