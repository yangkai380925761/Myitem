<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tgb.entity.Staff"%>
<%@page import="com.tgb.entity.Dqarea"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href=${pageContext.request.contextPath }/images/LOGO.ico" rel="shortcut icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>工作单批量导入</title>
<jsp:include page="/resources.jsp"></jsp:include>
<script src="${pageContext.request.contextPath }/js/Area.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/AreaData_min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/upload/jquery.ocupload-1.1.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/upload/ExportExcelDlg.js" type="text/javascript"></script>
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
.dowebok { width: 400px; margin: 100px auto;}
</style>
</head>
<body >
<script type="text/javascript">
var url;
var i=0;
	function add(){
		
		 $('#dlg').dialog('open').dialog('setTitle',' ');
		
		 //$(".combo-select").after('<label>收派标准:</label>');
		
		 $('#fm').form('clear');
		 url = '<%=basePath%>dqarea/add.action';
		
		 $('#ftitle').html("添加标准");
	}
	function shows(){ //查看
	    var row = $('#dg').datagrid('getSelected');
	    var rows = $('#dg').datagrid('getSelections');
	    if (row == undefined) {
	     		$.messager.alert('操作提示', "没有选择被操作的记录！", 'warning');
	     		return;
	     	 } 
	    if(rows.length>1){
	    	$.messager.alert('操作提示', "请选择一条数据！", 'warning');
	     	return false;
	    }  
		if (row){
			editBean(row);
		}
}
	var j=0;
	//编辑操作
	function editBean(row){
		
	    if (row){
	        $('#dlg').dialog('open').dialog('setTitle','');
	        $('#fm').form('load',row);
	        url = '<%=basePath%>dqarea/update.action';    
	        $('#ftitle').html("修改标准信息");
	    }
	}
	
	//修改保存
	function saveBean(){
		//alert($('#userName').val());
		 $('#fm').form('submit',{
		        url: url,
		        onSubmit: function(){
		            return $(this).form('validate');
		        },
		        success: function(result){
		            $('#dlg').dialog('close');        // close the dialog
		            $('#dg').datagrid('reload');    // reload the user data
		        }
		 });
	}
	//删除
	function destroyBean(){
		var rows = $('#dg').datagrid('getChecked');
	    if (rows.length>0){
	        $.messager.confirm('温馨提示','你真的要删除么?',function(r){
	            if (r){
	            	for(var i=0; i<rows.length; i++){
	            		var row=rows[i];
	             $.post('<%=basePath%>dqarea/delete.action',{id:row.id},function(result){
	                    if (result.success){
	                        $('#dg').datagrid('reload');    // reload the user data
	                    } else {
	                        $.messager.show({    // show error message
	                            title: 'Error',
	                            msg: "删除失败"
	                        });
	                    }
	                },'json'); 
	            }
	            }
	        });
	    }
	}
	//-------------格式化日期插件--------------------------
	Date.prototype.format = function (format) {  
	    var o = {  
	        "M+": this.getMonth() + 1, // month  
	        "d+": this.getDate(), // day  
	        "h+": this.getHours(), // hour  
	        "m+": this.getMinutes(), // minute  
	        "s+": this.getSeconds(), // second  
	        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
	        "S": this.getMilliseconds()  
	        // millisecond  
	    }  
	    if (/(y+)/.test(format))  
	        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
	            .substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	        if (new RegExp("(" + k + ")").test(format))  
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
	    return format;  
	}  
	function formatDatebox(value) {  
	    if (value == null || value == '') {  
	        return '';  
	    }  
	    var dt;  
	    if (value instanceof Date) {  
	        dt = value;  
	    } else {  
	        dt = new Date(value);  
	    }  
	  
	    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)  
	}  
	
	function refresh(){
		$("#dg").datagrid('reload');
	}
	
	function doImport() {  //打开导入excel对话框
		
		$('#importdlg').dialog('open').dialog('setTitle','导入Excel');
		$.messager.alert('操作提示',"请选择d盘根目录下的xls文件");
	}
	
	function exportExcel() {  //导出excel文件
	 	var rows = $('#dg').datagrid('getChecked');
		if (rows.length == 0) {
			
			$.messager.alert('操作提示', "请选择要导出的数据！", 'warning');
			return false;
	 	}else{
	 		var Nums = '';
	 		for(var i=0; i<rows.length; i++){
	 			Nums += rows[i].id+',';
		    }
			location.href = "<%=basePath %>quickworkbill/exportExcel.action?Nums="+Nums;
	 	} 
	}
	//---------------结束-----------------------
	$(function(){
		$('#dg').datagrid({
		    height: '100%',
		    fit:true,
		    url: '<%=basePath %>quickworkbill/getQuickbillList.action',
		    method: 'POST',
		    striped: true,  //显示条纹
		    nowrap: true,	//设置为true，当数据长度超出列宽时将会自动截取。
		    pageSize: 10,		//当设置分页属性时，初始化每页记录数。
		    pageNumber:1, 	//当设置分页属性时，初始化分页码。
		    pageList: [10, 20, 50, 100, 150, 200],	//当设置分页属性时，初始化每页记录数列表。
		    showFooter: true,	//定义是否显示行底（如果是做统计表格，这里可以显示总计等）
			loadMsg : '数据加载中请稍后……',	//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,		//设置true将在数据表格底部显示分页工具栏。
		    toolbar:"#toolbar",
		    checkOnSelect:false,
		    selectOnCheck:false,
		    
		    columns: [[
		        { field: 'ck', checkbox: true },
		        { field: 'id', title: '工作单号', width: 150},
		        { field: 'product', title: '产品', width: 150},
		        { field: 'prodtimelimit', title: '产品时限', width: 150},
		        { field: 'prodtype', title: '产品类型', width: 150},
		        { field: 'sendername', title: '发件人姓名', width: 150},
		        { field: 'senderphone', title: '发件人电话', width: 150},
		        { field: 'senderaddr', title: '发件人地址', width: 150},
		        { field: 'receivername', title: '收件人姓名', width: 150},
		        { field: 'receiverphone', title: '收件人电话', width: 150},
		        { field: 'receiveraddr', title: '收件人地址', width: 150},
		        { field: 'actlweit', title: '实际重量', width: 150},
		        { field: 'createTime', title: '创建时间', width: 120,align: 'center',formatter: formatDatebox}
		    ]],		
		   
		    onDblClickRow :function(rowIndex,rowData){
		    	editBean(rowData);
		   	}

		});
		  
	});
	
	//JS校验form表单信息
	function checkData(){
		var fileDir = $("#importfile").val();
		var suffix = fileDir.substr(fileDir.lastIndexOf("."));
		if("" == fileDir){
			$.messager.alert('操作提示', "选择需要导入的Excel文件！", 'warning');
			return false;
		}
		if(".xls" != suffix && ".xlsx" != suffix ){
			$.messager.alert('操作提示', "选择Excel格式的文件导入！", 'warning');
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$('#importExcel').click(function(){
 		if(checkData()){
 			var fileDir = $("#importfile").val();
 			$('#importfm').ajaxSubmit({  
 				url: '<%=basePath%>quickworkbill/importXls.action?file='+fileDir,
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
 
<div id="dg" style="width:50%;height:250px;"></div>
	<div id="toolbar">
		<div id="tb" style="height:auto;">		
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="exportExcel()">模板下载</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="doImport()">批量导入</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="refresh()">刷新</a>
	</div>	
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