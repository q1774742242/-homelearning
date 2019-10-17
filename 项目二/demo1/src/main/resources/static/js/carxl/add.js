var count=0;
var aid=0;
$(function(){
	$("#xname").focus();
	getaid();
	getcount();
	
	add();
});


//验证重名

function getcount(){
	$("#xname").blur(function(){
		var xname=$("#xname").val();
		
		$.ajax({
			url:"/carxl.do?method=getxname",
			data:{xname:xname},
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
			layer.tips('对不起，服务方式不为空',   '#xname',      {tips:[2,'red']});
			
		}
		else{
			$("#btn").focus();
		}
		
		
		
	});
	
	

	
	
	
}



//下拉框
function getaid(){
	$("#select").append($("<option value='0'>----请选择汽车品牌-----</option>"));
	$.ajax({
		url:"/carxl.do?method=alla",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#select").append($("<option value='"+xx.aid+"'>"+xx.aname+"</option>"));
			
	
		});
			
		}
	});
	
	
}
	






function add(){
	
	$("#btn").click(function(){
		var xname=$("#xname").val();
		 aid=$("#select").val();
		alert(aid+xname);
	
		if (xname.length==0) {
			layer.tips('对不起，服务方式不为空',   '#xname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#xname',      {tips:[2,'red']});
		}else {
			  var x='xname='+xname+"&aid="+aid;
	  	    $.post("/carxl.do?method=add",x,function(mydata){
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