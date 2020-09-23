<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:pageTemplate titulo="Create Worker">
	<section>
		<form:form servletRelativeAction="${s:mvcUrl('createWorker').build()}" method="POST">
			<div class="form-group">
				<label for="companyName"><fmt:message key="workers.label.name"/></label>
				<input type="text" class="form-control" id="companyName" aria-describedby="emailHelp" name="name">

                <label for="companyName"><fmt:message key="workers.label.personalId"/></label>
				<input type="text" class="form-control" id="companyName" aria-describedby="emailHelp" name="personalId">

                <label for="companyName"><fmt:message key="workers.label.email"/></label>
				<input type="text" class="form-control" id="companyName" aria-describedby="emailHelp" name="email">
			</div>
			<input type="submit" value="SUBMIT" class="btn btn-dark">
        </form:form>
	</section>
</tags:pageTemplate>