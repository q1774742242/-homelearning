var count;
var checkCode = "";
var maxTime = 60;
$().ready(function() {
	/*$("#login_form").validate({
		rules: {
			username: "required",
			password: {
				required: true,
				minlength: 5
			},
		},
		messages: {
			username: "请输入姓名",
			password: {
				required: "请输入密码",
				minlength:$.validator.format("密码长度至少为5"),
			},
		}
	});*/
	/*$("#register_form").validate({
		rules: {
			username: "required",
			password: {
				required: true,
				minlength: 5
			},
			rpassword: {
				equalTo: "#register_password"
			},
			email: {
				required: true,
				email: true
			}
		},
		messages: {
			username: "请输入姓名",
			password: {
				required: "请输入密码",
				minlength: "请输入有效密码"
			},
			rpassword: {
				equalTo: "两次密码不一样"
			},
			email: {
				required: "请输入邮箱",
				email: "请输入有效邮箱"
			}
		}
	});*/
	
	getlogin();
	getlogin1();
	verifypsw();
	postcode();
	registercode();
	
});

$(function() {
	$("#register_btn").click(function() {
		$("#register_form").css("display", "block");
		$("#login_form").css("display", "none");
	});
	$("#back_btn").click(function() {
		$("#register_form").css("display", "none");
		$("#login_form").css("display", "block");
	});
});

function getlogin(){
	$("#uname").blur(function(){
		var uname=$("#uname").val();
		
		if (uname.length==0) {
			$("#uname-error").html("用户名不为空").show(300).delay(3000).hide(300);
		} else {
			
			$.ajax({
				url:"/user.do?method=login",
				data:{uname:uname},
				dataType: "json",
				type:'post',
				success: function(data){
					
					count=data;
					
					if (count==0) {
						$("#uname-error").html("用户名不存在").show(300).delay(3000).hide(300);
						
					} else {
						$("#upsw").focus();
					}
				
				}
			});
		}
	
		
		
		
	})

}


function getlogin1(){
	var uname=$("#uname").val();
	var upsw=$("#upsw").val();

	$("#btn").click(function(){
		var uname=$("#uname").val();
		var upsw=$("#upsw").val();
		if (uname.length==0) {
			$("#uname-error").html("用户名不为空").show(300).delay(3000).hide(300);
		} else if (count==0) {
			$("#uname-error").html("用户名不存在").show(300).delay(3000).hide(300);
		}else if(upsw.length==0){
			$("#password-error").html("密码不为空").show(300).delay(3000).hide(300);
		}else{
		
			$.ajax({
				url:"/user.do?method=getlogin",
				data:{uname:uname,upsw:upsw},
				dataType: "json",
				type:'post',
				success: function(data){
				
				var count1=data;
					
					if (count1==0) {
						
						parent.layer.msg("用户名或者密码错误", {icon : 1,time :3000});
						$("#uname").focus();
						
					} else {
						
						parent.layer.msg("登录成功", {icon : 1,time :3000});
						window.location.href="/user.do?method=index&begin=0";
					}
				
				}
			});
			
		}
		
		
		
	});
	

	
}

//发送验证码
function postcode(){
	
	$("#sendCheckCode").click(function () {
        var email = $("#email").val();
        
        if (email == null || email == ""){
            layer.msg("请输入邮箱！！！");
            return;
        }
        var index = layer.open({
            type:3,
            
        });

        $.ajax({
            url:"/user.do?method=getCheckCode",
            data:{email:email},
            dataType: "json",
            type:"post",
            success:function (text) {
                if (text != null && text != ""){
                    layer.close(index);
                    layer.msg("已发送");
                    checkCode = text;
                    countDown();
                } else{
                    layer.msg("获取失败，请重新获取");
                }
            }
        });
        
        countDown();
    });


	
}




//注册验证
function registercode(){
	
	
	
	$("#register").click(function(){
		var email=$("#email").val();
		var username=$("#username").val();
		var upsw=$("#register_password").val();
		var inputCheckCode = $("#checkCode").val();
		if (inputCheckCode==checkCode) {
			alert("ok");
			$.ajax({
	            url:"/user.do?method=register",
	            type:"post",
	            dataType: "json",	       
	            data:{email:email,uname:username,upsw:upsw},
	            success:function (data) {
	            	
	               var x=data
	            	if (x==1) {
	            		layer.msg("注册成功");
	            		window.location.href="/user.do?method=index1&begin=0";
					}else{
						layer.msg("注册失败");
					}
	            }
	        });
			
			
			
			
			
		}
		
		
		
		
		
	})
	
	
	
	
	
}



function countDown(){
    if (maxTime == 0){
        checkCode = "";
        $("#sendCheckCode").removeClass("layui-btn-disabled");
        $("#sendCheckCode").removeAttr("disabled")
        $("#sendCheckCode").html("获取验证码");
        maxTime = 60;
    }else{
        $("#sendCheckCode").attr("disabled","disabled");
        $("#sendCheckCode").addClass("layui-btn-disabled");
        
        $("#sendCheckCode").html(maxTime+"秒后重新获取");
        maxTime--;
        setTimeout(countDown,1000);
    }
}

function verifypsw(){
	$("#verify-password").blur(function(){
		var oldname=$("#register_password").val();
		var  newname=$("#verify-password").val();
		if (oldname==newname) {
			
			$("#email").focus();
		} else {
			layer.msg("两次密码不一致");
			
			$("#verify-password").focus().val("");
		}
		
		
		
		
	})
	
}



