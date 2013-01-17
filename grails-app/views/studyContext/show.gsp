
<%@ page import="gcrud.StudyContext" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'studyContext.label', default: 'StudyContext')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-studyContext" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-studyContext" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list studyContext">
			
				<g:if test="${studyContextInstance?.contextID}">
				<li class="fieldcontain">
					<span id="contextID-label" class="property-label"><g:message code="studyContext.contextID.label" default="Context ID" /></span>
					
						<span class="property-value" aria-labelledby="contextID-label"><g:fieldValue bean="${studyContextInstance}" field="contextID"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studyContextInstance?.country}">
				<li class="fieldcontain">
					<span id="country-label" class="property-label"><g:message code="studyContext.country.label" default="Country" /></span>
					
						<span class="property-value" aria-labelledby="country-label"><g:fieldValue bean="${studyContextInstance}" field="country"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studyContextInstance?.latitude}">
				<li class="fieldcontain">
					<span id="latitude-label" class="property-label"><g:message code="studyContext.latitude.label" default="Latitude" /></span>
					
						<span class="property-value" aria-labelledby="latitude-label"><g:fieldValue bean="${studyContextInstance}" field="latitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studyContextInstance?.longitude}">
				<li class="fieldcontain">
					<span id="longitude-label" class="property-label"><g:message code="studyContext.longitude.label" default="Longitude" /></span>
					
						<span class="property-value" aria-labelledby="longitude-label"><g:fieldValue bean="${studyContextInstance}" field="longitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studyContextInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="studyContext.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${studyContextInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studyContextInstance?.numSamples}">
				<li class="fieldcontain">
					<span id="numSamples-label" class="property-label"><g:message code="studyContext.numSamples.label" default="Num Samples" /></span>
					
						<span class="property-value" aria-labelledby="numSamples-label"><g:fieldValue bean="${studyContextInstance}" field="numSamples"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studyContextInstance?.studies}">
				<li class="fieldcontain">
					<span id="studies-label" class="property-label"><g:message code="studyContext.studies.label" default="Studies" /></span>
					
						<span class="property-value" aria-labelledby="studies-label"><g:fieldValue bean="${studyContextInstance}" field="studies"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studyContextInstance?.subCont}">
				<li class="fieldcontain">
					<span id="subCont-label" class="property-label"><g:message code="studyContext.subCont.label" default="Sub Cont" /></span>
					
						<span class="property-value" aria-labelledby="subCont-label"><g:fieldValue bean="${studyContextInstance}" field="subCont"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${studyContextInstance?.id}" />
					<g:link class="edit" action="edit" id="${studyContextInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
