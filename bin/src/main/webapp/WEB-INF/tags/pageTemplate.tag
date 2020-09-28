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


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${contextPath}/rhsoft/resources/css/bootstrap.min.css"
		integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="${contextPath}/rhsoft/resources/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous">
	</script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <c:url value="/src/main/webapp" var="contextPath" />

    <title>${titulo} - RhSoft</title>
</head>

<body class=${bodyClass}>
    <%@include file="/WEB-INF/views/header.jsp" %>

    <jsp:doBody></jsp:doBody>

</body>
</html>