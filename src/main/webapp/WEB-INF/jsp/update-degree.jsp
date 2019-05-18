<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h1>Update Degree</h1>
		</div>
		<div class="panel-body">
			<form:form cssClass="form-horizontal" method="post"
				modelAttribute="degreeDTO">
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="degreeRank">Degree Rank</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${degreeDTO.id}" />
						<form:input cssClass="form-control" path="degreeRank"
							value="${degreeDTO.degreeRank}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="degreeType">Degree Type</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${degreeDTO.degreeType}" />
						<form:radiobutton path="degreeType" value="KYU" /> Kyu
						<form:radiobutton path="degreeType" value="DAN" /> Dan
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="beltColor">Belt Color</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${degreeDTO.id}" />
						<form:input cssClass="form-control" path="beltColor"
							value="${degreeDTO.beltColor}" />
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