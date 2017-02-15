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
		//var rows = $('#dg').datagrid('getChecked');
	    if (row){
	        $("#fitemPassword").hide();
	        row.password2=row.password="******";
	        
	        $('#fm').form('load',row);
	        $("#hasMenu tr").remove();
	        //加载原来的权限
	        var oldqxMenu = row.quanxianMenu;
	        var oldqxNum = row.quanxianNum;
	        if(oldqxMenu!=""&&oldqxNum!=""){
	    	    var splOldMenu = oldqxMenu.split(",");
	    	    var splOldNum = oldqxNum.split(",");
	    	    for(var i=0; i<splOldMenu.length; i++){
	    	    	var oneCh = splOldMenu[i].split("_");
	    	   		var liList = '<tr style="height:25px;"><td>'+oneCh[0]+'</td><td>'+oneCh[1]+'</td><td><img src="<%=basePath%>images/jianImg.png" onclick="deleteThis(this)" style="width:15px; cursor:pointer;"/><span class="numMenu" style="display:none;">'+splOldNum[i]+'</span></td></tr>';
	    			$("#hasMenu").append(liList);
	    	    }
	        }
	        
	        $('#dlg').dialog('open').dialog('setTitle','');
	        //$('#fm').form('load',row);
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
	//实现增删改查的权限
	
	
	var i=0;
	function setqx(){
		//$("#hasMenu tr").remove();
		
		$('#setquanxian').dialog('open').dialog('setTitle','设置菜单权限');
		if(i==0){
			loadData();
			i=1;
		}
	}
	
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
	
	
	//-------------------用户权限--------------------
	
	function addEentry(){
	var bossMenu = $("#menuBoss").val();
	var chendMenu = $("#menuChildren").val(); //getValue
	if(bossMenu=="==选择菜单模块=="){
		alert("菜单模块选择错误");
		return false;
	}
	var lenNumMenu = $(".numMenu").length;
	var resNumM = "";
	for(var i=0; i<lenNumMenu; i++){
		var num = $(".numMenu:eq("+i+")").text();
		resNumM += num+",";
	}
	var newResNumM = resNumM.substring(0,resNumM.length-1);
	var oneResNumM = newResNumM.split(",");
	for(var i=0; i<oneResNumM.length; i++){
		if(oneResNumM[i]==bossMenu+"-"+chendMenu||oneResNumM[i]==bossMenu+"-0"){
			alert("重复了");
			return false;
		}
		if(bossMenu+"-"+chendMenu==bossMenu+"-0"){
			var pp = "";
			if(oneResNumM[i].length==4){
				pp = oneResNumM[i].substring(0,2);
			}else{
				pp = oneResNumM[i].substring(0,1);
			}
			if(bossMenu==pp){
				alert("重复了");
				return false;
			}
		}
	}
	if(bossMenu+"-"+chendMenu=="10-6"){
		if(utype=="LUSER"){
			alert("这个功能不支持分配给业务员");
			return false;
		}
	}
	var getBossMenu = $("#menuBoss").find("option:selected").text();
	var getChendMenu = $("#menuChildren").find("option:selected").text(); //getValue
	var liList = '<tr style="height:25px;"><td>'+getBossMenu+'</td><td>'+getChendMenu+'</td><td><img src="<%=basePath%>images/jianImg.png" onclick="deleteThis(this)" style="width:15px; cursor:pointer;"/><span class="numMenu" style="display:none;">'+bossMenu+'-'+chendMenu+'</span></td></tr>';
	$("#hasMenu").append(liList);
}
	
function deleteThis(inc){
		$(inc).parents("tr").remove();
}

function changeChidren(inc){
	//alert(inc.value);
	var n=inc.value;
	if(n==1||n=="1"){
		$("#menuChildren").append('<option value="1">全部</option><option value="2">增加</option><option value="3">删除</option><option value="4">修改</option><option value="5">查看</option><option value="6">只显示数据</option>');
	}else if(n==2||n=="2"){
		//$("#menuChildren").find("option").remove();
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option><option value="2">增加</option><option value="3">删除</option><option value="4">修改</option><option value="5">查看</option><option value="6">只显示数据</option>');
	}else if(n==3||n=="3"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option><option value="2">增加</option><option value="3">删除</option><option value="4">修改</option><option value="5">查看</option><option value="6">批量导入</option><option value="7">只显示数据</option>');
	}else if(n==4||n=="4"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option><option value="2">增加</option><option value="3">删除</option><option value="4">修改</option><option value="5">查看</option><option value="6">导入</option><option value="7">导出</option><option value="8">只显示数据</option>');
	}else if(n==5||n=="5"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option><option value="2">增加</option><option value="3">删除</option><option value="4">修改</option><option value="5">查看</option><option value="6">关联客户</option><option value="7">只显示数据</option>');
	}else if(n==6||n=="6"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option>');
	}else if(n==7||n=="7"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option>');
	}else if(n==8||n=="8"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option><option value="2">模板下载</option><option value="3">批量导入</option>');
	}else if(n==9||n=="9"){
		
	}else if(n==10||n=="10"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option>');
	}else if(n==11||n=="11"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option>');
	}else if(n==11||n=="12"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option>');
	}else if(n==11||n=="13"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option>');
	}else if(n==11||n=="14"){
		$("#menuChildren").empty();
		$("#menuChildren").append('<option value="1">全部</option>');
	}
}

function loadData() {
	
	$.ajax({
		url : '${pageContext.request.contextPath}/json/menu.json',
		dataType : 'text',
		success : function(data) {
			var zNodes = eval("(" + data + ")");
	    	for(var i=0;i<zNodes.length;i++){
	    		if(zNodes[i].name=="基础数据"||zNodes[i].name=="受理"||zNodes[i].name=="调度"||zNodes[i].name=="中转配送流程管理"){
	    			continue;
	    		}
	    		console.log(zNodes[i]);
	    		$("#menuBoss").append('<option value="'+i+'">'+zNodes[i].name+'</option>');
	    		
	    	}
    	//$('select').comboSelect();
    }
    
   }); 
  } 
  //保存选定的权限
function yesSet(){
	var len = $("#hasMenu tr").length;
	var menuValue = "";
	for(var i=0; i<len; i++){
		var mokuaiValue = $("#hasMenu tr:eq("+i+") td:eq(0)").text();
		var funcValue = $("#hasMenu tr:eq("+i+") td:eq(1)").text();
		menuValue += mokuaiValue+"_"+funcValue+",";
	}
	//$("#quanxianMenu").textbox("setValue", menuValue.substring(0,menuValue.length-1));
	$("#quanxianMenu").val(menuValue.substring(0,menuValue.length-1));
	
	var lenNumMenu = $(".numMenu").length;
	var resNumM = "";
	for(var i=0; i<lenNumMenu; i++){
		var num = $(".numMenu:eq("+i+")").text();
		resNumM += num+",";
	}
	$("#quanxianNum").val(resNumM.substring(0,resNumM.length-1));
	
	$('#setquanxian').dialog('close');
}
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
				<label>菜单权限：</label>
				<input id="quanxianMenu" name="quanxianMenu"  readonly/>
				<input id="setQuan" onclick="setqx()" type="button" value="设置" style="width:50px; height:22px;">
				<input type="hidden" id="quanxianNum" name="quanxianNum" readonly/>
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
	
	<div id="setquanxian" class="easyui-dialog" style="width:400px; height:350px; padding:10px 20px" closed="true" buttons="#set-buttons">
		<div id="menuChexBox">
			<select  id="menuBoss" name="menuBoss" onchange="changeChidren(this)" style="width:130px; height:25px;">
					<option>==选择菜单模块==</option>
					<!-- <option value="1">用户管理</option>
					<option value="2">订单管理</option>
					<option value="3">导入导出</option>
					<option value="4">已分配订单</option>
					<option value="5">垃圾箱</option>
					<option value="6">费用配置</option>
					<option value="7">订单分配</option>
					<option value="8">客服信息</option>
					<option value="9">任务分配</option>
					<option value="10">绩效管理</option>
					<option value="11">短信记录</option> -->
			</select>
			<select  id="menuChildren" name="menuChildren"  style="width:100px; height:25px;">
					<!-- <option value="0">全部</option>
					<option value="1">删除</option>
					<option value="2">修改</option>
					<option value="3">新增</option>
					<option value="4">查找</option> -->
			</select>
			<a type="button" onclick="addEentry()" iconCls="icon-add" class="easyui-linkbutton" style="width:60px; height:25px;">添加</a>
			<!-- <input id="addMenu" type="button" value="添加" /> -->
		</div>
		<div>
			<div style="border-bottom:1px solid #cccccc; padding: 20px 0px 5px; color:#666666;"><span>拥有权限</span></div>
			<table id="hasMenu" border="0" cellpadding="0" cellspacing="0" width="100%"></table>
		</div>
	</div>
	
	<div id="set-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="yesSet()" style="width: 90px">设置</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-cancel" onclick="javascript:$('#setquanxian').dialog('close')"	style="width: 90px">取消</a>
	</div>
</body>

</html>