<%@ include file="header.jsp"%>
<!-- Page content (Begin)  -->

<%@ include file="home.jsp"%>

<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<div align="left">
				<h3 class="panel-title">
					<b>History</b>
				</h3>
			</div>
			<div align="right">
				<h3 class="panel-title">

					<a href="<c:url value='/karatekaDegree/add?id=${karateka.id}'/>">Add</a>
				</h3>
			</div>
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when
					test="${empty karatekaDegrees or empty karatekaDegrees.content}">There is no History</c:when>
				<c:otherwise>
					<form:form modelAttribute="searchDTO" method="get">

						<div class="form-group">
							<label for="sortDirection">Sort Direction:</label> <select
								name="sortDirection" class="form-control">
								<option value="ASC">ASCENDING</option>
								<option value="DES">DESCENDING</option>
							</select>
						</div>

						<div class="form-group">
							<label for="sortBy">Sort By:</label> <select name="sortBy"
								class="form-control">
								<option value="dateOfReceipt">Date of Receipt</option>
							</select>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-default">Apply
								filters</button>
						</div>
					</form:form>
					<table class="table table-hover table-bordered">
						<thead style="background-color: #bce8f1;">
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Degree</th>
								<th>Belt Color</th>
								<th>Date of Receipt</th>
								<th>Club</th>
								<th>Trainer</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${karatekaDegrees.content}"
								var="karatekaDegree">
								<tr>
									<th><c:out value="${karatekaDegree.id}" /></th>
									<th><c:out value="${karatekaDegree.karateka.fullName}" /></th>
									<th><c:out value="${karatekaDegree.degree.fullDegree}" /></th>
									<th><c:out value="${karatekaDegree.degree.beltColor}" /></th>
									<th><c:out value="${karatekaDegree.dateOfReceipt}" /></th>
									<th><c:out value="${karatekaDegree.club.clubName}" /></th>
									<th><c:out value="${karatekaDegree.trainer.fullName}" /></th>
									<th><a
										href="<c:url value='/karatekaDegree/update?id=${karatekaDegree.id}'/>">Edit</a></th>
									<th><a
										href="<c:url value='/karatekaDegree/delete?id=${karatekaDegree.id}'/>">Delete</a></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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