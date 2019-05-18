<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h1>Add Karateka</h1>
		</div>
		<div class="panel-body">
			<form:form cssClass="form-horizontal" method="post"
				modelAttribute="karatekaDTO">
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="firstName">First Name</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${karatekaDTO.id}" />
						<form:input cssClass="form-control" path="firstName"
							value="${karatekaDTO.firstName}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="lastName">Last Name</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${karatekaDTO.id}" />
						<form:input cssClass="form-control" path="lastName"
							value="${karatekaDTO.lastName}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="age">Age</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${karatekaDTO.id}" />
						<form:input cssClass="form-control" path="age"
							value="${karatekaDTO.age}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="beginningYear">Beginning Year</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${karatekaDTO.id}" />
						<form:input cssClass="form-control" path="beginningYear"
							value="${karatekaDTO.beginningYear}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="email">Email</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${karatekaDTO.id}" />
						<form:input cssClass="form-control" path="email" type="email"
							value="${karatekaDTO.email}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="password">Password</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${karatekaDTO.id}" />
						<form:input cssClass="form-control" path="password" type="password"
							value="${karatekaDTO.password}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="trainer">Is trainer?</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${karatekaDTO.trainer}" />
						<form:radiobutton path="trainer" value="true" /> Yes
						<form:radiobutton path="trainer" value="false" /> No
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