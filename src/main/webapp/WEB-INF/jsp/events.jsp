<%@ include file="header.jsp"%>
<!-- Page content (Begin)  -->

<%@ include file="home.jsp"%>

<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<div align="left">
				<h3 class="panel-title">
					<b>Events List</b>
				</h3>
			</div>
			<div align="right">
				<h3 class="panel-title">
					<a href="<c:url value='/event/add'/>">Add New Event</a>
				</h3>
			</div>
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${empty events or empty events.content}">There are no Events</c:when>
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
								<option value="name">Name</option>
								<option value="eventType">Event Type</option>
								<option value="date">Date</option>
								<option value="capacity">Capacity</option>
							</select>
						</div>
					</form:form>

					<table class="table table-hover table-bordered">
						<thead style="background-color: #bce8f1;">
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Type</th>
								<th>Capacity</th>
								<th>Location</th>
								<th>Date</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${events.content}" var="event">
								<tr>
									<th><c:out value="${event.id}" /></th>
									<th><c:out value="${event.name}" /></th>
									<th><c:out value="${event.eventType}" /></th>
									<th><c:out value="${event.capacity}" /></th>
									<th><c:out value="${event.location}" /></th>
									<th><c:out value="${event.date}" /></th>
									<th><a
										href="<c:url value='/event/update?id=${event.id}'/>">Edit</a></th>
									<th><a
										href="<c:url value='/event/delete?id=${event.id}'/>">Delete</a></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					${events.totalElements} total results
					
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

						<li class="">Page ${searchDTO.pageNumber} of ${events.totalPages}</li>

						<c:choose>
							<c:when test="${events.totalPages eq searchDTO.pageNumber}">
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