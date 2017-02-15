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
<title>定区管理</title>
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
		 if(j==0){
			 if(i==0){
				 loadData();
				 loadData1();
				 i=1;
			 }
		 }
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
		if(i==0){
			if(j==0){
				loadData();
				loadData1();
				j=1;
			}
		}
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
		loadBtn();
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
		    toolbar:"#tb",
		    checkOnSelect:false,
		    selectOnCheck:false,
		    
		    columns: [[
		        { field: 'ck', checkbox: true },
		        { field: 'did', title: '定区编号', width: 150},
		        { field: 'dqName', title: '定区名称', width: 150},
				{ field: 'staff', title: '负责人/联系电话/所属公司', width: 200,align: 'center',formatter: getStaffInfo},
		        { field: 'createTime', title: '创建时间', width: 120,align: 'center',formatter: formatDatebox}
		    ]],		
		   
		    onDblClickRow :function(rowIndex,rowData){
		    	editBean(rowData);
		   	}

		});
		  
	});
	
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
					var oldbtnstr = "5-1,5-2,5-3,5-4,5-5,5-6,5-7";
					var szBtnstr = oldbtnstr.split(",");
					var delBtnstr = "";
					for(var i=0; i<szBtnstr.length; i++){
						if(newNum.indexOf(szBtnstr[i])>=0){
							if(szBtnstr[i]=="5-1"){
								return false;
							}else if(szBtnstr[i]=="5-7"){
								$("#addbtn_qx").remove();
								$("#updatbtn_qx").remove();
								$("#delbtn_qx").remove();
								$("#show_qx").remove();
								$("#lianxi_qx").remove();
								return false;
							}
						}else{
							delBtnstr += szBtnstr[i]+",";
						}
					}
					delBtnstr = delBtnstr.substring(0,delBtnstr.length-1);
					var onedel = delBtnstr.split(",");
					for(var i=0; i<onedel.length; i++){
						if(onedel[i]=="5-2"){
							$("#addbtn_qx").remove();
						}else if(onedel[i]=="5-4"){
							$("#updatbtn_qx").remove();
						}else if(onedel[i]=="5-3"){
							$("#delbtn_qx").remove();
						}else if(onedel[i]=="5-5"){
							$("#show_qx").remove();
						}else if(onedel[i]=="5-6"){
							$("#lianxi_qx").remove();
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
		   		if("5-"==qq.substring(0,2)){
					newbtn += qq+",";
		   		}
		   	}
		   	newbtn = newbtn.substring(0,newbtn.length-1);
		   	return newbtn;
		}
		
	
	
	 function loadData() {
		
		    $.ajax({
		    type : "POST",                                            // 使用post方法访问后台
		    dataType : "json",                                        // 返回json格式的数据
		    url: "<%=basePath %>staff/ajaxList.action",                                    // 要访问的后台地址
		    complete : function() {}, 
		    success : function(result) {// result为返回的数据
		    	var list=result.staffList;
		    	for(var i=0;i<list.length;i++){
		    		//alert(list[i].standardName);
		    		$("#staffid").append('<option value="'+list[i].staffName+'">'+list[i].staffName+'</option>');
		    	}
		    }
		    
		   }); 
		  } 
	 function loadData1() {
		
		    $.ajax({
		    type : "POST",                                            // 使用post方法访问后台
		    dataType : "json",                                        // 返回json格式的数据
		    url: "<%=basePath %>subarea/ajaxList.action",                                    // 要访问的后台地址
		    complete : function() {}, 
		    success : function(result) {// result为返回的数据
		    	var list=result.subareaList;
		    	for(var i=0;i<list.length;i++){
		    		//alert(list[i].standardName);
		    		$("#subareaid").append('<option value="'+list[i].id+'">'+list[i].addressName+" "+list[i].position+'</option>');
		    	}
		    }
		    
		   }); 
		  } 
	 
	 function reset(){ //重置
			$('#qfm').form('clear');
		}
		//-------------------------------查询方法------------------------------
		
		function query(){ //查询 
			var dqName = $("#dqName").val().trim(); 
			var did = $("#did").val().trim(); 
			var qStarttime = $('#qStarttime').datebox('getValue');
			var qEndtime = $('#qEndtime').datebox('getValue');
			//console.log(standardName+","+minweight+","+maxweight+","+createBy+","+qStarttime+","+qEndtime);
			$('#dg').datagrid({
			    height: '100%',
			    fit:true,
			    url: '<%=basePath %>dqarea/queryDqareaList.action?dqName='+dqName+'&did='+did+'&qStarttime='+qStarttime+'&qEndtime='+qEndtime,
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
			        { field: 'did', title: '定区编号', width: 150},
			        { field: 'dqName', title: '定区名称', width: 150},
					{ field: 'staff', title: '负责人/联系电话/所属公司', width: 200,align: 'center',formatter: getStaffInfo},
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
			<span><label class="font_style">定区名称：</label><input id="dqName"   placeholder="请输入定区名称" style="width:125px;height:20px"></span>
			<span><label class="font_style">定区编号：</label><input id="did"   placeholder="请输入定区编号" style="width:125px;height:20px"></span>
			<span style="width:200px; position:relative; right:-1000px; text-align:center; top:-27px; display:block;  overflow:hidden;"><a href="javascript:void(0)" data-options="iconCls:'icon-search',plain:false" class="yhorder_btn  yhorder_btn_res"  onclick="reset()"><img src="<%=basePath %>js/easyui/themes/default/images/res.png"/><span class="img_class"  >重置</span></a></span>	
		</div>	
		<div style="margin-bottom:-10px;padding-left:10px">
			<span><label class="font_style">创建时间：</label><input id="qStarttime" class="easyui-datebox"  style="width:130px;height:25px"></span>
			<span><label class="font_style">　至　&nbsp;&nbsp;&nbsp;　</label><input id="qEndtime" class="easyui-datebox"  style="width:130px;height:25px;"></span>															
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
		<a id="lianxi_qx" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-sum',plain:true" onclick="add()">关联客户</a>
	</div>	
	
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 580px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" >
		<div class="ftitle" id="ftitle"></div>
		<form id="fm" method="post"  novalidate>
				<input id="<%=Dqarea.DQAREAID %>" name="<%=Dqarea.DQAREAID %>"  type="hidden">
			<div class="fitem ">
				<label>定区编号:</label>
				<input name="did" id=did  />
			</div>
			<div class="fitem ">
				<label>定区名称:</label>
				<input name="dqName" id=dqName />
			</div>
			<div class="fitem ">
				<label>选择负责人:</label>
				<select  id="staffid" name="staff" data-options="panelHeight:auto" style="width:165px;height:23px;overflow:auto">
				
				</select>
			</div>
			
			<div class="fitem ">
				<label>关联分区:</label>
				<select  id="subareaid" name="subarea" data-options="panelHeight:auto" style="width:165px;height:23px;overflow:auto">
				
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