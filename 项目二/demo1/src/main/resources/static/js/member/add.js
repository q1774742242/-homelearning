var count=0;
var count1=0;
$(function(){
	$("#rcard").focus();
	 getcount1();
	 getcount();
	 getgrade();
	 getcar();
	 getcarxl();
	 getpcard();
	 add();
});


//验证重名

function getcount(){
	$("#rname").blur(function(){
		var rname=$("#rname").val();
		
		$.ajax({
			url:"/member.do?method=getrname",
			data:{rname:rname},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count=data;
				
				if (count>0) {
				
					count=1;
					layer.tips('对不起，此名称已经被使用',   '#rname',      {tips:[2,'red']});
				} else {
				
					count=0;
				}
			
			}
		});
		
		if (rname.length==0) {
			layer.tips('对不起，会员名称不为空',   '#rname',      {tips:[2,'red']});
			
		}
		else{
			$("#ximg").focus();
		}
		
		
		
	});
	
	

	
	
	
}
//验证重编号
function getcount1(){
	$("#rcard").blur(function(){
		var rcard=$("#rcard").val();
		
		$.ajax({
			url:"/member.do?method=getrcard",
			data:{rcard:rcard},
			dataType: "json",
			type:'post',
			success: function(data){
				
				count1=data;
				
				if (count>0) {
				
					count1=1;
					layer.tips('对不起，此卡号已经被使用',   '#rcard',      {tips:[2,'red']});
				} else {
				
					count1=0;
				}
			
			}
		});
		
		if (rcard.length==0) {
			layer.tips('对不起，会员卡号不为空',   '#rcard',      {tips:[2,'red']});
			
		}
		else{
			$("#rname").focus();
		}
		
		
		
	});
	
	

	
	
	
}


//下拉框
//等级
function getgrade(){
	$("#did").empty();
	$("#did").append($("<option value='0'>----请选择会员等级-----</option>"));
	$.ajax({
		url:"/member.do?method=alld",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#did").append($("<option value='"+xx.did+"'>"+xx.dname+"</option>"));
			
	
		});
			
		}
	});
	
	
}

//性别
function getsex(){

	$("#rsex").append($("<option value='0'>----请选择性别-----</option>"));
	$.ajax({
		url:"/member.do?method=allsex",
		data:{rid:rid},
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#rsex").append($("<option value='"+xx.rsex+"'>"+xx.sex+"</option>"));
			
	
		});
			
		}
	});
	
	
}

//证件类型
function getpcard(){
	$("#zid").empty();
	$("#zid").append($("<option value='0'>----请选择证件类型-----</option>"));
	$.ajax({
		url:"/member.do?method=allcard",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#zid").append($("<option value='"+xx.zid+"'>"+xx.zname+"</option>"));
			
	
		});
			
		}
	});
	
	
}


//联动下拉框
function getcar(){
	$("#aid").empty();
	$("#aid").append($("<option value='0'>----请选择汽车品牌-----</option>"));
	$.ajax({
		url:"/member.do?method=allc",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			
			
			$.each(data,function(index,xx){
			
				$("#aid").append($("<option value='"+xx.aid+"'>"+xx.aname+"</option>"));
			
	
		});
			
		}
	});
	
	
}

//汽车系列
function getcarxl(){
	$("#xid").append($("<option value='0'>----请选择汽车系列-----</option>"));
	$("#aid").change(function(){
		var aid=$("#aid").val();
		$("#xid").empty();
		$("#xid").append($("<option value='0'>----请选择汽车系列-----</option>"));
		$.ajax({
			url:"/member.do?method=allx",
			data:{aid:aid},
			dataType: "json",
			type:'post',
			success: function(data){
				
				
				$.each(data,function(index,xx){
				
					$("#xid").append($("<option value='"+xx.xid+"'>"+xx.xname+"</option>"));
				
		
			});
				
			}
		});
		
		
		
		
		
	})

}




