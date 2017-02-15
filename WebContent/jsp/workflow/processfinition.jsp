<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>流程定义管理</title>
<jsp:include page="/resources.jsp"></jsp:include>
<script src="${pageContext.request.contextPath }/js/jquery.form.js" type="text/javascript"></script>
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
.font_style{
	font-weight:bold;
	font-family:Microsoft YaHei;
	font-size:15px;
}
.yhorder_btn{
	width:100px;
	height:30px;
	background-color:#01B5E6;
	border:none;
	cursor:pointer;
	outline:none;
	margin-left:15px;
	color:#fff;
	display:block;
	float:left;
	line-height:30px;
	text-decoration : none;
}
.yhorder_btn_find:hover{
	background-color:#0085a9;
}
.yhorder_btn_res{
	background-color:#01B5E6;
}
.yhorder_btn_res:hover{
	background-color:#0085a9;
}
.img_class{
	position:relative;
	top:-9px;
}
</style>
</head>
<script type="text/javascript">
	$(function(){
		
		$("#grid").datagrid({
			striped : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			toolbar : [
				{
					id : 'deploy',
					text : '发布新流程',
					iconCls : 'icon-add',
					handler : function(){
						$('#importdlg').dialog('open').dialog('setTitle','导入zip文件');
					}
				}
			]
		});
	});
	
	
	$(document).ready(function(){
		$('#importExcel').click(function(){
 		if(1){
 			var fileDir = $("#importfile").val();
 			$('#importfm').ajaxSubmit({  
 				url: '<%=basePath%>functions/ajaxList222.action?file='+fileDir,
 						type:"POST",
 				dataType: 'text',
 				beforeSend:function(){$("#loadBox").show();},
				complete:function(){$("#loadBox").hide();},
 				success: resutlMsg,
 				error: errorMsg
 			}); 
 			function resutlMsg(msg){
 				var obj = JSON.parse(msg);
 				if(obj.state==1){
	       			$('#importdlg').dialog('close');        // close the dialog
	           		$('#dg').datagrid('reload');    // reload the user data
	           		 $.messager.alert("操作提示", "数据导入成功！");
	           		 <%-- $.messager.confirm('确认', '导入文件数据成功！是否查看错误数据？', function (r) {
        				if (r) {
        					window.location.href="<%=basePath%>console/framework/order/DupDataExcel.jsp";
        				}
    				}); --%>
	       		}else {
	       			$.messager.alert('操作提示', '导入excel文件失败！', 'warning');
	       		}
					
				}
				function errorMsg(){
					$.messager.alert('操作提示', "导入excel出错！", 'warning');
				}
 		}
		});
		
		
  });
</script>	
<body class="easyui-layout">
  <div region="center" >
  	<table id="grid" class="easyui-datagrid">  
  		<thead>
  			<tr>
  				<th data-options="field:'id'" width="120">流程编号</th>
  				<th data-options="field:'name'" width="200">流程名称</th>
  				<th data-options="field:'key'" width="100">流程名称</th>
  				<th data-options="field:'version'" width="80">版本号</th>
  				<th data-options="field:'viewpng'" width="200">查看流程图</th>
  			</tr>
  		</thead>
  		<tbody>
  				
  		</tbody>
  	</table>
  </div>
  
  <div id="importdlg" class="easyui-dialog" style="margin-top:40px; width: 300px; height: 150px; padding: 10px 20px" closed="true">
	  <form id="importfm" method="post"  enctype="multipart/form-data"novalidate >
		<!-- <input id="importfile" type="file" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" /> -->
		<input id="importfile" type="file"  /> 
	  </form>
	   <a href="javascript:void(0)" class="easyui-linkbutton c6" id="importExcel"
			iconCls="icon-ok" style="margin-top:20px;">确定</a> 
		 <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#importdlg').dialog('close')"
			style="margin-left:40px; margin-top:20px">取消</a>
	</div>
	  <div id="loadBox" style="display:none; position:absolute; top:30%; left:45%; background:rgba(0,0,0,0.5); z-index:99999; font-size:12px; padding:10px; border-radius:5px; color:#ffffff;">
	  	正在导入...
	  </div>
</body>
</html>