<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>班级管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/classes/claClasses/">班级管理列表</a></li>
		<shiro:hasPermission name="classes:claClasses:edit"><li><a href="${ctx}/classes/claClasses/form">班级管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="claClasses" action="${ctx}/classes/claClasses/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>班级名：</label>
				<form:input path="className" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>班主任：</label>
				<form:input path="classTecher" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>班级名</th>
				<th>班主任</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="classes:claClasses:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="claClasses">
			<tr>
				<td><a href="${ctx}/classes/claClasses/form?id=${claClasses.id}">
					${claClasses.id}
				</a></td>
				<td>
					${claClasses.className}
				</td>
				<td>
					${claClasses.classTecher}
				</td>
				<td>
					<fmt:formatDate value="${claClasses.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${claClasses.remarks}
				</td>
				<shiro:hasPermission name="classes:claClasses:edit"><td>
    				<a href="${ctx}/classes/claClasses/form?id=${claClasses.id}">修改</a>
					<a href="${ctx}/classes/claClasses/delete?id=${claClasses.id}" onclick="return confirmx('确认要删除该班级管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>