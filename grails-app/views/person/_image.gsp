 <g:if test="${personInstance.filename}">
    <p>
      <img src="${createLinkTo(dir:'payload/'+personInstance.id,
                               file:''+personInstance.filename)}"
           alt="${personInstance.filename}"
           title="${personInstance.filename}" />
    </p>
  </g:if>