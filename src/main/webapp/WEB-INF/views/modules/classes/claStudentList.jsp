<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生管理管理</title>
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
		<li class="active"><a href="${ctx}/classes/claStudent/">学生管理列表</a></li>
		<shiro:hasPermission name="classes:claStudent:edit"><li><a href="${ctx}/classes/claStudent/form">学生管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="claStudent" action="${ctx}/classes/claStudent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="stuName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:select path="stuGender" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>班级id：</label>
				<form:input path="classId" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>姓名</th>
				<th>性别</th>
				<th>班级id</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="classes:claStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="claStudent">
			<tr>
				<td><a href="${ctx}/classes/claStudent/form?id=${claStudent.id}">
					${claStudent.id}
				</a></td>
				<td>
					${claStudent.stuName}
				</td>
				<td>
					${fns:getDictLabel(claStudent.stuGender, 'sex', '')}
				</td>
				<td>
					${claStudent.classId}
				</td>
				<td>
					<fmt:formatDate value="${claStudent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${claStudent.remarks}
				</td>
				<shiro:hasPermission name="classes:claStudent:edit"><td>
    				<a href="${ctx}/classes/claStudent/form?id=${claStudent.id}">修改</a>
					<a href="${ctx}/classes/claStudent/delete?id=${claStudent.id}" onclick="return confirmx('确认要删除该学生管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>