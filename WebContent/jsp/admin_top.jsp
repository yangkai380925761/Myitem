<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title> 管理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="refresh" content="60">
 -->
 <script language=JavaScript>
 $(function(){
	 $.ajax({
		 url : '${pageContext.request.contextPath}/user/getCurrentUser.action',
		 type:"post",
		 dataType : 'json',
		 success : function(data) {
			var userList=data.userInfo;
			console.log(data.userList);
			$(".msg").html('管理员：<b>'+userList[0].userName+'</b> 您好,感谢登陆使用！');
		},
	 });
 });
 
	//修改密码
	function editPassword() {
		$('#editPwdWindow').window('open');
	}
	function logout(){
	// 询问 用户是否确认退出
	$.messager.confirm("确认窗口","你确定退出系统吗？", function(isConfirm){
		if(isConfirm){
			// 确认退出 
			location.href = "${pageContext.request.contextPath}/user/destroySession";
		}
	});
}
</script>

<base target="main">
<link href="${pageContext.request.contextPath }/images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="61%" height="64"><img src="${pageContext.request.contextPath }/images/logforme.gif" width="262" height="65"></td>
    <td width="39%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="52%" height="38" class="admin_txt msg"></td>
        <td width="15%"><button onClick="editPassword()" style="border-radius:4px;">修改密码</button></td>
      <%--   <td width="22%"><a href="#"  target="_self" onClick="logout();"><img src="${pageContext.request.contextPath }/images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td> --%>
        <td width="22%"><button  onClick="logout()" style="border-radius:4px;">退出</button></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
