<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
    h1 {
        text-align:center;
    }

	table {
		text-align: center;
	}

	form {
		margin-block-end: 0em;
	}

	.buttonHolder {
		vertical-align: middle;
	}

	.emailColumn {
		width: 15%;
	}

	.nameColumn {
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
        <h1>${department.name}</h1>
		<table class="table table-striped table-dark table-hover">
			<thead>
				<tr>
					<th scope="col" class="nameColumn">
						<fmt:message key="workers.label.name" />
					</th>
					<th class="emailColumn" scope="col">
						<fmt:message key="workers.label.email" />
					</th>
					<th class="otherColumn" scope="col" colspan="2"></th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-dark">
					<th class="createButton nonHoverable blockLink" colspan="4">
						<a href="${s:mvcUrl('addWorker').arg(0, department.id).build()}">
							<fmt:message key="buttons.addNew" />
							<fmt:message key="workers.worker" /> +
						</a>
					</th>
				</tr>
				<c:forEach items="${department.workers}" var="worker">
					<tr>
						<th scope="row" class="departmentColumn nonHoverable blockLink" style="vertical-align:middle">
							<a href="#">${worker.name}</a>
						</th>
						<td class="workerCount nonHoverable blockLink" style="vertical-align:middle">
							<a href="#">${worker.email}</a>
						</td>
						<td class="otherColumn links">
							<form:form servletRelativeAction="${s:mvcUrl('workerRemoveDepartment').arg(0,worker.id).arg(1,department.id).build()}" method="POST">
								<input type="submit" value="<fmt:message key='buttons.remove'>" class="btn btn-secondary">
                            </form:form>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	</section>
</tags:pageTemplate>