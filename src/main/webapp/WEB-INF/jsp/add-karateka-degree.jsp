<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h1>New</h1>
		</div>
		<div class="panel-body">
			<form:form cssClass="form-horizontal" method="post"
				modelAttribute="karatekaDegreeDTO">				
				
				<form:hidden path="karatekaId" value="${karateka.id}"/>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="degreeId">Choose a Degree :</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="degreeId" required="true">
						<form:option value="">--Select--</form:option>	
						<form:options items="${degrees}" itemLabel="fullDegree" itemValue="id" />			
					</form:select>
					</div>
				</div>	
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="dateOfReceipt">Date of Receipt</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${karatekaDegreeDTO.id}" />
						<form:input cssClass="form-control" path="dateOfReceipt" type="date" pattern="yyyy-MM-dd"
							value="${karatekaDegreeDTO.dateOfReceipt}" />
					</div>
				</div>								
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="clubId">Choose a Club :</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="clubId" required="true">
						<form:option value="">--Select--</form:option>	
						<form:options items="${clubs}" itemLabel="clubName" itemValue="id" />			
					</form:select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="trainerId">Choose a Trainer :</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="trainerId" required="true">
						<form:option value="">--Select--</form:option>	
						<form:options items="${trainers}" itemLabel="fullName" itemValue="id" />			
					</form:select>
					</div>
				</div>																				
				
				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" class="btn btn-primary" value="Add" />
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