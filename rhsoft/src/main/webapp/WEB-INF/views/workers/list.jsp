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

		<form:form servletRelativeAction="#">
			<c:forEach items="${workers}" var="worker">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
					<label class="form-check-label" for="defaultCheck1">
                        Default checkbox
                    </label>
				</div>
			</c:forEach>
		</form:form>

	</section>
</tags:pageTemplate>