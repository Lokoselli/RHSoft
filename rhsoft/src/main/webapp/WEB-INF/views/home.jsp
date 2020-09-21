<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
	table {
		text-align: center;
	}

	form {
		margin-block-end: 0em;
	}

	.buttonHolder {
		vertical-align: middle;
	}

	.workerCount {
		width: 15%;
	}

	.departmentColumn {
		width: 80%;
	}

	.blockLink a {
		display: block;
		width: 100%;
		color: inherit;
	}

	.nonHoverable a:hover {
		color: inherit;
		text-decoration: none;
		cursor: pointer;
	}

	.links a:hover {
		color: inherit;
		cursor: pointer;
	}

	.links a {
		color: inherit;
	}

	.createButton {}
</style>

<tags:pageTemplate titulo="Home">
	<section>
		<table class="table table-striped table-dark table-hover">
			<thead>
				<tr>
					<th scope="col" class="departmentColumn">
						<fmt:message key="departments.list.department" />
					</th>
					<th class="workerCount" scope="col">
						<fmt:message key="departments.list.workerCount" />
					</th>
					<th class="otherColumn" scope="col" colspan="2"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${exposedCompany.departments}" var="department">
					<tr>
						<th scope="row" class="departmentColumn nonHoverable blockLink" style="vertical-align:middle">
							<a href="#">${department.name}</a>
						</th>
						<td class="workerCount nonHoverable blockLink" style="vertical-align:middle">
							<a href="#">0</a>
						</td>
						<td class="otherColumn links">
							<form:form servletRelativeAction="#" method="POST">
								<input type="submit" value='<fmt:message key="buttons.edit"/>' class="btn btn-secondary">
                            </form:form>
						</td>
						<td class="otherColumn links">
							<form:form servletRelativeAction="${s:mvcUrl('removeDepartment').arg(0, department.id).build()}" method="POST">
								<input type="submit" value='<fmt:message key="buttons.delete"/>' class="btn btn-secondary">
                            </form:form>
						</td>
					</tr>
				</c:forEach>
				<tr class="table-dark">
					<th class="createButton nonHoverable blockLink" colspan="4">
						<a href="${s:mvcUrl('departmentForm').build()}">
							<fmt:message key="buttons.createNew" />
							<fmt:message key="departments.list.department" /> +
						</a>
					</th>
				</tr>
			</tbody>
		</table>

	</section>
</tags:pageTemplate>