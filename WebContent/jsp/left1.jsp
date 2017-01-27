<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page
	import="com.tgb.entity.MenuItem"
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物流-菜单页面</title>

<script src="${pageContext.request.contextPath }/js/prototype.lite.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/moo.fx.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/moo.fx.pack.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		/* $.ajax({
			url:"${pageContext.request.contextPath }/menu/show.action", 
			type:"post",
			dataType:"json",
			success:function(data){
				console.log(data.menuList);
				var menu=data.menuList;
				//alert($(".first").html());
				//document.getElementById("first").innerHTML=menu[0].itemName;
				 for(var i=menu.length-1;i>=0;i--){
					//document.getElementById("first").innerHTML=menu[0].itemName;
					//$(".container").after('<h1 class="type"><a href="javascript:void(0)" id="first">'+menu[0].itemName+'</a></h1>');
					$("#container").append('<h1 class="type" id="menuName'+i+'"><a href="javascript:void(0)" id="first">'+menu[i].itemName+'</a></h1>');
					$("#menuName"+i).after('<div class="content" id="spli'+i+'"></div>');
					$("#spli"+i).append('<table id="content'+i+'" width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="${pageContext.request.contextPath }/images/menu_topline.gif" width="182" height="5" /></td></tr></table>');
					$("#content"+i).after('<ul class="MM" id="demo'+i+'"></ul>'); 
					$("#demo"+i).append('<li><a href="http://www.865171.cn" target="main">基本设置</a></li><li><a href="http://www.865171.cn" target="main">邮件设置</a></li>');
					var contents = document.getElementById('spli'+i);
					var toggles = document.getElementById('menuName'+i);
				
					var myAccordion = new fx.Accordion(
						toggles, contents, {opacity: true, duration: 400}
					);
					myAccordion.showThisHideOpen(contents[0]);
				} 
			
			},
			error:function(rq, status, e){
			}
	}); */
	
});
</script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(${pageContext.request.contextPath }/images/menu_bgs.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(${pageContext.request.contextPath }/images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(${pageContext.request.contextPath }/images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(${pageContext.request.contextPath }/images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(${pageContext.request.contextPath }/images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  <tr>
    <td width="182" valign="top">
    <div id="container" class="container">
    	
	      <h1 class="type"><a href="javascript:void(0)">网站常规管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="http://www.865171.cn" target="main">基本设置</a></li>
          <li><a href="http://www.865171.cn" target="main">邮件设置</a></li>
          <li><a href="http://www.865171.cn" target="main">广告设置</a></li>
          <li><a href="http://www.865171.cn" target="main">114增加</a></li>
          <li><a href="http://www.865171.cn" target="main">114管理</a></li>
          <li><a href="http://www.865171.cn" target="main">联系方式</a></li>
          <li><a href="http://www.865171.cn" target="main">汇款方式</a></li>
          <li><a href="http://www.865171.cn" target="main">增加链接</a></li>
          <li><a href="http://www.865171.cn" target="main">管理链接</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">栏目分类管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="http://www.865171.cn" target="main">信息分类</a></li>
          <li><a href="http://www.865171.cn" target="main">信息类型</a></li>
          <li><a href="http://www.865171.cn" target="main">资讯分类</a></li>
          <li><a href="http://www.865171.cn" target="main">地区设置</a></li>
          <li><a target="main" href="http://www.865171.cn">市场联盟</a></li>
          <li><a href="http://www.865171.cn" target="main">商家类型</a></li>
          <li><a href="http://www.865171.cn" target="main">商家星级</a></li>
          <li><a href="http://www.865171.cn" target="main">商品分类</a></li>
          <li><a href="http://www.865171.cn" target="main">商品类型</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">栏目内容管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="http://www.865171.cn" target="main">信息管理</a></li>
          <li><a href="http://www.865171.cn" target="main">张贴管理</a></li>
          <li><a href="http://www.865171.cn" target="main">增加商家</a></li>
          <li><a href="http://www.865171.cn" target="main">管理商家</a></li>
          <li><a href="http://www.865171.cn" target="main">发布资讯</a></li>
          <li><a href="http://www.865171.cn" target="main">资讯管理</a></li>
          <li><a href="http://www.865171.cn" target="main">市场联盟</a></li>
          <li><a href="http://www.865171.cn" target="main">名片管理</a></li>
          <li><a href="http://www.865171.cn" target="main">商城管理</a></li>
          <li><a href="http://www.865171.cn" target="main">商品管理</a></li>
          <li><a href="http://www.865171.cn" target="main">商城留言</a></li>
          <li><a href="http://www.865171.cn" target="main">商城公告</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">注册用户管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="http://www.865171.cn" target="main">会员管理</a></li>
          <li><a href="http://www.865171.cn" target="main">留言管理</a></li>
          <li><a href="http://www.865171.cn" target="main">回复管理</a></li>
          <li><a href="http://www.865171.cn" target="main">订单管理</a></li>
          <li><a href="http://www.865171.cn" target="main">举报管理</a></li>
          <li><a href="http://www.865171.cn" target="main">评论管理</a></li>
        </ul>
      </div>
    </div>
        <h1 class="type"><a href="javascript:void(0)">其它参数管理</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
            <li><a href="http://www.865171.cn" target="main">管理设置</a></li>
          <li><a href="http://www.865171.cn" target="main">主机状态</a></li>
          <li><a href="http://www.865171.cn" target="main">攻击状态</a></li>
          <li><a href="http://www.865171.cn" target="main">登陆记录</a></li>
          <li><a href="http://www.865171.cn" target="main">运行状态</a></li>
        </ul>
      </div>
	      
      </div>
	        <script type="text/javascript">
			var contents = document.getElementsByClassName('content');
			var toggles = document.getElementsByClassName('type');
		
			var myAccordion = new fx.Accordion(
				toggles, contents, {opacity: true, duration: 400}
			);
			myAccordion.showThisHideOpen(contents[0]);
			</script>
        </td>
  </tr>
</table>
</body>
</html>
