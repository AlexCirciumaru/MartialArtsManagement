<%@ include file="header.jsp"%>
<!-- Page content (Begin)  -->

<%@ include file="home.jsp"%>

<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<div align="left">
				<h3 class="panel-title">
					<b>Karatekas List</b>
				</h3>
			</div>
			<div align="right">
				<h3 class="panel-title">
					<a href="<c:url value='/karateka/add'/>">Add New Karateka</a>
				</h3>
			</div>
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${empty karatekas or empty karatekas.content}">There are no Karatekas</c:when>
				<c:otherwise>
					<form:form modelAttribute="searchDTO" method="get">
						<div class="form-group">
							<label for="query">Search:</label>
							<div class="input-group">
								<input type="text" class="form-control" name="query" id="query" />
								<span class="input-group-btn"><button type="submit"
										class="btn btn-default">
										<span class="glyphicon glyphicon-search"></span>
									</button> </span>
							</div>
						</div>

						<div class="form-group">
							<label for="perPage">Per Page:</label> <select name="perPage"
								class="form-control">
								<option value="5">5</option>
								<option value="10">10</option>
								<option value="25">25</option>
								<option value="50">50</option>
							</select>
						</div>
						
						<div class="form-group">
							<label for="sortDirection">Sort Direction:</label> <select name="sortDirection"
								class="form-control">
								<option value="ASC">ASCENDING</option>
								<option value="DES">DESCENDING</option>
							</select>
						</div>
						
						<div class="form-group">
							<label for="sortBy">Sort By:</label> <select name="sortBy"
								class="form-control">
								<option value="lastName">Last Name</option>
								<option value="firstName">First Name</option>
								<option value="age">Age</option>
								<option value="beginningYear">Beginning Year</option>
								<option value="email">Email</option>
							</select>
						</div>
					</form:form>					

					<table class="table table-hover table-bordered">
						<thead style="background-color: #bce8f1;">
							<tr>
								<th>Id</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Age</th>
								<th>Beginning Year</th>
								<th>Club</th>
								<th>Email</th>
								<th>Historic</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${karatekas.content}" var="karateka">
								<tr>
									<th><c:out value="${karateka.id}" /></th>
									<th><c:out value="${karateka.firstName}" /></th>
									<th><c:out value="${karateka.lastName}" /></th>
									<th><c:out value="${karateka.age}" /></th>
									<th><c:out value="${karateka.beginningYear}" /></th>
									<th><c:out value="${karateka.club.clubName}" /></th>
									<th><c:out value="${karateka.email}" /></th>
									<th><a
										href="<c:url value='/karateka/${karateka.id}/history'/>">View</a></th>
									<th><a
										href="<c:url value='/karateka/update?id=${karateka.id}'/>">Edit</a></th>
									<th><a
										href="<c:url value='/karateka/delete?id=${karateka.id}'/>">Delete</a></th>
								</tr>								
							</c:forEach>
						</tbody>
					</table>
					
					${karatekas.totalElements} total results
					
					<ul class="pager">
						<c:url var="searchUrl" value="">
							<c:param name="query">${searchDTO.query}</c:param>
							<c:param name="perPage">${searchDTO.perPage}</c:param>
							<c:param name="sortDirection">${searchDTO.sortDirection}</c:param>
							<c:param name="sortBy">${searchDTO.sortBy}</c:param>
						</c:url>

						<c:url var="nextUrl" value="${searchUrl}">
							<c:param name="pageNumber">${searchDTO.pageNumber + 1}</c:param>
						</c:url>
	
						<c:url var="previousUrl" value="${searchUrl}">
							<c:param name="pageNumber">${searchDTO.pageNumber - 1}</c:param>
						</c:url>												

						<c:choose>
							<c:when test="${searchDTO.pageNumber eq 1}">
								<li class="previous disabled"><a href="#"><span
										aria-hidden="true">&larr;</span> Older</a></li>
							</c:when>
							<c:otherwise>
								<li class="previous"><a href="${previousUrl}"><span
										aria-hidden="true">&larr;</span> Older</a></li>
							</c:otherwise>
						</c:choose>						
						
						<li class="">Page ${searchDTO.pageNumber} of ${karatekas.totalPages}</li>						
						
						<c:choose>
							<c:when test="${karatekas.totalPages eq searchDTO.pageNumber}">
								<li class="next disabled"><a href="#">Newer <span
										aria-hidden="true">&rarr;</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="next"><a href="${nextUrl}">Newer <span
										aria-hidden="true">&rarr;</span></a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- Page content (End)    -->
<%@ include file="footer.jsp"%>