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
<title>收派标准</title>
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
	function add(){
		 $('#dlg').dialog('open').dialog('setTitle',' ');
		 $('#fm').form('clear');
		 url = '<%=basePath%>standard/add.action';
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
	
	//编辑操作
	function editBean(row){
	    if (row){
	        $('#dlg').dialog('open').dialog('setTitle','');
	        $('#fm').form('load',row);
	        url = '<%=basePath%>standard/update.action';    
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
	             $.post('<%=basePath%>standard/delete.action',{id:row.id},function(result){
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
		$('#dg').datagrid({
		    height: '100%',
		    fit:true,
		    url: '<%=basePath %>standard/getStandardList.action',
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
		        { field: 'standardName', title: '标准名称', width: 150},
		        { field: 'minweight', title: '最小重量', width: 150},
		        { field: 'maxweight', title: '最大重量', width: 150},
		        { field: 'createBy', title: '操作人', width: 150},
		        { field: 'updateTime', title: '更新时间', width: 120,align: 'center',formatter: formatDatebox}
		    ]],		
		   
		    onDblClickRow :function(rowIndex,rowData){
		    	editBean(rowData);
		   	}

		});
			    
	});
	
	function reset(){ //重置
		$('#qfm').form('clear');
	}
	//-------------------------------查询方法------------------------------
	
	function query(){ //查询 
		var standardName = $("#standardName").val().trim(); 
		var minweight = $("#minweight").val().trim(); 
		var maxweight = $("#maxweight").val().trim(); 
		var createBy = $("#createBy").val().trim(); 
		var qStarttime = $('#qStarttime').datebox('getValue');
		var qEndtime = $('#qEndtime').datebox('getValue');
		console.log(standardName+","+minweight+","+maxweight+","+createBy+","+qStarttime+","+qEndtime);
		$('#dg').datagrid({
		    height: '100%',
		    fit:true,
		    url: '<%=basePath %>standard/queryStandardList.action?standardName='+standardName+'&minweight='+minweight+'&maxweight='+maxweight+'&createBy='+createBy+'&qStarttime='+qStarttime+'&qEndtime='+qEndtime,
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
		        { field: 'standardName', title: '标准名称', width: 150},
		        { field: 'minweight', title: '最小重量', width: 150},
		        { field: 'maxweight', title: '最大重量', width: 150},
		        { field: 'createBy', title: '操作人', width: 150},
		        { field: 'updateTime', title: '更新时间', width: 120,align: 'center',formatter: formatDatebox}
		    ]],		
		   
		    onDblClickRow :function(rowIndex,rowData){
		    	editBean(rowData);
		   	}

		});
	}
</script>
 
<body>
<div id="contain" >
	<form id="qfm"  action="">
	  <div id="yhHeader" >
		<div style="margin-bottom:-10px;padding-top:5px;padding-left:10px">
			<span><label class="font_style">标准名称：</label><input id="standardName" class="easyui-textbox"  placeholder="请输入标准名称" style="width:125px;height:20px"></span>
			<span><label class="font_style">重量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="minweight" type="text"   placeholder="最小重量（单位kg）" style="width:125px;height:20px"></span>
			<span><span><label class="font_style">　       至　　</label><input id="maxweight" type="text"  placeholder="最大重量（单位kg）" style="width:125px;height:20px"></span>
			<span style="width:200px; position:relative; right:-1000px; text-align:center; top:-27px; display:block;  overflow:hidden;"><a href="javascript:void(0)" data-options="iconCls:'icon-search',plain:false" class="yhorder_btn  yhorder_btn_res"  onclick="reset()"><img src="<%=basePath %>js/easyui/themes/default/images/res.png"/><span class="img_class"  >重置</span></a></span>	
		</div>	
		<div style="margin-bottom:-10px;padding-left:10px">
			<span><label class="font_style">操作人：&nbsp;&nbsp;&nbsp;</label><input id="createBy" type="text"   placeholder="请输入操作人名称" style="width:125px;height:20px"></span>
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
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true" onclick="destroyBean()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editBean(this)">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="shows()">查看</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
	</div>	
	
	</div>
	  
	<div id="dlg" class="easyui-dialog"
		style="width: 580px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle" id="ftitle"></div>
		<form id="fm" method="post"  novalidate>
			<input id="<%=Standard.STANDARDID %>" name="<%=Standard.STANDARDID %>"  type="hidden">
			
			<div class="fitem ">
				<label>标准名称:</label>
				<input name="standardName" id="standardName" class="easyui-validatebox" data-options="required:true,validType:['length[1,10]']"/>
			</div>
			<div class="fitem ">
				<label>最小重量:</label>
				 <input name="minweight" id="minweight" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
			<div class="fitem">
				<label>最大重量:</label>
				 <input name="maxweight" id="maxweight" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
		</form> 
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBean()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"	style="width: 90px">取消</a>
	</div>
	
</body>

</html>