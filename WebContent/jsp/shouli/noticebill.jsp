<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tgb.entity.Staff"%>
<%@page import="com.tgb.entity.Standard"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href=${pageContext.request.contextPath }/images/LOGO.ico" rel="shortcut icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>业务受理</title>
<jsp:include page="/resources.jsp"></jsp:include>


<style type="text/css">
	#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 20px;
}

.fitem label {
	display: inline-block;
	width: 90px;
}

.fitem input {
	width: 160px;
}

.fitem input[type=radio] {
	width: 39px;
}
.textbox .textbox-text{white-space: pre-line;}
.newPass{width:200px; height:25px; margin-top:10px;}
.datagrid-htable{font-size:18px; text-align:center; font-weight:bold; height:40px; color:#000;}
.datagrid-btable{text-align:center;}
.datagrid-btable tr{height:40px;}
.dowebok { width: 400px; margin: 100px auto;}
.titleClass{width:100px; font-size:12px; color:#666666;}
</style>
</head>
<body >
<script type="text/javascript">
	$(function(){
		$("body").css({visibility:"visible"});
		
		// 点击新单按钮，将业务通知单 保存
		$('#save').click(function(){
			if($("#noticebillForm").form('validate')){
				//$('#noticebillForm').submit();
				 alert("提交成功！");
				 $('#noticebillForm').form('submit',{
				        url:'${pageContext.request.contextPath }/notibill/add.action',
				        onSubmit: function(){
				            return $(this).form('validate');
				        },
				        success: function(result){
				            window.location.href="${pageContext.request.contextPath }/notibill/index.action";
				        }
				 });
			}else{
				$.messager.alert('警告','表单存在非法数据项！','warning');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="north" style="height:31px;overflow:hidden;" split="false"
		border="false">
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
				plain="true">提交新单</a>
			<a id="edit" icon="icon-edit" href="${pageContext.request.contextPath }/workbill/index.action" class="easyui-linkbutton"
				plain="true">工单操作</a>	
		</div>
	</div>
	<div region="center" style="overflow:auto;padding:5px;" border="false">
		<form id="noticebillForm" action="${pageContext.request.contextPath }/notibill/add.action" method="post">
			<table class="table-edit" width="100%" align="center">
				<tr >
					<td class=titleClass colspan="4"><font color="red">客户信息</font></td>
				</tr>
				<tr >
					<td class=titleClass colspan="4">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="titleClass">来电号码:</td>
					<td><input type="text" class="easyui-validatebox" name="linkNum"
						required="true" /></td>
					<td class="titleClass">客户编号:</td>
					<td><input type="text" class="easyui-validatebox" name="customerId"
						required="true" /></td> 
				</tr>
				<tr>
					<td class="titleClass">客户姓名:</td>
					<td><input type="text" class="easyui-validatebox" name="customerName"
						required="true" /></td>
					<td class="titleClass">联系人:</td>
					<td><input type="text" class="easyui-validatebox" name="linkman"
						required="true" /></td>
				</tr>
				<tr >
					<td class=titleClass colspan="4">&nbsp;&nbsp;</td>
				</tr>
				<tr >
					<td class="titleClass" colspan="4"><font color="red">货物信息</font></td>
				</tr>
				<tr >
					<td class=titleClass colspan="4">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="titleClass">品名:</td>
					<td><input type="text" class="easyui-validatebox" name="product"
						required="true" /></td>
					<td class="titleClass">件数:</td>
					<td><input type="text" class="easyui-numberbox" name="num"
						required="true" /></td>
				</tr>
				<tr>
					<td class="titleClass">重量:</td>
					<td><input type="text" class="easyui-validatebox" name="weight"
						required="true" /></td> 
					<td class="titleClass" >体积:</td>
					<td><input type="text" class="easyui-validatebox" name="volume"
						required="true" /></td>
				</tr>
				<tr>
					<td class="titleClass">取件地址</td>
					<td colspan="3"><input type="text" class="easyui-validatebox" name="pickaddress"
						required="true" size="144"/></td>
				</tr>
				<tr>
					<td class="titleClass">到达城市:</td>
					<td><input type="text" class="easyui-validatebox" name="arrivecity"
						required="true" /></td>
					<td class="titleClass">预约取件时间:</td>
					<td class="titleClass"><input type="text" class="easyui-datebox" name="pickdate"
						data-options="required:true,editable:false"/></td>
				</tr>
				<tr>
					<td class="titleClass">备注:</td>
					<td colspan="3"><textarea rows="5" cols="80" type="text" class="easyui-validatebox" name="remark"
						required="true" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>