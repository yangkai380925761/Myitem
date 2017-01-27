<%@page import="java.util.List"%>
<%@page import="com.tgb.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@page  
import="com.tgb.entity.User"
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物业管理系统_用户登录</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE1 {font-size: 12px}
-->
</style>
<script>
var isCommitted = false;
$(document).ready(function(){
		
		$("#submit").click(function(){
			$.ajax({
				url:"${pageContext.request.contextPath }/user/login.action", 
				type:"post",
				data:{<%=User.USERNAME%>:$("#<%=User.USERNAME%>").val(), <%=User.PASSWORD%>:$("#<%=User.PASSWORD%>").val()},
				dataType:"json",
				success:function(data){
					if(data.msg!=null){
						alert(data.msg);
					}
					window.location.href="${pageContext.request.contextPath}/"+data.url;
				},
				error:function(rq, status, e){
				}
			});
			/* var form = document.forms[0];
			form.action = "/Myitem/user/login.action";
			form.method="post";
			form.submit();  */
			
			
		});
	
})
$(function(){
	
	
})
</script>
</head>

<body>
<form>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="9fc967">&nbsp;</td>
  </tr>
  <tr>
    <td height="604"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="604" background="${pageContext.request.contextPath }/images/login_02.gif">&nbsp;</td>
        <td width="989"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="345" background="${pageContext.request.contextPath }/images/login_11.gif">&nbsp;</td>
          </tr>
          <tr>
            <td height="47"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="539" height="47" background="${pageContext.request.contextPath }/images/login_05.gif" nowrap="nowrap">&nbsp;</td>
                <td width="206" background="${pageContext.request.contextPath }/images/login_06.gif" nowrap="nowrap"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="17%" height="22"><div align="center"><span class="STYLE1">用户</span></div></td>
                    <td width="58%" height="22"><div align="center">
                        <input name="userName" type="text" id="<%=User.USERNAME%>" size="15" style="height:17px;margin:1px auto; border:solid 1px #bbbbbb">
                    </div></td>
                    <td width="25%" height="22">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="22"><div align="center"><span class="STYLE1">密码</span></div></td>
                    <td height="22"><div align="center">
                        <input name="password" type="password" id="<%=User.PASSWORD%>" size="15" style="height:17px;margin:1px auto; border:solid 1px #bbbbbb">
                    </div></td>
                    <td height="22"><div align="center"><input type="image" src="${pageContext.request.contextPath }/images/dl.gif" width="39" height="18" id="submit" onclick="return false;"></div></td>
                  </tr>
                </table></td>
                <td width="244" background="${pageContext.request.contextPath }/images/login_07.gif" nowrap="nowrap">&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="212" background="${pageContext.request.contextPath }/images/login_08.gif">&nbsp;</td>
          </tr>
        </table></td>
        <td background="${pageContext.request.contextPath }/images/login_04.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="70ad21">&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
