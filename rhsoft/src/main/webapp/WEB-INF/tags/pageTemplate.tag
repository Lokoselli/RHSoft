<%@attribute name="titulo" required="true"%>
<%@attribute name="bodyClass" required="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<style>
    section{
        padding-top:60px;
        padding-right:30px;
        padding-left:30px;
    }
</style>
<style><%@include file="/resources/statics/css/bootstrap.min.css"%></style>
<script><%@include file="/resources/statics/js/bootstrap.min.js"%></script>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <c:url value="/src/main/webapp" var="contextPath" />

    <title>${titulo} - RhSoft</title>
</head>

<body class=${bodyClass}>
    <%@include file="/WEB-INF/views/header.jsp" %>

    <jsp:doBody></jsp:doBody>

</body>
</html>