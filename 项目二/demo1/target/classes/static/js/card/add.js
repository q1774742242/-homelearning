var count=0;

$(function(){
	$("#zname").focus();
	getcount();

	add();
});


//验证重名

function getcount(){
	$("#zname").blur(function(){
		var zname=$("#zname").val();
		
		$.ajax({
			url:"/card.do?method=getzname",
			data:{zname:zname},
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
		
		if (sname.length==0) {
			layer.tips('对不起，服务方式不为空',   '#zname',      {tips:[2,'red']});
			
		}
		else{
			$("#btn").focus();
		}
		
		
		
	});
	
	

	
	
	
}




	






function add(){
	
	$("#btn").click(function(){
		var zname=$("#zname").val();
	
		

		if (zname.length==0) {
			layer.tips('对不起，服务方式不为空',   '#zname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#zname',      {tips:[2,'red']});
		}else {
			  var x='zname='+zname;
	  	    $.post("/card.do?method=add",x,function(mydata){
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