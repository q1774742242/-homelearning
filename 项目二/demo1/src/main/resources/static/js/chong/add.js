var count=0;
var rimg='';
var ylessmoney=0;
$(function(){
	
	
	select();
	getyou();
	add();
});


//会员卡号的详情
function select(){
	
	$("#btn1").click(function(){
		$("#more").css("display","block");
		var rcard=$("#rcard1").val();
		if (rcard.length==0) {
			$("#more").css("display","none");
			$("#rcard").val("");
		} else {
			
			$.ajax({
				url:"/chong.do?method=getcard",
				data:{rcard:rcard},
				dataType: "json",
				type:'post',
				success: function(data){
					count=data;
					if (count==0) {
						layer.msg('此卡号不存在',{icon : 2,time : 3000});
					} else {
						
						$.ajax({
							url:"/chong.do?method=getselect",
							data:{rcard:rcard},
							dataType: "json",
							type:'post',
							success: function(data){
								
								$("#card").html(data.rcard);
								//
								$("#rcard").val(data.rcard);
								$("#name").text(data.rname);
								 rimg=data.rimg;
							
								var sex=data.rsex;
								if (sex==0) {
									$("#sex").html("男");
								} else {
									$("#sex").html("女");
								}
								$("#rid").val(data.rid);
								$("#psw").html(data.rpsw);
								$("#did").html(data.dname);
								$("#birthday").html(data.rbirthday);
								$("#tel").html(data.rtel);
								$("#cnum").html(data.rmoney);//余额
								$("#statu").html(data.rstatus);
								$("#code").html(data.rcode);
								$("#carnum").html(data.rcarnum);//车牌
								$("#cname").html(data.aname);
								$("#carxl").html(data.xname);
								$("#way").html(data.rway);//会员卡号
								$("#address").html(data.raddress);
								
								$("#cardtype").html(data.zname);//证件类型
								$("#cardnum").html(data.rnum);
								$("#time").html(data.rtime);
								$("#remark").html(data.rremark);
								
								
								
								
								getimg();
							}
								
						});
					
					
					}
					}
			
		});
		
		}
		
	});
	
	
}


//优惠下拉框
function getyou(){
	$("#yid").empty();
	$("#yid").append($("<option value='0'>--请选择优惠方式--</option>"));
	$.ajax({
		url:"/chong.do?method=ally",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			$.each(data,function(index,xx){
			
				$("#yid").append($("<option value='"+xx.yid+"'>"+xx.ytitle+"</option>"));
			
	
		});
			
		
			
		}
	});

	
}

//绑定优惠数据

	





//提交
function add(){
	
	$("#yid").change(function(){
		var yid=$("#yid").val();
		
		$.ajax({
			url:"/chong.do?method=getmoney",
			data:{yid},
			dataType: "json",
			type:'post',
			success: function(data){	
				$("#osmoney").val(data.ymoney);
				ylessmoney=data.ylessmoney;
			}
		});
		
		
		
		
		
	})
	
	$("#omoney").blur(function(){
		var omoney=$("#omoney").val();
		var osmoney=$("#osmoney").val();
		
		if (omoney<ylessmoney) {
			layer.tips('对不起，充值金额必须大于优惠起始金额',   '#omoney',      {tips:[2,'red']});
		} else {
			var olastmoney=omoney-osmoney;
			$("#olastmoney").val(olastmoney);
		}
		
	})
	
	
	

	$("#btn2").click(function(){
		
		var rcard=$("#rcard").val();
		var osmoney=$("#osmoney").val();
		var yid=$("#yid").val();
		var rid=$("#rid").val();
		var omoney=$("#omoney").val();
		var oremark=$("#oremark").val();
		var olastmoney=$("#olastmoney").val();
		
		
		if (rcard.length==0) {
			layer.tips('对不起，会员卡号不为空',   '#rcard',      {tips:[2,'red']});
		}else if (osmoney.length==0) {
			layer.tips('对不起，优惠金额不为空',   '#osmoney',      {tips:[2,'red']});
		}else if (yid.length==0) {
			layer.tips('对不起，请选择优惠方式',   '#yid',      {tips:[2,'red']});
		}else if (omoney.length==0) {
			layer.tips('对不起，充值金额不为空',   '#omoney',      {tips:[2,'red']});
		}else if (omoney<ylessmoney) {
			layer.tips('对不起，充值金额必须大于优惠最低金额',   '#omoney',      {tips:[2,'red']});
		}else if (olastmoney.length==0) {
			layer.tips('对不起，最终金额不可改',   '#olastmoney',      {tips:[2,'red']});
		}else if (oremark.length==0) {
			layer.tips('对不起，评价不为空',   '#oremark',      {tips:[2,'red']});
		}
		
		
		else {
			alert(rid);
			  var x='rcard='+rcard+"&osmoney="+osmoney+"&yid="+yid+"&omoney="+omoney+"&olastmoney="+olastmoney+"&oremark="+oremark+"&rid="+rid;
	  	    $.post("/chong.do?method=add",x,function(mydata){
		    	  if (mydata==1) {
		    		
		    		  var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
		    		  parent.layer.close(index);
		    		  parent.layer.msg('充值成功',{icon : 1,time : 3000});
		    		  location.reload();
					}
		      },'json');
	  	
	  	}
	})
		
		

	
}

function getimg(){
	
	
	  var img='<img src="//localhost:9090/'+rimg+'" width="600px" height="500px"/>';
	$("#bt").click(function(){
		layer.open({
			            type: 1,
			            title: false,
			            closeBtn: 0,
			            area: ['600px', '500px'],
			            skin: 'layui-layer-nobg', //没有背景色
			            shadeClose: true,
			            content: img
			          });
	})
	
		
} 	
