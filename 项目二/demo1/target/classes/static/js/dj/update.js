var count=0;
var count1=0;
var t = /^(([^0][0-9]+|0)\.([0-9]{1,2}))$/;
var did=0;
$(function(){
	 did=$("#did").val();

	$("#dname").focus();
	getone();
	getcount();
	getcount1();
	init();
	update();
});


//验证重名

function getcount(){
	$("#dname").blur(function(){
		var dname=$("#dname").val();
		
		$.ajax({
			url:"/grade.do?method=vname1",
			data:{dname:dname,did:did},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
				if (count>0) {
				
					count=1;
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (dname.length==0) {
			layer.tips('对不起，等级名称不为空',   '#dname',      {tips:[2,'red']});
			
		}else if(count>1){
			
			layer.tips('对不起，此名称已经被使用',   '#dname',      {tips:[2,'red']});
			
		}
		else{
			$("#djf").focus();
		}
		
		
		
	});
	
	

	
	
	
}


function getcount1(){
	

	$("#djf").blur(function(){
		var djf=$("#djf").val();
		$.ajax({
			url:"/grade.do?method=vcode1",
			data:{djf:djf,did:did},
			dataType: "json",
			type:'post',
			success: function(data){
				count1=data;
				if (count1>0) {
					count1=1;
				} else {
					count1=0;
				}
			
			}
		});
		if (djf.length==0) {
			layer.tips('对不起，等级积分不为空',   '#djf',      {tips:[2,'red']});
		}else if (count1>0) {
			layer.tips('对不起，此积分已经被使用',   '#djf',      {tips:[2,'red']});
		}
		else {
			$("#dchangemoney").focus();
		}
	});
	

		}

	
	
//传值
function getone(){

	$.ajax({
		url:"/grade.do?method=getone",
		data:{did:did},
		dataType: "json",
		type:'post',
		success: function(data){
			$("#dname").val(data.dname);
			$("#djf").val(data.djf);
			$("#dchangemoney").val(data.dmoneychange);
			$("#dzk").val(data.dzk);
		
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





function update(){
	
	$("#btn").click(function(){
		var dname=$("#dname").val();
		var djf=$("#djf").val();
		var dmoneychange=$("#dchangemoney").val();
		var dzk=$("#dzk").val();
		

		if (dname.length==0) {
			layer.tips('对不起，等级名称不为空',   '#dname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#dname',      {tips:[2,'red']});
		}else if (djf.length==0) {
			layer.tips('对不起，等级积分不为空',   '#djf',      {tips:[2,'red']});
		}else if (count1>0) {
			layer.tips('对不起，此积分已经被使用',   '#djf',      {tips:[2,'red']});
		}else if(!t.test(dmoneychange)){
			layer.tips('对不起，请输入小数点后一位或两位的小数',   '#dchangemoney',  {tips:[2,'red']});
			return false;
		}else if(!t.test(dzk)){
			layer.tips('对不起，请输入小数点后一位或两位的小数',   '#dzk',      {tips:[2,'red']});
			return false;
		}else {
			
		
			  var x='dname='+dname+"&djf="+djf+"&dmoneychange="+dmoneychange+"&dzk="+dzk+"&did="+did;
	  	    $.post("/grade.do?method=upp",x,function(mydata){
		    	  if (mydata==1) {
		    		
		    		  var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
		    		  parent.layer.close(index);
		    		  parent.layer.msg('修改成功',{icon : 1,time : 3000});
		    	
		    		
					}
		      },'json');
	  	
	  	}
	})
		
		

	
}