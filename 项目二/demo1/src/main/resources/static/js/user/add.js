var count=0;
var tel= /^1[0-9]{10}$/;
$(function(){
	$("#uname").focus();
	getcount();
	gettime();
	add();
});


//验证重名

function getcount(){
	$("#uname").blur(function(){
		var uname=$("#uname").val();
		
		$.ajax({
			url:"/user.do?method=getuname",
			data:{uname:uname},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
				
				if (count>0) {
				
					count=1;
					layer.tips('对不起，此账号已经被使用',   '#uname',      {tips:[2,'red']});
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (uname.length==0) {
			layer.tips('对不起，员工账号不为空',   '#uname',      {tips:[2,'red']});
			
		}
		else{
			$("#upsw").focus();
		}
		
		
		
	});
	

	
}

//时间和金额
function gettime(){
	$("#upsw").blur(function(){
		var upsw=$("#upsw").val();
		if (upsw.length==0) {
			layer.tips('对不起，真实姓名不为空',   '#upsw',      {tips:[2,'red']});
		} else {
			$("#urealname").focus();
		}
		
		
	});
	$("#urealname").blur(function(){
		var urealname=$("#upsw").val();
		if (urealname.length==0) {
			layer.tips('对不起，密码不为空',   '#urealname',      {tips:[2,'red']});
		} else {
			$("#utel").focus();
		}
		
		
	});
	$("#utel").blur(function(){
		var utel=$("#utel").val();
	
		if (!tel.test(utel)) {
			layer.tips('对不起，应该填写11位手机号',   '#utel',      {tips:[2,'red']});
		} else {
			$("#usex").focus();
		}
		
		
	});
	
	$("#usex").blur(function(){
		var usex=$("input[name='usex']:checked").val();
		if (usex==null) {
			layer.tips('对不起，请选择性别',   '#usex',      {tips:[2,'red']});
		} else {
			$("#btn").focus();
		}
		
		
	});
	
	
	
	

}


	






function add(){
	
	$("#btn").click(function(){
		var uname=$("#uname").val();
		var urealname=$("#urealname").val();
		var upsw=$("#upsw").val();
		var utel=$("#utel").val();
		var usex=$("input[name='usex']:checked").val();
		

		if (uname.length==0) {
			layer.tips('对不起，员工账号不为空',   '#uname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此账号已经存在',   '#uname',      {tips:[2,'red']});
		}else if (urealname.length==0) {
			layer.tips('对不起，真实姓名不为空',   '#urealname',      {tips:[2,'red']});
		}else if (upsw.length==0) {
			layer.tips('对不起，密码不为空',   '#upsw',      {tips:[2,'red']});
		}else if (!tel.test(utel)) {
			layer.tips('对不起，应该填写11位手机号',   '#utel',      {tips:[2,'red']});
		}else if (usex==null) {
			layer.tips('对不起，请选择性别',   '#usex',      {tips:[2,'red']});
		}
		else {
			  var x='uname='+uname+"&urealname="+urealname+"&upsw="+upsw+"&utel="+utel+"&usex="+usex;
	  	    $.post("/user.do?method=add",x,function(mydata){
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