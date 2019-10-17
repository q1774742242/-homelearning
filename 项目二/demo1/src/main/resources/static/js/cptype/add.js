var count=0;
var cid=0;


$(function(){
	$("#cname").focus();
	getcount();

	add();
});


//验证重名

function getcount(){
	$("#cname").blur(function(){
		var cname=$("#cname").val();
		
		$.ajax({
			url:"/ctype.do?method=getcname",
			data:{cname:cname},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
				
				if (count>0) {
				
					count=1;
					layer.tips('对不起，此名称已经被使用',   '#cname',      {tips:[2,'red']});
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (cname.length==0) {
			layer.tips('对不起，服务方式不为空',   '#cname',      {tips:[2,'red']});
			
		}
		else{
			$("#btn").focus();
		}
		
		
		
	});
	
	

	
	
	
}




	
	
	

function init(){

	$("#dchangemoney").blur(function(){
		var dmoneychange=$("#dchangemoney").val();
	
		if (!t.test(dmoneychange)) {
			layer.tips('对不起，请输入小数点后一位或两位的小数',   '#dchangemoney',      {tips:[2,'red']});
			
		} else {
			$("#dzk").focus();
		}
		
	});

		

	
	$("#dzk").blur(function(){
		var dzk=$("#dzk").val();

		if (!t.test(dzk)) {
			layer.tips('对不起，请输入小数点后一位或两位的小数',   '#dzk',      {tips:[2,'red']});
			
		} 
	
		
	});
		



}





function add(){
	
	$("#btn").click(function(){
		var cname=$("#cname").val();
	
		

		if (cname.length==0) {
			layer.tips('对不起，服务方式不为空',   '#cname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#cname',      {tips:[2,'red']});
		}else {
			  var x='cname='+cname;
	  	    $.post("/ctype.do?method=add",x,function(mydata){
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