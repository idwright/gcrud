<%@ page import="gcrud.Person" %>



<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="person.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="100" required="" value="${personInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="person.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${personInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="person.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="1500" value="${personInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'affiliation', 'error')} ">
	<label for="affiliation">
		<g:message code="person.affiliation.label" default="Affiliation" />
		
	</label>
	<g:select name="affiliation" from="${gcrud.Affiliation.list()}" multiple="multiple" optionKey="id" size="5" value="${personInstance?.affiliation*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'payload', 'error')} ">
	<label for="payload">
		<g:message code="person.payload.label" default="payload" />
		
	</label>
	<input type="file" id="payload"
						name="payload" />
</div>
