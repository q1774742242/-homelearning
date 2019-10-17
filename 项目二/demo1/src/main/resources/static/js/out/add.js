var count=0;
var rid=0;
var foutprice=0;
var dzk=0;

$(function(){
	$("#cid").focus();
	rid=$("#rid").val();
	
	tflag=$("#tflag").val();
	getcid();
	getfid();
	getone();
	getinput();
	add();
});


//验证重名




//下拉框
function getcid(){
	$("#cid").empty();
	$("#cid").append($("<option value='0'>----请选择产品类型-----</option>"));
	$.ajax({
		url:"/cpinfo.do?method=allc",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#cid").append($("<option value='"+xx.cid+"'>"+xx.cname+"</option>"));
			
	
		});
			
		}
	});
	
	
}
	
//产品系列下拉框
function getfid(){
	$("#fid").empty();
	$("#fid").append($("<option value='0'>----请选择产品名称-----</option>"));
	$("#cid").change(function(){
		$("#fid").empty();
		$("#fid").append($("<option value='0'>----请选择产品名称-----</option>"));
	var cid=$("#cid").val();
	
	$.ajax({
		url:"/out.do?method=allf",
		data:{cid:cid},
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#fid").append($("<option value='"+xx.fid+"'>"+xx.fname+"</option>"));

		});
			
		}
	});
		
		
		
		
	})

	
}




//得到折扣

function getone(){
	
	$.ajax({
		url:"/out.do?method=getrid",
		data:{rid:rid},
		dataType: "json",
		type:'post',
		success: function(data){
			dzk=data.dzk;
		}
	});
	
	
}




//获得库存



function getinput(){
	
	
	$("#fid").change(function(){

		var fid=$("#fid").val();
		
		$.ajax({
			url:"/out.do?method=getfid",
			data:{fid:fid},
			dataType: "json",
			type:'post',
			success: function(data){
				foutprice=data.foutprice;
				lastprice=foutprice*dzk;
				$("#foutprice").val(data.foutprice);
				$("#lastprice").val(lastprice);//折后价
				$("#gcount").val(data.count);

			}
		});
		
		
		
		
		
	});

	$("#xcount").blur(function(){
		var xcount=$("#xcount").val();
		var gcount=$("#gcount").val();
		
		if (xcount.length==0) {
			layer.tips('对不起，请填写购买数量',   '#xcount',      {tips:[2,'red']});
		}else if(xcount-gcount>0){
			layer.tips('对不起，库存不够',   '#xcount',      {tips:[2,'red']});
			
		} else {
			var  lastprice=$("#lastprice").val();
			var  tcount=xcount*lastprice;
			$("#tcount").val(tcount);

		}

	})
	

}






function add(){
	
	$("#btn").click(function(){
		var gcount=$("#gcount").val();
		var xcount=$("#xcount").val();
		var tflag=$("#tflag").val();
		var rid=$("#rid").val();
		var fid=$("#fid").val();
	
		if (xcount.length==0) {
			layer.tips('对不起，数量不为空',   '#xcount',      {tips:[2,'red']});
		}else if(xcount-gcount>0){
			layer.tips('对不起，库存不够',   '#xcount',      {tips:[2,'red']});
			
		} else {
			  var x='xcount='+xcount+"&rid="+rid+"&fid="+fid+"&tflag="+tflag;
	  	    $.post("/out.do?method=add",x,function(mydata){
		    	  if (mydata==1) {
		    		
		    		  var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
		    		  parent.layer.close(index);
		    		  window.location.reload();
		    		  layer.msg('购买成功',{icon : 1,time : 3000});
		    		    
		    		
					}
		      },'json');
	  	
	  	}
	})
		
		

	
}