<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<tags:pageTemplate titulo="Create Department">
	<section>
		<form:form servletRelativeAction="${s:mvcUrl('createDepartment').build()}" method="POST">
			<div class="form-group">
				<label for="companyName">Department Name</label>
				<input type="text" class="form-control" id="companyName" aria-describedby="emailHelp" name="name">
			</div>
			<input type="submit" value="SUBMIT" class="btn btn-dark">
        </form:form>
	</section>
</tags:pageTemplate>