<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tags:pageTemplate titulo="Create Company">
	<section>
		<form:form servletRelativeAction="${s:mvcUrl('gravaEmpresa').build()}" method="POST">
			<div class="form-group">
				<label for="companyName">
                    <fmt:message key="company.label.name"/>
                </label>
				<input type="text" class="form-control" id="companyName" aria-describedby="emailHelp" name="name">
			</div>
				<input type="submit" value="<fmt:message key='buttons.submit'/>" class="btn btn-dark">
        </form:form>
	</section>
</tags:pageTemplate>