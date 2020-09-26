<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">
			${exposedCompany}
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="${s:mvcUrl('index').build()}">
						<fmt:message key="navegacao.categoria.home" />
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${s:mvcUrl('listAllWorkers').build()}"><fmt:message key='navegacao.categoria.workers'/></a>
				</li>
			</ul>
			<ul class="navbar-nav navbar-right">
				<li>
					<a class="nav-link" href="${s:mvcUrl('login').build()}">
						Login
					</a>
				</li>

                <tags:localeList/>
			</ul>
		</div>
	</nav>

	<nav>
	</nav>
</body>