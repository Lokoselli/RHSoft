<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Navbar</a>
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
					<a class="nav-link" href="#">Link</a>
				</li>
			</ul>
			<ul class="navbar-nav navbar-right">
                <li>
                    <a class="nav-link" href="${s:mvcUrl('login').build()}">
                        Login
                    </a>
                </li>
				<li class="nav-item">
					<a class="nav-link" href="?locale=en_US">
						EN
					</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="?locale=pt">PT</a></li>
			</ul>
		</div>
	</nav>

	<nav>
	</nav>
</body>