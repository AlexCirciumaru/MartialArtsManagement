<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h1>Add Event</h1>
		</div>
		<div class="panel-body">
			<form:form cssClass="form-horizontal" method="post"
				modelAttribute="eventDTO">
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="name">Name</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${eventDTO.id}" />
						<form:input cssClass="form-control" path="name"
							value="${eventDTO.name}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="eventType">Event Type</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${eventDTO.eventType}" />
						<form:radiobutton path="eventType" value="SEMINAR" /> Seminar
						<form:radiobutton path="eventType" value="COMPETITION" /> Competition
						<form:radiobutton path="eventType" value="DEMONSTRATION" /> Demonstration
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="capacity">Capacity</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${eventDTO.id}" />
						<form:input cssClass="form-control" path="capacity"
							value="${eventDTO.capacity}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="location">Location</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${eventDTO.id}" />
						<form:input cssClass="form-control" path="location"
							value="${eventDTO.location}" />
					</div>
				</div>	
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="date">Date</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${eventDTO.id}" />
						<form:input cssClass="form-control" path="date" type="date" pattern="yyyy-MM-dd"
							value="${eventDTO.date}" />
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