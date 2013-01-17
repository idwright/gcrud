
<%@ page import="gcrud.StudyContext" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'studyContext.label', default: 'StudyContext')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-studyContext" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-studyContext" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="contextID" title="${message(code: 'studyContext.contextID.label', default: 'Context ID')}" />
					
						<g:sortableColumn property="country" title="${message(code: 'studyContext.country.label', default: 'Country')}" />
					
						<g:sortableColumn property="latitude" title="${message(code: 'studyContext.latitude.label', default: 'Latitude')}" />
					
						<g:sortableColumn property="longitude" title="${message(code: 'studyContext.longitude.label', default: 'Longitude')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'studyContext.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="numSamples" title="${message(code: 'studyContext.numSamples.label', default: 'Num Samples')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${studyContextInstanceList}" status="i" var="studyContextInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${studyContextInstance.id}">${fieldValue(bean: studyContextInstance, field: "contextID")}</g:link></td>
					
						<td>${fieldValue(bean: studyContextInstance, field: "country")}</td>
					
						<td>${fieldValue(bean: studyContextInstance, field: "latitude")}</td>
					
						<td>${fieldValue(bean: studyContextInstance, field: "longitude")}</td>
					
						<td>${fieldValue(bean: studyContextInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: studyContextInstance, field: "numSamples")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${studyContextInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
