<%@ page import="gcrud.StudyContext" %>



<div class="fieldcontain ${hasErrors(bean: studyContextInstance, field: 'contextID', 'error')} ">
	<label for="contextID">
		<g:message code="studyContext.contextID.label" default="Context ID" />
		
	</label>
	<g:textField name="contextID" value="${studyContextInstance?.contextID}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studyContextInstance, field: 'country', 'error')} ">
	<label for="country">
		<g:message code="studyContext.country.label" default="Country" />
		
	</label>
	<g:textField name="country" value="${studyContextInstance?.country}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studyContextInstance, field: 'latitude', 'error')} required">
	<label for="latitude">
		<g:message code="studyContext.latitude.label" default="Latitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="latitude" value="${fieldValue(bean: studyContextInstance, field: 'latitude')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: studyContextInstance, field: 'longitude', 'error')} required">
	<label for="longitude">
		<g:message code="studyContext.longitude.label" default="Longitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="longitude" value="${fieldValue(bean: studyContextInstance, field: 'longitude')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: studyContextInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="studyContext.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${studyContextInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studyContextInstance, field: 'numSamples', 'error')} required">
	<label for="numSamples">
		<g:message code="studyContext.numSamples.label" default="Num Samples" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numSamples" type="number" value="${studyContextInstance.numSamples}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: studyContextInstance, field: 'studies', 'error')} ">
	<label for="studies">
		<g:message code="studyContext.studies.label" default="Studies" />
		
	</label>
	<g:textField name="studies" value="${studyContextInstance?.studies}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studyContextInstance, field: 'subCont', 'error')} ">
	<label for="subCont">
		<g:message code="studyContext.subCont.label" default="Sub Cont" />
		
	</label>
	<g:textField name="subCont" value="${studyContextInstance?.subCont}"/>
</div>

