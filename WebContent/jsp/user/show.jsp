<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tgb.entity.User"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href=${pageContext.request.contextPath }/images/LOGO.ico" rel="shortcut icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>集团后台登录页</title>
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
</style>
</head>
<body>
<script type="text/javascript">
var url;
	function newBean(){
		$("#fitemPassword").show();
	    $('#dlg').dialog('open').dialog('setTitle',' ');
	    $('#fm').form('clear');
	    $("[name='userType']").eq(0)[0].checked=true;
	    url = '<%=basePath%>user/add.action';
	    $('#ftitle').html("添加用户");
	}
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
	function editBean(row){
	    if (row){
	        $("#fitemPassword").hide();
	        row.password2=row.password="******";
	        $('#dlg').dialog('open').dialog('setTitle','');
	        $('#fm').form('load',row);
	        url = '<%=basePath%>user/update.action';    
	        $('#ftitle').html("修改用户信息");
	    }
	}
	function destroyBean(){
		var rows = $('#dg').datagrid('getChecked');
	    if (rows.length>0){
	        $.messager.confirm('温馨提示','你真的要删除么?',function(r){
	            if (r){
	            	for(var i=0; i<rows.length; i++){
	            		var row=rows[i];
	             $.post('<%=basePath%>user/delete.action',{id:row.id},function(result){
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
	var id=""; //对应用户的ID
	//修改密码模块
	function upPass(){
		$(".newPass").val(""); //清空
		$(".oldPassMsg").text("");
		$(".newPassMsg").text("");
		$("#newPass").attr("disabled","disabled");
		$("#agPass").attr("disabled","disabled");
		var rows = $('#dg').datagrid('getChecked');
		if(rows.length==0){
			alert("请选择修改的用户");
			return false;
		}
		if(rows.length>1){
			alert("请选择其中一个");
			return false;
		}
		if(rows.length==1){
			$('#upPass').dialog('open').dialog('setTitle','修改密码'); 
			id=rows[0].id;
		}
	}
	
	//点击保存的时候修改密码
	function updatePass(){
		var fals=true;
		if($("#newPass").val()!=$("#agPass").val()){
			$(".newPassMsg").text("两次输入密码不同");
			fals=false;
		}else if($("#newPass").val()==""){
			$(".newPassMsg").text("密码不能为空");
			fals=false;
		}else if($("#agPass").val()==""){
			$(".newPassMsg").text("请再一次输入密码");
			fals=false;
		}else if($("#newPass").val().length<6||$("#newPass").val().length>20){
			$(".newPassMsg").text("密码要大于6小于20");
			fals=false;
		}
		if(fals==true){
			$.ajax({
				url: "<%=basePath%>user/upPass.action",
				type: "POST",
				data: {
					id:id,
					password:$("#newPass").val()
				},
				dataType: "json",
				success: function(result) {
					if(result.msg==1){
						alert("密码修改成功");
						//恢复新密码输入框的禁用同时清空旧密码内容
						$(".newPass").val(""); //清空
						$(".oldPassMsg").text("");
						$(".newPassMsg").text("");
						$("#newPass").attr("disabled","disabled");
						$("#agPass").attr("disabled","disabled");
					}else if(result.msg==0){
						alert("密码修改失败");
					}
				},
				error: function() {
				}
			});
			fals==false;//防止多次点击重复提交
		}	
	}
	//校验模块
	$.extend($.fn.validatebox.defaults.rules, {  
	    equalTo: {
	        validator:function(value,param){
	            return $(param[0]).val() == value;
	        },
	        message:'两次密码不匹配'
	    },
	    username: {// 验证用户名
	                validator: function (value) {
	                    return /^[a-zA-Z0-9_]/i.test(value);
	                },
	                message: '用户名不合法（登录名只能为英文或者数字）'
	    },
	    isExist: {
	        validator:function(value,param){
	        	var r=false;
	        	$.ajax({  
	                type : "post",  
	                 url : "<%=basePath%>user/isExist.action",
	                 data : {id:$("#id").val(),<%=User.USERNAME%>:value}, 
	                 dataType: 'json',
	                 async : false,  
	                 success : function(data){
	                	 if(data==false) r=true;
	                 }  
	            }); 
	            return r;
	        },
	        message:'用户已存在'
	    }
	    

	}); 
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
		    url: '<%=basePath %>user/getUserList.action',
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
		        { field: 'userTrueName', title: '用户名', width: 150},
		        { field: 'userName', title: '用户登录名', width: 150},
		        { field: 'userType', title: '用户角色', width: 136,
		        	 formatter:function(value,rec,index){  
	                    if(value=="ADMIN"){
	                   	 return "管理员";
	                    }else if(value=="USER"){
	                   	 return "业务主管";
	                    }else if(value=="LUSER"){
	                   	 return "业务员";
	                    }else{
	                   	 return "不明";
	                    }
	                }	
		        },
		        { field: 'phone', title: '手机号码', width: 150},
		        { field: 'email', title: '邮箱地址', width: 200},
		        { field: 'loginTime', title: '最近登录时间', width: 180,align: 'center',formatter: formatDatebox}
		    ]],		
		   
		    onDblClickRow :function(rowIndex,rowData){
		    	editBean(rowData);
		   	}

		});
			    
	});
	
	$(function(){
		$("#oldPass").change(function(){
			var oldPassW=$("#oldPass").val();
			$.ajax({
				url: "<%=basePath%>user/findPass.action",
				type: "POST",
				data: {
					id:id,
					password:oldPassW
				},
				dataType: "json",
				success: function(result) {
					if(result.msg==1){
						$(".oldPassMsg").text("密码匹配正确");
						$("#newPass").removeAttr("disabled");
						$("#agPass").removeAttr("disabled");
					}else if(result.msg==0){
						$(".oldPassMsg").text("密码匹配错误");
						$("#newPass").attr("disabled","disabled");
						$("#agPass").attr("disabled","disabled");
					}
				},
				error: function() {
				}
			});
		});	
		
		$("#userName").change(function(){
			var reg = /^([\u4e00-\u9fa5 a-zA-Z]+)$/;
			if(!reg.test($("#userName").val())){
				//alert("联系人只能为中文或者英文");
				alert("联系人只能填写中文或字母", "填写错误");
				$("#userName").val("");
				$("#userName").focus();
			}
		});
		
	});
</script>
 
<div id="dg" style="width:50%;height:250px;"></div>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newBean()">添加用户</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyBean()">删除用户</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="upPass()">修改密码</a>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 580px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle" id="ftitle"></div>
		<form id="fm" method="post"  novalidate>
			<input id="<%=User.USERID %>" name="<%=User.USERID %>"  type="hidden">
			
			<div class="fitem userTrueName">
				<label>用户名:</label>
				<input name="userTrueName" id="userName" class="easyui-validatebox" data-options="required:true,validType:['length[1,10]', 'isExistTure']"/>
			</div>
			<div class="fitem Name">
				<label>用户登录名:</label>
				 <input name="<%=User.USERNAME%>" id="loginName" class="easyui-validatebox" data-options="required:true,validType:['length[3,15]', 'isExist', 'username[\'#loginName\']']"  />
			</div>
			<div id="fitemPassword">
				<div class="fitem fitem_password">
					<label>用户密码:</label>
					 <input id="<%=User.PASSWORD %>" name="<%=User.PASSWORD %>" type="password" class="easyui-validatebox" 
					 	data-options="required:true,validType:['length[5,15]']" />
				</div>
				<div class="<%=User.PASSWORD %>2 fitem fitem_password">
					<label>验证密码:</label>
					 <input name="<%=User.PASSWORD %>2" type="password" class="easyui-validatebox"
					 	data-options="required:true,validType:['equalTo[\'#password\']']"/>
				</div>
			</div>
			<div class="fitem">
				<label>用户角色:</label>
				 <input name="userType" type="radio" value="ADMIN"/>管理员
				 <input name="userType" type="radio" value="USER"/>业务主管
				 <input name="userType" type="radio" value="LUSER"/>业务员
			</div>
			
			<div class="fitem">
				<label>手机号码:</label>
				 <input name="phone" class="easyui-validatebox"
				 	data-options="validType:['length[0,25]']" />
			</div>
			<div class="fitem">
				<label>电子邮件地址:</label>
				<input name="email" class="easyui-validatebox" data-options="validType:['email']" style="width:162px;"/>
			</div>
			
		</form> 
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBean()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"	style="width: 90px">取消</a>
	</div>
	<div id="upPass" class="easyui-dialog" closed="true" style="width:400px; padding:20px 10px;">
		<div style="text-align:left; padding-left:10px; padding-bottom:10px;">
			<div><span class="titlePass">旧密码：</span><input type="text" id="oldPass" class="newPass" placeholder="请输入旧密码"/><span class="oldPassMsg" style="color:#ff0000;"></span></div>			
			<div><span class="titlePass">新密码：</span><input type="text" id="newPass" class="newPass" disabled="disabled" placeholder="请输入新密码"/><span class="newPassMsg" style="color:#ff0000;"></span></div>
			<div><span class="titlePass">　　　　</span><input type="text" id="agPass" class="newPass" disabled="disabled" placeholder="再次输入新密码"/></div>
			<div style="text-align:center; margin-top:25px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updatePass()" style="width: 90px">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-cancel" onclick="javascript:$('#upPass').dialog('close')"	style="width: 90px">取消</a>
			</div>
		</div>
	</div>
</body>

</html>