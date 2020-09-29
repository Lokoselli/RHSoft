<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Login">
    <section>
	<form:form servletRelativeAction="/login" method="POST">
		<div class="form-group">
			<label>Email</label>
			<input name="username" type="text" class="form-control" />
			<form:errors path="username" />
		</div>
		<div class="form-group">
			<label>Senha</label>
			<input type="password" name="password" class="form-control"/>
			<form:errors path="descricao" />
		</div>

        <button type="submit" class="btn btn-primary">Login</button>
	</form:form>
    </section>
</tags:pageTemplate>
