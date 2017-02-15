<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tgb.entity.Region"%>
<%@page import="com.tgb.entity.Subarea"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href=${pageContext.request.contextPath }/images/LOGO.ico" rel="shortcut icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>分区管理</title>
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
var url;
var i=0;
	function add(){
		
		 $('#dlg').dialog('open').dialog('setTitle',' ');
		 if(j==0){
			 if(i==0){
				 loadData();
				 i=1;
			 }
		 }
		 //$(".combo-select").after('<label>收派标准:</label>');
		
		 $('#fm').form('clear');
		 url = '<%=basePath%>subarea/add.action';
		
		 $('#ftitle').html("添加标准");
	}
	
	function doImport() {  //打开导入excel对话框
		
		$('#importdlg').dialog('open').dialog('setTitle','导入Excel');
		$.messager.alert('操作提示',"请选择d盘根目录下的xls文件");
	}
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
	 				url: '<%=basePath%>subarea/importXls.action?file='+fileDir,
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
		if(i==0){
			if(j==0){
				loadData();
				j=1;
			}
		}
	    if (row){
	        $('#dlg').dialog('open').dialog('setTitle','');
	        $('#fm').form('load',row);
	        url = '<%=basePath%>subarea/update.action';    
	        $('#ftitle').html("修改标准信息");
	    }
	}
	
	//修改保存
	function saveBean(){
		//alert($('#userName').val());
		$("option[value=]")
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
	             $.post('<%=basePath%>subarea/delete.action',{id:row.id},function(result){
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
	//---------------结束-----------------------
	$(function(){
		loadBtn();
		$('#dg').datagrid({
		    height: '100%',
		    fit:true,
		    url: '<%=basePath %>subarea/getSubareaList.action',
		    method: 'POST',
		    striped: true,  //显示条纹
		    nowrap: true,	//设置为true，当数据长度超出列宽时将会自动截取。
		    pageSize: 10,		//当设置分页属性时，初始化每页记录数。
		    pageNumber:1, 	//当设置分页属性时，初始化分页码。
		    pageList: [10, 20, 50, 100, 150, 200],	//当设置分页属性时，初始化每页记录数列表。
		    showFooter: true,	//定义是否显示行底（如果是做统计表格，这里可以显示总计等）
			loadMsg : '数据加载中请稍后……',	//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,		//设置true将在数据表格底部显示分页工具栏。
		    toolbar:"#tb",
		    checkOnSelect:false,
		    selectOnCheck:false,
		    
		    columns: [[
		        { field: 'ck', checkbox: true },
		        { field: 'fid', title: '分拣编号', width: 150},
		        { field: 'region', title: '区域信息', width: 150},
		        { field: 'addressName', title: '关键字', width: 150},
		        { field: 'startNum', title: '起始号', width: 150},
		        { field: 'endNum', title: '结束号', width: 150},
		        { field: 'hasSingle', title: '单双号', width: 150,
		        	formatter : function(data,row, index){
					if(data=="0"){
						return "单双号";
					}else if(data=="1"){
						return "单号";
					}else{
						return "双号";
					}
				}},
				{ field: 'position', title: '位置', width: 150},
		        { field: 'createTime', title: '创建时间', width: 120,align: 'center',formatter: formatDatebox}
		    ]],		
		   
		    onDblClickRow :function(rowIndex,rowData){
		    	editBean(rowData);
		   	}

		});
		  
	});
	function loadData() {
		
	    $.ajax({
	    type : "POST",                                            // 使用post方法访问后台
	    dataType : "json",                                        // 返回json格式的数据
	    url: "<%=basePath %>region/ajaxList.action",                                    // 要访问的后台地址
	    complete : function() {}, 
	    success : function(result) {// result为返回的数据
	    	var list=result.regionList;
	    	//$(".station").after('<div><label>收派标准:</label> <select class="easyui-combobox" id="standardId" name="sID" data-options="panelHeight:auto" style="width:165px;height:23px;margin:0px 0px 0px 30px;overflow:auto"></select></div>');
	    	for(var i=0;i<list.length;i++){
	    		//alert(list[i].standardName);
	    		$("#regionid").append('<option value="'+list[i].province+" "+list[i].city+" "+list[i].district+'">'+list[i].province+" "+list[i].city+" "+list[i].district+'</option>');
	    		//$(".combo-panel").append('<div class="combobox-item" value="'+i+'" >'+list[i].standardName+'</div>');
	    		
	    	}
	    	//$('select').comboSelect();
	    }
	    
	   }); 
	  } 
	
	 //加载用户权限
	 function loadBtn(){
			$.ajax({
				 url : '${pageContext.request.contextPath}/user/getCurrentUser.action',
				 type:"post",
				 dataType : 'json',
				 async:false,
				 success : function(data) {
					var userList=data.userInfo;
					//console.log(userList[0]);
					quanxianNum=userList[0].quanxianNum;
					var newNum=getNum(quanxianNum);
					var oldbtnstr = "4-1,4-2,4-3,4-4,4-5,4-6,4-7,4-8";
					var szBtnstr = oldbtnstr.split(",");
					var delBtnstr = "";
					for(var i=0; i<szBtnstr.length; i++){
						if(newNum.indexOf(szBtnstr[i])>=0){
							if(szBtnstr[i]=="4-1"){
								return false;
							}else if(szBtnstr[i]=="4-8"){
								$("#addbtn_qx").remove();
								$("#updatbtn_qx").remove();
								$("#delbtn_qx").remove();
								$("#show_qx").remove();
								$("#import_qx").remove();
								$("#export_qx").remove();
								return false;
							}
						}else{
							delBtnstr += szBtnstr[i]+",";
						}
					}
					delBtnstr = delBtnstr.substring(0,delBtnstr.length-1);
					var onedel = delBtnstr.split(",");
					for(var i=0; i<onedel.length; i++){
						if(onedel[i]=="4-2"){
							$("#addbtn_qx").remove();
						}else if(onedel[i]=="4-4"){
							$("#updatbtn_qx").remove();
						}else if(onedel[i]=="4-3"){
							$("#delbtn_qx").remove();
						}else if(onedel[i]=="4-5"){
							$("#show_qx").remove();
						}else if(onedel[i]=="4-6"){
							$("#import_qx").remove();
						}else if(onedel[i]=="4-7"){
							$("#export_qx").remove();
						}
					}
					
					
				},
			 });
		}
		
		function getNum(ss){
		 	var dd = ss.split(",");
		   	var newbtn = "";
		   	for(var zz=0;zz<dd.length;zz++){
		   		var qq=dd[zz];
		   		if("4-"==qq.substring(0,2)){
					newbtn += qq+",";
		   		}
		   	}
		   	newbtn = newbtn.substring(0,newbtn.length-1);
		   	return newbtn;
		}
		
	
	
	
	function reset(){ //重置
		$('#qfm').form('clear');
	}
	//-------------------------------查询方法------------------------------
	
	function query(){ //查询 
		var fid = $("#fid").val().trim(); 
		var addressName = $("#addressName").val().trim(); 
		var startNum = $("#startNum").val().trim(); 
		var endNum = $("#endNum").val().trim(); 
		var position = $("#position").val().trim(); 
		var hasSingle = $("#hasSingle").val().trim(); 
		var qStarttime = $('#qStarttime').datebox('getValue');
		var qEndtime = $('#qEndtime').datebox('getValue');
		
		//console.log(standardName+","+minweight+","+maxweight+","+createBy+","+qStarttime+","+qEndtime);
		$('#dg').datagrid({
		    height: '100%',
		    fit:true,
		    url: '<%=basePath %>subarea/querySubareaList.action?fid='+fid+'&addressName='+addressName+'&startNum='+startNum+'&endNum='+endNum+'&position='+position+'&hasSingle='+hasSingle+'&qStarttime='+qStarttime+'&qEndtime='+qEndtime,
		    method: 'POST',
		    striped: true,  //显示条纹
		    nowrap: true,	//设置为true，当数据长度超出列宽时将会自动截取。
		    pageSize: 10,		//当设置分页属性时，初始化每页记录数。
		    pageNumber:1, 	//当设置分页属性时，初始化分页码。
		    pageList: [10, 20, 50, 100, 150, 200],	//当设置分页属性时，初始化每页记录数列表。
		    showFooter: true,	//定义是否显示行底（如果是做统计表格，这里可以显示总计等）
			loadMsg : '数据加载中请稍后……',	//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,		//设置true将在数据表格底部显示分页工具栏。
		    toolbar:"#tb",
		    checkOnSelect:false,
		    selectOnCheck:false,
		    
		    columns: [[
		               { field: 'ck', checkbox: true },
				        { field: 'fid', title: '分拣编号', width: 150},
				        { field: 'region', title: '区域信息', width: 150},
				        { field: 'addressName', title: '关键字', width: 150},
				        { field: 'startNum', title: '起始号', width: 150},
				        { field: 'endNum', title: '结束号', width: 150},
				        { field: 'hasSingle', title: '单双号', width: 150,
				        	formatter : function(data,row, index){
							if(data=="0"){
								return "单双号";
							}else if(data=="1"){
								return "单号";
							}else{
								return "双号";
							}
						}},
						{ field: 'position', title: '位置', width: 150},
				        { field: 'createTime', title: '创建时间', width: 120,align: 'center',formatter: formatDatebox}
		    ]],		
		   
		    onDblClickRow :function(rowIndex,rowData){
		    	editBean(rowData);
		   	}

		});
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
			location.href = "<%=basePath %>subarea/exportExcel.action?Nums="+Nums;
	 	} 
	}
</script>
 <body >
 <div id="contain" >
	<form id="qfm"  action="">
	  <div id="yhHeader" >
		<div style="margin-bottom:-10px;padding-top:5px;padding-left:10px">
			<span><label class="font_style">分拣编号：</label><input id="fid" type="text"   placeholder="请输入编号" style="width:125px;height:20px"></span>
			<span><label class="font_style">关键字：</label><input id="addressName" type="text"   placeholder="请输入关键字" style="width:125px;height:20px"></span>
			<span><label class="font_style">起始号&nbsp;&nbsp;&nbsp;&nbsp;：</label><input id="startNum" type="text"  placeholder="请输入起始号" style="width:125px;height:20px"></span>
			<span><label class="font_style">　至　　</label><input id="endNum" type="text" placeholder="请输入结束号"  style="width:125px;height:20px;"></span>															
			<span style="width:200px; position:relative; right:-1000px; text-align:center; top:-27px; display:block;  overflow:hidden;"><a href="javascript:void(0)" data-options="iconCls:'icon-search',plain:false" class="yhorder_btn  yhorder_btn_res"  onclick="reset()"><img src="<%=basePath %>js/easyui/themes/default/images/res.png"/><span class="img_class"  >重置</span></a></span>	
		</div>	
		<div style="margin-bottom:-10px;padding-left:10px">
			<span><label class="font_style">位置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：</label><input id="position" type="text"   placeholder="请输入具体位置" style="width:125px;height:20px"></span>
			<span><label class="font_style">单双号：</label><select id="hasSingle" style="width:130px;height:25px;">
				<option value="" selected =""></option>
				<option value="0">单双号</option> 
				<option value="1">单号</option>
				<option value="2">双号</option>
				</select>	
			</span>
			<span><label class="font_style">创建时间：</label><input id="qStarttime" class="easyui-datebox"  style="width:130px;height:25px"></span>
			<span><label class="font_style">　至　　</label><input id="qEndtime" class="easyui-datebox"  style="width:130px;height:25px;"></span>															
			<span style="width:200px; position:relative; right:-1000px; text-align:center; top:-27px; display:block;  overflow:hidden;"><a href="javascript:void(0)" data-options="iconCls:'icon-search',plain:false"  class="yhorder_btn yhorder_btn_find"  onclick="query()"><img src="<%=basePath %>js/easyui/themes/default/images/find.png"/><span class="img_class"  >查询</span></a></span>			
		</div>
	  </div>		
		
	</form>
	
	<div style="width:100%;height:420px;">
		<div id="dg" style="width:100%;height:420px;">
		</div>
	</div>
	
	<div id="tb" style="height:auto;">		
		<a id="delbtn_qx" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true" onclick="destroyBean()">删除</a>
		<a id="updatbtn_qx" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editBean(this)">修改</a>
		<a id="show_qx" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="shows()">查看</a>
		<a id="addbtn_qx" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
		<a id="import_qx" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="doImport()">导入</a>
		<a id="export_qx" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="exportExcel()">导出</a>
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
	  

	<div id="dlg" class="easyui-dialog"
		style="width: 580px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" >
		<div class="ftitle" id="ftitle"></div>
		<form id="fm" method="post"  novalidate>
				<input id="<%=Subarea.FQID %>" name="<%=Subarea.FQID %>"  type="hidden">
			<div class="fitem ">
				<label>选择区域:</label>
				<select  id="regionid" name="region" data-options="panelHeight:auto" style="width:165px;height:23px;overflow:auto">
				
				</select>
			</div>
			<div class="fitem ">
				<label>分拣编号:</label>
				 <input name="fid" id="fid" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
			<div class="fitem ">
				<label>关键字:</label>
				 <input name="addressName" id="addressName" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
			<div class="fitem ">
				<label>起始号:</label>
				 <input name="startNum" id="startNum" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
			<div class="fitem ">
				<label>终止号:</label>
				 <input name="endNum" id="endNum" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
			<div class="fitem">
				<label>单双号:</label>
				<select class="easyui-combobox" name="hasSingle" style="width:162px;">  
				    <option value="0">单双号</option>  
				    <option value="1">单号</option>  
				    <option value="2">双号</option>  
				</select>
			</div>
			<div class="fitem ">
				<label>位置信息:</label>
				 <input name="position" id="position" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
			
			
		</form> 
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBean()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"	style="width: 90px">取消</a>
	</div>
	
</body>

</html>