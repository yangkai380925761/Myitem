<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" type="text/css"href="<%=basePath%>js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"href="<%=basePath%>js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"href="<%=basePath %>js/easyui/themes/default/datagrid.css">

<script type="text/javascript"src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"src="<%=basePath%>js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"src="<%=basePath %>js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- 导入ztree类库 -->

<script src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js" type="text/javascript"></script>
