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
<title>取派员设置</title>
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
var url;
var i=0;
	function add(){
		
		 $('#dlg').dialog('open').dialog('setTitle',' ');
		 //alert(j);
		 if(j==0){
			 if(i==0){
				 loadData();
				 i=1;
			 }
		 }
		 //$(".combo-select").after('<label>收派标准:</label>');
		
		 $('#fm').form('clear');
		 url = '<%=basePath%>staff/add.action';
		
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
		var id=row.sID;
		//alert(i);
		if(i==0){
			if(j==0){
				loadData();
				j=1;
			}
		}
		
	    if (row){
	    	//alert(row.standard);
	        $('#dlg').dialog('open').dialog('setTitle','');
	        //$("option[value="+id+"]").attr("selected",true);
	        $("option[value='"+id+"']").attr("selected",true);
	        $('#fm').form('load',row);
	        url = '<%=basePath%>staff/update.action';    
	        $('#ftitle').html("修改标准信息");
	    }
	}
	
	//修改保存
	function saveBean(){
		//alert($('#staffName').val());
		var a=$('#standardId').val();
		var b=$("option[value='"+a+"']").html();
		$("#standard").val(b);
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
	             $.post('<%=basePath%>staff/delete.action',{id:row.id},function(result){
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
		    url: '<%=basePath %>staff/getStaffList.action',
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
		        { field: 'id', title: '取派员编号', width: 150},
		        { field: 'staffName', title: '姓名', width: 150},
		        { field: 'phone', title: '手机', width: 150},
		        { field: 'station', title: '所属单位', width: 150},
		        { field: 'haspda', title: 'pda', width: 150,
		        	formatter : function(data,row, index){
					if(data=="1"){
						return "有";
					}else{
						return "无";
					}
				}},
				{ field: 'standard', title: '收派标准', width: 150},
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
		    url: "<%=basePath %>standard/ajaxList.action",                                    // 要访问的后台地址
		    complete : function() {}, 
		    success : function(result) {// result为返回的数据
		    	var list=result.standardList;
		    	//$(".station").after('<div><label>收派标准:</label> <select class="easyui-combobox" id="standardId" name="sID" data-options="panelHeight:auto" style="width:165px;height:23px;margin:0px 0px 0px 30px;overflow:auto"></select></div>');
		    	for(var i=0;i<list.length;i++){
		    		//alert(list[i].standardName);
		    		$("#standardId").append('<option value="'+i+'">'+list[i].standardName+'</option>');
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
					var oldbtnstr = "2-1,2-2,2-3,2-4,2-5,2-6";
					var szBtnstr = oldbtnstr.split(",");
					var delBtnstr = "";
					for(var i=0; i<szBtnstr.length; i++){
						if(newNum.indexOf(szBtnstr[i])>=0){
							if(szBtnstr[i]=="2-1"){
								return false;
							}else if(szBtnstr[i]=="2-6"){
								$("#addbtn_qx").remove();
								$("#updatbtn_qx").remove();
								$("#delbtn_qx").remove();
								$("#show_qx").remove();
								return false;
							}
						}else{
							delBtnstr += szBtnstr[i]+",";
						}
					}
					delBtnstr = delBtnstr.substring(0,delBtnstr.length-1);
					var onedel = delBtnstr.split(",");
					for(var i=0; i<onedel.length; i++){
						if(onedel[i]=="2-2"){
							$("#addbtn_qx").remove();
						}else if(onedel[i]=="2-4"){
							$("#updatbtn_qx").remove();
						}else if(onedel[i]=="2-3"){
							$("#delbtn_qx").remove();
						}else if(onedel[i]=="2-5"){
							$("#show_qx").remove();
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
		   		if("2-"==qq.substring(0,2)){
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
			var staffName = $("#staffName").val().trim(); //取派员
			var phone = $("#phone").val().trim(); 		//取派员手机号
			var station = $("#station").val().trim();  //单位 
			var haspda = $("#haspda").val().trim();   //移动设备
			var standard = $("#standard").val().trim();   //收派标准
			var qStarttime = $('#qStarttime').datebox('getValue'); 
			var qEndtime = $('#qEndtime').datebox('getValue');
			console.log(staffName+","+phone+","+station+","+haspda+","+qStarttime+","+qEndtime);
			$('#dg').datagrid({
			    height: '100%',
			    fit:true,
			    url: '<%=basePath %>staff/queryStaffList.action?staffName='+staffName+'&phone='+phone+'&station='+station+'&haspda='+haspda+'&standard='+standard+'&qStarttime='+qStarttime+'&qEndtime='+qEndtime,
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
			        { field: 'id', title: '取派员编号', width: 150},
			        { field: 'staffName', title: '姓名', width: 150},
			        { field: 'phone', title: '手机', width: 150},
			        { field: 'station', title: '所属单位', width: 150},
			        { field: 'haspda', title: 'pda', width: 150,
			        	formatter : function(data,row, index){
						if(data=="1"){
							return "有";
						}else{
							return "无";
						}
					}},
					{ field: 'standard', title: '收派标准', width: 150},
			        { field: 'createTime', title: '创建时间', width: 120,align: 'center',formatter: formatDatebox}
			    ]],		
			   
			    onDblClickRow :function(rowIndex,rowData){
			    	editBean(rowData);
			   	}

			});
		}
</script>
<body >
 <div id="contain" >
	<form id="qfm"  action="">
	  <div id="yhHeader" >
		<div style="margin-bottom:-10px;padding-top:5px;padding-left:10px">
			<span><label class="font_style">姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="staffName" type="text"   placeholder="请输入姓名" style="width:125px;height:20px"></span>
			<span><label class="font_style">手机号：&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="phone" type="text"   placeholder="请输入手机号" style="width:125px;height:20px"></span>
			<span><label class="font_style">单位：&nbsp;&nbsp;&nbsp;</label><input id="station" type="text"   placeholder="请输入单位名称" style="width:125px;height:20px"></span>
			<span><label class="font_style">移动设备：</label><select  id="haspda" style="width:100px;height:25px;">
				<option value="" selected =""></option>
				<option value="1">有</option> 
				<option value="0">无</option>
				</select>	
			</span>
			<span style="width:200px; position:relative; right:-1000px; text-align:center; top:-27px; display:block;  overflow:hidden;"><a href="javascript:void(0)" data-options="iconCls:'icon-search',plain:false" class="yhorder_btn  yhorder_btn_res"  onclick="reset()"><img src="<%=basePath %>js/easyui/themes/default/images/res.png"/><span class="img_class"  >重置</span></a></span>	
		</div>	
		<div style="margin-bottom:-10px;padding-left:10px">
			
			<span><label class="font_style">收派标准：</label><input id="standard" type="text"   placeholder="请输入收派标准" style="width:125px;height:20px"></span>
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
	</div>	
	
	</div>
<div id="dg" style="width:50%;height:250px;"></div>
	<div id="toolbar">
		<div id="tb" style="height:auto;">		
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true" onclick="destroyBean()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editBean(this)">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="shows()">查看</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
	</div>	
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 580px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" >
		<div class="ftitle" id="ftitle"></div>
		<form id="fm" method="post"  novalidate>
				<input id="<%=Staff.STAFFDID %>" name="<%=Staff.STAFFDID %>"  type="hidden">
				<input id="createTime" name="createTime"  type="hidden">
			<div class="fitem ">
				<label>取派员姓名:</label>
				<input name="staffName" id="staffName" class="easyui-validatebox" data-options="required:true,validType:['length[1,10]']"/>
			</div>
			<div class="fitem ">
				<label>取派员电话:</label>
				 <input name="phone" id="phone" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
			<div class="fitem">
				<label>移动设备:</label>
				 <input type="radio" name="haspda" value="0">否</input>
                 <input type="radio" name="haspda" value="1">是</input>
			</div>
			<div class="fitem station">
				<label>所属单位:</label>
				 <input name="station" id="station" class="easyui-textbox" data-options="required:true,validType:['length[3,15]']"  />
			</div>
				<input id="standard" name="standard"  type="hidden">
				<!-- <label>收派标准:</label>
				 <select  id="standardId" name="standard" style="width:160px;"  >
						
				 </select> -->
			 <div>
				<label>收派标准:</label> 
				<select  id="standardId" name="sID" data-options="panelHeight:auto" style="width:165px;height:23px;margin:0px 0px 0px 30px;overflow:auto">
				
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