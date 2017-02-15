<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理中心</title>
<link href=${pageContext.request.contextPath }/images/LOGO.ico" rel="shortcut icon" type="image/x-icon" />
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript">
	//初始化ztree菜单
	$(function(){
		var setting = {
				data : {
					key : {
						title : "t" // 鼠标在停留在菜单上提示
					},
					simpleData : { // 简单数据 
						enable : true,
						pIdKey: "pId",
					}
				},
				callback : {
					onClick : onClick
				}
			};
		
		
		var quanxianNum;
		// 基本功能菜单加载
		$.ajax({
			url : '${pageContext.request.contextPath}/json/menu.json',
			dataType : 'text',
			success : function(data) {
				//console.log(data);
				var zNodes = eval("(" + data + ")");
				console.log(zNodes);
				console.log(zNodes[0]);
				
				 $.ajax({
					 url : '${pageContext.request.contextPath}/user/getCurrentUser.action',
					 type:"post",
					 dataType : 'json',
					 async:false,
					 success : function(data) {
						var userList=data.userInfo;
						//console.log(data.userList);
						//console.log(userList[0]);
						quanxianNum=userList[0].quanxianNum;
					},
				 });
				 var arr=quanxianNum.split(",");
				 var znode;
				 var newZnode=[];
				 var newZnodes=[];
				 var k=0;//基础数据
				 var q=0;//业务受理
				 var w=0;//调度
				 var e=0;//中转配送
				 var flag;
				 for(var i=0;i<arr.length;i++){
					 var one=arr[i];
					 if(one.length==3){
						 znode=one.substring(0,1);
						 //alert(zNodes[0].pageNum);
						for(var j=0;j<zNodes.length;j++){
							if(znode==zNodes[j].pageNum){
								newZnode[i]=zNodes[j];
								if(j>=1&&j<=5){
									k=1;
								}
								if(j>=7&&j<=9){
									q=1;
								}
								if(j>=11&&j<=12){
									w=1;
								}
								if(j>=14&&j<=16){
									e=1;
								}
							}
						}
					 }else{
						 znode=one.substring(0,2);
						 for(var j=0;j<zNodes.length;j++){
								if(znode==zNodes[j].pageNum){
									newZnode[i]=zNodes[j];
									if(j>=1&&j<=5){
										k=1;
									}
									if(j>=7&&j<=9){
										q=1;
									}
									if(j>=11&&j<=12){
										w=1;
									}
									if(j>=14&&j<=16){
										e=1;
									}
								}
							}
					 }
				 }
				 /* for(var o=0;o<newZnode.length;o++){
					 object a=newZnode[o];
					 for(var p=o+1;p<newZnode.length;p++){
						 if(a==newZnode[p]){
							 
						 }
					 }
				 } */
				 console.log("原来的：");
				 console.log(newZnode);
				 newZnodes=newZnode.unique3();
				 console.log("去重后的：");
				 console.log(newZnodes);
				 flag=newZnodes.length;
				 if(k==1){
					 newZnodes[flag++]=zNodes[0];
				 }
				 if(q==1){
					 newZnodes[flag++]=zNodes[6];
				 }
				 if(w==1){
					 newZnodes[flag++]=zNodes[10];
				 }
				 if(e==1){
					 newZnodes[flag++]=zNodes[13];
				 }
				console.log("新的树"+newZnode);
				$.fn.zTree.init($("#treeMenu"), setting, newZnodes);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		});
		
		var setting2 = {
				data : {
					key : {
						title : "t" // 鼠标在停留在菜单上提示
					},
					simpleData : { // 简单数据 
						enable : true
					}
				},
				callback : {
					onClick : onClick
				}
		};
		
		// 系统管理菜单加载
		$.ajax({
			url : '${pageContext.request.contextPath}/json/admin.json',
			dataType : 'text',
			success : function(data) {
				var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#adminMenu"), setting2, zNodes);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		});
		
		function onClick(event, treeId, treeNode, clickFlag) {
			if (treeNode.click != false) {
				open(treeNode.name, treeNode.page);
			}
		}
		// 开启一个新的tab页面
		function open(text, url) {
			if ($("#tabs").tabs('exists', text)) {
				$('#tabs').tabs('select', text);
			} else {
				var content = '<div style="width:100%;height:100%;overflow:hidden;">'
						+ '<iframe src="'
						+ url
						+ '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';

				$('#tabs').tabs('add', {
					title : text,
					content : content,
					//href : url
					closable : true
				});
			}
		}
		
		// 页面加载后 右下角 弹出窗口
		/**************/
		window.setTimeout(function(){
			//alert($("b").html());
			var msg=$("b").html();
			$.messager.show({
				title:"消息提示",
				msg:'欢迎登录，'+msg+'！ <a href="javascript:void" onclick="showAbout();">联系管理员</a>',
				timeout:5000
			});
		},3000);
		
		//取消密码修改
		$("#btnCancel").click(function(){
			$('#editPwdWindow').window('close');
		});
		
		//修改密码后保存事件
		$("#btnEp").click(function(){
			var newpass=$("#txtNewPass").val();
			var confirmpass=$("#txtRePass").val();
			//校验密码是否为空
			if($.trim(newpass)==""){
				$.messager.alert('警告','新密码不能为空或者空白字符！','warning');
				return;
			}
			//两次输入的密码必须相同
			if($.trim(newpass) != $.trim(confirmpass)){
				$.messager.alert('警告','两次密码输入不一致！','warning');
				return ;
			}
			
			$.post("${pageContext.request.contextPath}/user/changePass.action", {password: newpass}, function(data){
				if(data.result == "success"){
					$.messager.alert("信息", data.msg, "info");
				}else{
					$.messager.alert("信息", data.msg, "info");
				}
				// 窗口关闭 
				$("#editPwdWindow").window('close');
			},"json");
		});
		
		
	})
	
	Array.prototype.unique3 = function(){
		var n = [this[0]]; //结果数组
		 for(var i = 1; i < this.length; i++) //从第二项开始遍历
		 {
		 //如果当前数组的第i项在当前数组中第一次出现的位置不是i，
		 //那么表示第i项是重复的，忽略掉。否则存入结果数组
		 if (this.indexOf(this[i]) == i) n.push(this[i]);
		 }
		 return n;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resources.jsp"></jsp:include>
</head>

  <body  class="easyui-layout">
  	<div data-options="region:'north',border:false">
		<jsp:include page="./admin_top.jsp" />
	</div>
	<div data-options="region:'west',split:true,title:'菜单导航'"
		style="width:200px">
		<div class="easyui-accordion" fit="true" border="false">
			<div title="基本功能" data-options="iconCls:'icon-mini-add'" style="overflow:auto">
				<ul id="treeMenu" class="ztree"></ul>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-mini-add'" style="overflow:auto">  
				<ul id="adminMenu" class="ztree"></ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" fit="true" class="easyui-tabs" border="false">
			<div title="消息中心" id="subWarp"
				style="width:100%;height:100%;overflow:hidden">
			</div>
		</div>
	</div>
	 <div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 160px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                    
                </table>
            </div>
             <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
	</div>
  </body>
    
</html>
