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
<title>工作单快速录入</title>
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
//成员变量，用来保存当前正在编辑行的行号，控制用户当前只能同时编辑一行
var editIndex ;

// 点击新增一行
function doAdd(){
	// 判断当前是否正在编辑 
	if(editIndex != undefined){
		$("#grid").datagrid('endEdit',editIndex); // 结束当前行编辑
		// 触发onAfterEdit 函数
	}
	// 判断当前已经没有编辑行
	if(editIndex==undefined){ 
		// 在数据表格第一行 ，插入一个空行
		$("#grid").datagrid('insertRow',{
			index : 0,
			row : {}
		});
		// 打开第一行编辑状态
		$("#grid").datagrid('beginEdit',0);
		// 将编辑的行号，保存成员变量
		editIndex = 0;
	}
}

// 点击保存 
function doSave(){
	$("#grid").datagrid('endEdit',editIndex );
	// 执行 doAfterEdit 事件
}

function doCancel(){
	if(editIndex!=undefined){
		$("#grid").datagrid('cancelEdit',editIndex);
		$("#grid").datagrid('deleteRow',editIndex);
		editIndex = undefined;
	}
}

//工具栏
var toolbar = [ {
	id : 'button-add',	
	text : '新增一行',
	iconCls : 'icon-edit',
	handler : doAdd
}, {
	id : 'button-cancel',
	text : '取消编辑',
	iconCls : 'icon-cancel',
	handler : doCancel
}, {
	id : 'button-save',
	text : '保存',
	iconCls : 'icon-save',
	handler : doSave
}];
// 定义列
var columns = [ [ {
	field : 'id',
	title : '工作单号',
	width : 120,
	align : 'center',
	editor :{
		type : 'validatebox',
		options : {
			required: true
		}
	}
}, {
	field : 'arrivecity',
	title : '到达地',
	width : 120,
	align : 'center',
	editor :{
		type : 'validatebox',
		options : {
			required: true
		}
	}
},{
	field : 'product',
	title : '产品',
	width : 120,
	align : 'center',
	editor :{
		type : 'validatebox',
		options : {
			required: true
		}
	}
}, {
	field : 'num',
	title : '件数',
	width : 120,
	align : 'center',
	editor :{
		type : 'numberbox',
		options : {
			required: true
		}
	}
}, {
	field : 'weight',
	title : '重量',
	width : 120,
	align : 'center',
	editor :{
		type : 'validatebox',
		options : {
			required: true
		}
	}
}, {
	field : 'floadreqr',
	title : '配载要求',
	width : 220,
	align : 'center',
	editor :{
		type : 'validatebox',
		options : {
			required: true
		}
	}
}] ];

$(function(){
	// 先将body隐藏，再显示，不会出现页面刷新效果
	$("body").css({visibility:"visible"});
	
	// 收派标准数据表格
	$('#grid').datagrid( {
		iconCls : 'icon-forward',
		fit : true,
		border : true,
		rownumbers : true,
		striped : true,
		nowrap: true,	//设置为true，当数据长度超出列宽时将会自动截取。
		pageSize: 10,		//当设置分页属性时，初始化每页记录数。
		pageNumber:1, 	//当设置分页属性时，初始化分页码。
		pageList: [10, 20, 50, 100, 150, 200],
		pagination : true,
		toolbar : toolbar,
		url :  "${pageContext.request.contextPath}/quickworkbill/getQuickbillList.action",
		idField : 'id',
		columns : columns,
		onDblClickRow : doDblClickRow,
		onAfterEdit : function(rowIndex, rowData, changes){
			editIndex = undefined; // 将当前正在编辑行 设置undefined
			// 提交ajax请求，将编辑行数据，以ajax方式，发送到服务器，完成保存 
			$.post("${pageContext.request.contextPath}/quickworkbill/add.action",rowData , function(data){
				//alert(data.map.result);
				console.log(data);
				// 判断data.result 是否 为 success 
				if(data.result){
					$('#grid').datagrid('reload');
				}else{
					$.messager.alert('保存失败',data.msg, 'error');
				}
			},"json");
		}
	});
});

function doDblClickRow(rowIndex, rowData){
	alert("双击表格数据...");
	console.info(rowIndex);
	$('#grid').datagrid('beginEdit',rowIndex);
	editIndex = rowIndex;
}

// 搜索函数
function doSearch(value,name){
	// alert("搜索项："+name + ", 搜索内容：" + value);
	// 将查询条件 缓存到 datagrid
	$('#grid').datagrid('load',{
		conditionName : name,
		conditionValue : value
	});
}

</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div data-options="region:'north'">
	<!-- 编写搜索框 -->
	<!--
		 prompt 默认提示内容
		 menu 搜索条件下拉选项 
		 searcher 点击搜索按钮执行js函数名称
	 -->
	<input id="ss" class="easyui-searchbox" style="width:300px" 
		data-options="prompt:'请输入您的查询内容',menu:'#mm',searcher:doSearch"/>
		
	<div id="mm">
		<div data-options="name:'arrivecity'">按照到达地搜索</div>
		<div data-options="name:'product'">按照货物名称搜索</div>
	</div>
</div>
<div region="center" border="false">
	<table id="grid"></table>
</div>
</body>
</html>