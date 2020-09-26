<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<c:set var="localeCode" value="${pageContext.response.locale}" />	

<li class="nav-item ${localeCode == 'en_US' ? 'active' : ''}">
	<a class="nav-link" href="?locale=en_US">
		EN
	</a>
</li>
<li class="nav-item ${localeCode == 'pt' ? 'active' : ''}">
	<a class="nav-link" href="?locale=pt">
		PT
	</a>
</li>