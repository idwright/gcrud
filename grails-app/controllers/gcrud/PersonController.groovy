package gcrud

import org.springframework.dao.DataIntegrityViolationException

class PersonController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [personInstanceList: Person.list(params), personInstanceTotal: Person.count()]
    }

	def problems(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def filter = { it.email == null || it.email.length() == 0 }
		//Count number of words
		def words = "(( LENGTH(description) - LENGTH(REPLACE(description, ' ', ''))+1) < 20 OR ( LENGTH(description) - LENGTH(REPLACE(description, ' ', ''))+1) > 150)"
		def problems1 = Person.findAll("from Person WHERE (email IS NULL OR LENGTH(email) = 0) OR " + words)
		
		Person.findAll().each { s ->
			def found = false
			//Check if any affiliations
			s.affiliation.each { b ->
			   found = true
			}
			if (!found) {
				//Check if they are already in the problem list
				problems.each { p ->
					if (${p.id} == ${s.id}) {
						found = false;
					}
				}
			}
			//If no affs and not already there then add them in
			if (!found) problems1 += s
		  }
		//def problems2 = Person.executeQuery("select person.id, person.version, person.name, person.email, person.description, person.filename from person left join person_affiliation ON person_affiliation_id = person.id left join affiliation on affiliation_id = affiliation.id where affiliation.name = '' OR affiliation.name is null group by person.id")
		def problems =  (problems1)
		println(problems.size())
		Integer end = 0
		Integer start = params.int('offset')
		if ( start + params.max > problems.size())
			end = problems.size() - 1
		else
			end = start + params.max
		[personProblemList: problems[start..end], personInstanceTotal: problems.size()]
	}
	
    def create() {
        [personInstance: new Person(params)]
    }

    def save() {
        def personInstance = new Person(params)
        if (!personInstance.save(flush: true)) {
            render(view: "create", model: [personInstance: personInstance])
            retu
        }
		//handle uploaded file
		def uploadedFile = request.getFile('payload')
		if(!uploadedFile.empty){
		  println "Class: ${uploadedFile.class}"
		  println "Name: ${uploadedFile.name}"
		  println "OriginalFileName: ${uploadedFile.originalFilename}"
		  println "Size: ${uploadedFile.size}"
		  println "ContentType: ${uploadedFile.contentType}"
	
		  def webRootDir = servletContext.getRealPath("/")
		  def userDir = new File(webRootDir, "/payload/" + personInstance.id)
		  userDir.mkdirs()
		  uploadedFile.transferTo( new File( userDir, uploadedFile.originalFilename))
		  personInstance.filename = uploadedFile.originalFilename
		}
        flash.message = message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])
        redirect(action: "show", id: personInstance.id)
    }

    def show(Long id) {
        def personInstance = Person.get(id)
        if (!personInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
            redirect(action: "list")
            return
        }

        [personInstance: personInstance]
    }

    def edit(Long id) {
        def personInstance = Person.get(id)
        if (!personInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
            redirect(action: "list")
            return
        }

        [personInstance: personInstance]
    }

    def update(Long id, Long version) {
        def personInstance = Person.get(id)
        if (!personInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (personInstance.version > version) {
                personInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'person.label', default: 'Person')] as Object[],
                          "Another user has updated this Person while you were editing")
                render(view: "edit", model: [personInstance: personInstance])
                return
            }
        }

        personInstance.properties = params

        if (!personInstance.save(flush: true)) {
            render(view: "edit", model: [personInstance: personInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])
        redirect(action: "show", id: personInstance.id)
    }

    def delete(Long id) {
        def personInstance = Person.get(id)
        if (!personInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
            redirect(action: "list")
            return
        }

        try {
            personInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'person.label', default: 'Person'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'person.label', default: 'Person'), id])
            redirect(action: "show", id: id)
        }
    }
}
