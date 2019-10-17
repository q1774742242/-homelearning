var count=0;
var yid=0;

$(function(){
	$("#ytitle").focus();
	yid=$("#yid").val();
	 getone();
	getcount();
	gettime();
	upp();
});


//验证重名

function getcount(){
	$("#ytitle").blur(function(){
		var ytitle=$("#ytitle").val();
		
		$.ajax({
			url:"/you.do?method=verifytitle",
			data:{ytitle:ytitle,yid:yid},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
				
				if (count>0) {
				
					count=1;
					layer.tips('对不起，此名称已经被使用',   '#ytitle',      {tips:[2,'red']});
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (ytitle.length==0) {
			layer.tips('对不起，活动名称不为空',   '#ytitle',      {tips:[2,'red']});
			
		}
		
		
		
		
	});
	

	
}




	
	
	


function getone(){
	
	$.ajax({
		url:"/you.do?method=one",
		data:{yid:yid},
		dataType: "json",
		type:'post',
		success: function(data){
		
			$("#ytitle").val(data.ytitle);
			$("#ybegintime").val(data.ybegintime);
			$("#yendtime").val(data.yendtime);
			$("#ymoney").val(data.ymoney);
			$("#ylessmoney").val(data.ylessmoney);
		}
	});
	
	
	
	
}





//时间和金额

function gettime(){
	$("#ybegintime").blur(function(){
		var ybegintime=$("#ybegintime").val();
		if (ybegintime.length==0) {
			layer.tips('对不起，开始时间不为空',   '#ybegintime',      {tips:[2,'red']});
		} 
		
		
	});
	$("#yendtime").blur(function(){
		var yendtime=$("#yendtime").val();
		if (yendtime.length==0) {
			layer.tips('对不起，结束时间不为空',   '#yendtime',      {tips:[2,'red']});
		} 
		
		
	});
	$("#ymoney").blur(function(){
		var ymoney=$("#ymoney").val();
		if (ymoney.length==0) {
			layer.tips('对不起，优惠金额不为空',   '#ymoney',      {tips:[2,'red']});
		} else {
			$("#ylessmoney").focus();
		}
		
		
	});
	
	$("#ylessmoney").blur(function(){
		var ylessmoney=$("#ylessmoney").val();
		if (ylessmoney.length==0) {
			layer.tips('对不起，最低金额不为空',   '#ylessmoney',      {tips:[2,'red']});
		} else {
			$("#btn").focus();
		}
		
		
	});
	
	
	
	

}




function upp(){
	
	$("#btn").click(function(){
		var ytitle=$("#ytitle").val();
		var ybegintime=$("#ybegintime").val();
		var yendtime=$("#yendtime").val();
		var ymoney=$("#ymoney").val();
		var ylessmoney=$("#ylessmoney").val();
		

		if (ytitle.length==0) {
			layer.tips('对不起，服务方式不为空',   '#ytitle',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#ytitle',      {tips:[2,'red']});
		}else if (ybegintime.length==0) {
			layer.tips('对不起，此名称已经被使用',   '#ybegintime',      {tips:[2,'red']});
		}else if (yendtime.length==0) {
			layer.tips('对不起，此名称已经被使用',   '#yendtime',      {tips:[2,'red']});
		}else if (ymoney.length==0) {
			layer.tips('对不起，此名称已经被使用',   '#ymoney',      {tips:[2,'red']});
		}else if (ylessmoney.length==0) {
			layer.tips('对不起，此名称已经被使用',   '#ylessmoney',      {tips:[2,'red']});
		}
		else {
			 var x='ytitle='+ytitle+"&ybegintime="+ybegintime+"&yendtime="+yendtime+"&ymoney="+ymoney+"&ylessmoney="+ylessmoney+"&yid="+yid;
	  	    $.post("/you.do?method=upp",x,function(mydata){
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