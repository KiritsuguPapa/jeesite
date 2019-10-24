<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成绩管理管理</title>
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
		<li class="active"><a href="${ctx}/classes/claScore/">成绩管理列表</a></li>
		<shiro:hasPermission name="classes:claScore:edit"><li><a href="${ctx}/classes/claScore/form">成绩管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="claScore" action="${ctx}/classes/claScore/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>stu_id：</label>
				<form:input path="stuId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>lesson_id：</label>
				<form:input path="lessonId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>lesson_name：</label>
				<form:input path="lessonName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>score：</label>
				<form:input path="score" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>teacher_id：</label>
				<form:input path="teacherId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>stu_id</th>
				<th>lesson_id</th>
				<th>lesson_name</th>
				<th>score</th>
				<th>teacher_id</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="classes:claScore:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="claScore">
			<tr>
				<td><a href="${ctx}/classes/claScore/form?id=${claScore.id}">
					${claScore.stuId}
				</a></td>
				<td>
					${claScore.lessonId}
				</td>
				<td>
					${claScore.lessonName}
				</td>
				<td>
					${claScore.score}
				</td>
				<td>
					${claScore.teacherId}
				</td>
				<td>
					<fmt:formatDate value="${claScore.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${claScore.remarks}
				</td>
				<shiro:hasPermission name="classes:claScore:edit"><td>
    				<a href="${ctx}/classes/claScore/form?id=${claScore.id}">修改</a>
					<a href="${ctx}/classes/claScore/delete?id=${claScore.id}" onclick="return confirmx('确认要删除该成绩管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>