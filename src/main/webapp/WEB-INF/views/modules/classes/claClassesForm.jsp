<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>班级管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/classes/claClasses/">班级管理列表</a></li>
		<li class="active"><a href="${ctx}/classes/claClasses/form?id=${claClasses.id}">班级管理<shiro:hasPermission name="classes:claClasses:edit">${not empty claClasses.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="classes:claClasses:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="claClasses" action="${ctx}/classes/claClasses/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">班级名：</label>
			<div class="controls">
				<form:input path="className" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">班主任：</label>
			<div class="controls">
				<form:input path="classTecher" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">cla_student：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>姓名</th>
								<th>性别</th>
								<th>备注信息</th>
								<shiro:hasPermission name="classes:claClasses:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="claStudentList">
						</tbody>
						<shiro:hasPermission name="classes:claClasses:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#claStudentList', claStudentRowIdx, claStudentTpl);claStudentRowIdx = claStudentRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="claStudentTpl">//<!--
						<tr id="claStudentList{{idx}}">
							<td class="hide">
								<input id="claStudentList{{idx}}_id" name="claStudentList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="claStudentList{{idx}}_delFlag" name="claStudentList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="claStudentList{{idx}}_stuName" name="claStudentList[{{idx}}].stuName" type="text" value="{{row.stuName}}" maxlength="50" class="input-small "/>
							</td>
							<td>
								<select id="claStudentList{{idx}}_stuGender" name="claStudentList[{{idx}}].stuGender" data-value="{{row.stuGender}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('sex')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<textarea id="claStudentList{{idx}}_remarks" name="claStudentList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="classes:claClasses:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#claStudentList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var claStudentRowIdx = 0, claStudentTpl = $("#claStudentTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(claClasses.claStudentList)};
							for (var i=0; i<data.length; i++){
								addRow('#claStudentList', claStudentRowIdx, claStudentTpl, data[i]);
								claStudentRowIdx = claStudentRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="classes:claClasses:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>