function add(){
	

	
	
	$("#btn").click(function(){

		var rname=$("#rname").val();
		var rcard=$("#rcard").val();
		var ximg=$("#ximg").val();
		
		var rtel=$("#rtel").val();
		var rpsw=$("#rpsw").val();
		var did=$("#did").val();
		
		var zid=$("#zid").val();
		var xid=$("#xid").val();
		var aid=$("#aid").val();
		
		var raddress=$("#raddress").val();
		var rbirthday=$("#rbirthday").val();
		var rmoney=$("#rmoney").val();
		
		var rcode=$("#rcode").val();
		var rcarnum=$("#rcarnum").val();
		var rcolor=$("#rcolor").val();
		
		var rnum=$("#rnum").val();
		var rway=$("#rway").val();
		var rremark=$("#rremark").val();
		
		var rsex=$("#rsex").val();
		if (rcard.length==0) {
			layer.tips('对不起，卡号不为空',   '#rcard',      {tips:[2,'red']});
		}else if (count1>0) {
			layer.tips('对不起，此卡号已经被使用',   '#rcard',      {tips:[2,'red']});
		}else if (rname.length==0) {
			layer.tips('对不起，会员名称不为空',   '#rname',      {tips:[2,'red']});
		}else if (count>0) {
			layer.tips('对不起，此名称已经被使用',   '#rname',      {tips:[2,'red']});
		}else if (ximg.length==0) {
			layer.tips('对不起，请上传图片',   '#ximg',      {tips:[2,'red']});
		}else if (rtel.length==0) {
			layer.tips('对不起，手机号码不为空',   '#rtel',      {tips:[2,'red']});
		}
		else if (rpsw.length==0) {
			layer.tips('对不起，密码不为空',   '#rpsw',      {tips:[2,'red']});
		}else if (did==0) {
			layer.tips('对不起，请选择会员等级',   '#did',      {tips:[2,'red']});
		}else if (zid==0) {
			layer.tips('对不起，请选择证件类型',   '#zid',      {tips:[2,'red']});
		}else if (xid==0) {
			layer.tips('对不起，请选择汽车系列',   '#xid',      {tips:[2,'red']});
		}else if (raddress.length==0) {
			layer.tips('对不起，地址不为空',   '#raddress',      {tips:[2,'red']});
		}else if (rbirthday.length==0) {
			layer.tips('对不起，会员生日',   '#rbirthday',      {tips:[2,'red']});
		}
		
		else if (rmoney.length==0) {
			layer.tips('对不起，会员余额不为空',   '#rmoney',      {tips:[2,'red']});
		}else if (rcode.length==0) {
			layer.tips('对不起，会员积分不为空',   '#rcode',      {tips:[2,'red']});
		}
		else if (rcolor.length==0) {
			layer.tips('对不起，汽车颜色不为空',   '#rcolor',      {tips:[2,'red']});
		}
		else if (rcarnum.length==0) {
			layer.tips('对不起，车牌号不为空',   '#rcarnum',      {tips:[2,'red']});
		}
		else if (rway.length==0) {
			layer.tips('对不起，汽车里程不为空',   '#rway',      {tips:[2,'red']});
		}
		else if (rremark.length==0) {
			layer.tips('对不起，备注不为空',   '#rremark',      {tips:[2,'red']});
		}
		else if (rnum.length==0) {
			layer.tips('对不起，证件号码不为空',   '#rnum',      {tips:[2,'red']});
		}
	
		else {
			
			 // var x='rname='+rname+"&rcard="+rcard+"&ximg="+ximg+"&rtel="+rtel+"&rpsw="+rpsw+"&did="+did+"&zid="+zid+"&xid="+xid+"&raddress="+raddress+"&rbirthday="+rbirthday+"&rmoney="+rmoney+"&rcode="+rcode+"&rcarnum="+rcarnum+"&rcolor="+rcolor+"&rnum="+rnum+"&rway="+rway+"&rsex="+rsex+"&rremark="+rremark;
			  $.ajaxFileUpload({
      	    	url:"/member.do?method=add",
      	    	secureuri:false,
      	    	fileElementId:['ximg'],
      	    	data:{"rcard":rcard,"rname":rname,"rtel":rtel,"rpsw":rpsw,"did":did,"zid":zid,"xid":xid,"raddress":raddress,"rbirthday":rbirthday,"rmoney":rmoney,"rcode":rcode,"rcarnum":rcarnum,"rcolor":rcolor,"rnum":rnum,"rway":rway,"rsex":rsex,"rremark":rremark},
      	    	dataType: "json",
      	    	success: function(data,status){
      	    		if (data==1) {	
      	    		 var index=parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
   		    		  parent.layer.close(index);
   		    		  parent.layer.msg('增加成功',{icon : 1,time : 3000});
   		    		  location.reload();
					}
      	    		
      	    	
      	    	}
      	    	});
	  	
	  	}
	})
		
		

	
}