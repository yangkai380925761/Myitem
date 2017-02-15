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
<title>人工调度</title>
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
	
	function getStaffInfo(value){
		var staffName=null;
		var phone=null;
		var station=null;
		 $.ajax({
		    type : "POST",                                            // 使用post方法访问后台
		    data: {name:value},
		    dataType : "json",                                        // 返回json格式的数据
		   
		    url: "<%=basePath %>staff/findInfoByName.action",                                    // 要访问的后台地址
		    complete : function() {},
		    async:false,
		    success : function(result) {// result为返回的数据
		    	var staff=result.staff;
		   		 //console.log(staff[0]);
		    	//return staff[0].staffName+"/"+staff[0].phone+"/"+staff[0].station;
		    	staffName=staff[0].staffName;
		    	phone=staff[0].phone;
		    	station=staff[0].station; 
		    }
		    
		   }); 
		return staffName+"/"+phone+"/"+station;
	}
	//---------------结束-----------------------
	$(function(){
		$('#dg').datagrid({
		    height: '100%',
		    fit:true,
		    url: '<%=basePath %>dqarea/getDqareaList.action',
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
		        { field: 'id', title: '编号', width: 150},
		        { field: 'linkman', title: '联系人', width: 150},
		        { field: 'linkNum', title: '电话', width: 150},
		        { field: 'product', title: '产品', width: 150},
		        { field: 'pickaddress', title: '取件地址', width: 150},
		        { field: 'pickdate', title: '取件日期', width: 150},
		        { field: 'createTime', title: '创建时间', width: 120,align: 'center',formatter: formatDatebox}
		    ]],		
		   
		    onDblClickRow :function(rowIndex,rowData){
		    	editBean(rowData);
		   	}

		});
		  
	});
	 
	 
</script>
 
<div id="dg" style="width:50%;height:250px;"></div>
	<div id="toolbar">
		<div id="tb" style="height:auto;">		
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="destroyBean()">人工调度</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="destroyBean()">保存</a>
	</div>	
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 580px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" >
		<div class="ftitle" id="ftitle"></div>
		<form id="fm" method="post"  novalidate>
				<input id="<%=Dqarea.DQAREAID %>" name="<%=Dqarea.DQAREAID %>"  type="hidden">
			
			<div class="fitem ">
				<label>选择负责人:</label>
				<select  id="staffid" name="staff" data-options="panelHeight:auto" style="width:165px;height:23px;margin:0px 0px 0px 30px;overflow:auto">
				
				</select>
			</div>
			
		</form> 
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBean()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"	style="width: 90px">取消</a>
	</div>
</body>

</html>