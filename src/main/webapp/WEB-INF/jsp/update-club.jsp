<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h1>Update Club</h1>
		</div>
		<div class="panel-body">
			<form:form cssClass="form-horizontal" method="post"
				modelAttribute="clubDTO">

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="clubName">Club Name</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${clubDTO.id}" />
						<form:input cssClass="form-control" path="clubName"
							value="${clubDTO.clubName}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="address">Address</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${clubDTO.id}" />
						<form:input cssClass="form-control" path="address"
							value="${clubDTO.address}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="dateOfEstablishment">Date of Establishment</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${clubDTO.id}" />
						<form:input cssClass="form-control" path="dateOfEstablishment" type="date" pattern="yyyy-MM-dd"
							value="${clubDTO.dateOfEstablishment}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" class="btn btn-primary" value="Update" />
						</div>
						<div class="col-xs-4"></div>
					</div>
				</div>
				<form:errors path="*" cssClass="errorblock" element="div" />
			</form:form>
		</div>
	</div>
</div>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>