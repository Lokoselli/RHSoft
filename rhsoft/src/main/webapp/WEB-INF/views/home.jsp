<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<style>
	td a {
		display: block;
		width: 100%;
		color: inherit;
	}

	td a:hover {
		color: inherit;
		text-decoration: none;
		cursor: pointer;
	}

	th a {
		display: block;
		width: 100%;
        color: inherit;
	}

    th a:hover {
		color: inherit;
		text-decoration: none;
		cursor: pointer;
	}

</style>

<tags:pageTemplate titulo="Home">
	<section>
		<table class="table table-striped table-dark table-hover" style="text-align:center">
			<thead>
				<tr>
					<th scope="col">Department</th>
					<th scope="col">Workers #</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${exposedCompany.departments}" var="department">
					<tr>
						<th scope="row">
							<a href="#">${department.name}</a>
							</td>
						<td>
							<a href="#">0</a>
						</td>
					</tr>
				</c:forEach>
                <tr class="table-dark">
                    <th colspan="2" style="text-align:center" ><a href="${s:mvcUrl('departmentForm').build()}">Create New Department +</a></td>
                </tr>
			</tbody>
		</table>

	</section>
</tags:pageTemplate>