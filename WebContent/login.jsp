<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tgb.entity.User"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href=${pageContext.request.contextPath }/images/LOGO.ico" rel="shortcut icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>集团后台登录页</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css">
	<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js" ></script>
</head>
<body>


<div class="content">
		<div class="logo">
			<h1>
				<a href="#"> </a>
			</h1>
		</div>
		<div class="container">
			<div class="text_align">
				<div class="left"></div>
				<div class="right">
					<div class="form">
					<h1>欢迎登录</h1>
					<div>
					
					</div>
					<ul>
						<li>
							<span><img src="${pageContext.request.contextPath }/images/person_03.png" style="position:absolute;top:55px;left:17px;"></span>
							<input type="text" id="<%=User.USERNAME%>"   placeholder="请输入您的用户名">
						</li>
						<li><span><img src="${pageContext.request.contextPath }/images/lock_03.png" style="position:absolute;top:124px;left:17px;"></span>
						<input type="password" id="<%=User.PASSWORD%>"  placeholder="请输入您的密码"></li>
						<!-- <li><b>验证码: </b><input type="text" 	name="captcha" id="" style="width:100px;"><img align="absmiddle" id="kaptcha" src="kaptcha.jpg" ><a id="changeCaptcha" href="javascript:void(0);">换一张</a></li> -->
						<li><input type="submit" id="submit" value="登陆" style="margin-right:25px;"><!-- <input type="reset" value="重置"> --></li>
				 <li style="height: 30px; color: red;"> </li>
					</ul>
					
					</div>       
				</div>
			</div>
		</div>	
	</div>




<script type="text/javascript">
$(document).ready(function(){
	
	$("#submit").click(function(){
		
		 $.ajax({
			url:"${pageContext.request.contextPath }/user/login.action", 
			type:"post",
			data:{<%=User.USERNAME%>:$("#<%=User.USERNAME%>").val(), <%=User.PASSWORD%>:$("#<%=User.PASSWORD%>").val()},
			dataType:"json",
			success:function(data){
				var url=data.url;
				if(data.msg!=null){
					alert(data.msg);
					url="";
				}
				
				window.location.href="${pageContext.request.contextPath}/"+url;
			},
			error:function(rq, status, e){
			} 
			
		});
		/* var form = document.forms[0];
		form.action = "/Myitem/user/login.action";
		form.method="post";
		form.submit();  */
	});

});
$(function(){
/* 	$('#changeCaptcha').click(     
			        function() {     
			           $("#kaptcha").hide().attr('src','kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();     
			    }); */
			    
});

</script>
</body></html>