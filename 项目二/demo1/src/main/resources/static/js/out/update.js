var count=0;
var tid=0;

$(function(){
	$("#xcount").focus();
	tid=$("#tid").val();
	tflag=$("#tflag").val();
	getone();
	getinput();
	upp();
});


//验证重名




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
	

//得到部分数据

function getone(){
	
	$.ajax({
		url:"/out.do?method=one",
		data:{tid:tid},
		dataType: "json",
		type:'post',
		success: function(data){
			$("#xcount").val(data.xcount);
			$("#cid").val(data.cname);
			$("#fname").val(data.fname);
			$("#gcount").val(data.gcount);
			$("#foutprice").val(data.foutprice);
			$("#lastprice").val(data.lastprice);
			$("#rid").val(data.rid);
			$("#fid").val(data.fid);
		}
	});
	
	
}


function getinput(){
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






function upp(){
	
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
			  var x='xcount='+xcount+"&rid="+rid+"&fid="+fid+"&tflag="+tflag+"&tid="+tid;
	  	    $.post("/out.do?method=upp",x,function(mydata){
		    	  if (mydata==1) {
		    		
		    		  var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
		    		  parent.layer.close(index);
		    		  window.location.reload();
		    		  parent.layer.msg('修改成功',{icon : 1,time : 3000});
		    		    
		    		
					}
		      },'json');
	  	
	  	}
	})
		
		

	
	
	
	
	
	
	
	
}