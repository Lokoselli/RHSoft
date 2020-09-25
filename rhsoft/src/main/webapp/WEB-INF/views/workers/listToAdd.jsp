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
		<form:form servletRelativeAction="${s:mvcUrl('teste').build()}">
			<table class="table table-striped table-dark table-hover">
                <tr class="table-dark">
					<th class="createButton nonHoverable blockLink" colspan="4">
						<a href="${s:mvcUrl('workerForm').arg(0, previousPage).build()}">
							<fmt:message key="buttons.addNew" />
							<fmt:message key="workers.worker" /> +
						</a>
					</th>
				</tr>
                <tr>
                    <th></th>
                    <th>Nome</th>
                    <th>Email</th>
                </tr>
				<c:forEach items="${workers}" var="worker">
					<tr>
						<td>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="${worker.id}" id="defaultCheck${worker.id}" name="selected">
							</div>
						</td>
						<td>${worker.name}</td>
						<td>${worker.email}</td>
					</tr>


				</c:forEach>
			</table>
            <input type = "hidden" value="${previousPage}" name="previousPage"/>
            <input type="hidden" value="${departmentId}" name="departmentId"/>
			<input type="submit" value="Adicionar"/>
		</form:form>

	</section>
</tags:pageTemplate>