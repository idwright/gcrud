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

		def problems = new ArrayList<Person>()
		Person.findAll().each { s ->
			def valid = true
			
			def words = s.description.findAll(/\b[\w+]+\b/)
			if (words.size() < 20) {
				s.tooShort = true
				valid = false
			} else {
			    s.tooShort = false
			}
			if (words.size() > 150) {
				s.tooLong = true
				valid = false
			} else {
				s.tooLong = false;
			}
			if (s.email == null || s.email.length() == 0) {
				s.noEmail = true
				valid = false
			} else {
				s.noEmail = false
			}
			//Check if any affiliations
			def found = false
			s.affiliation.each { b ->
			   if (b.name.length() > 0) {
				   found = true
			   }
			}
			if (!found) {
				s.hasAffiliations = false
				valid = false
			} else {
				s.hasAffiliations = true
			}
			
			if (!valid) {
				problems += s
			}
		  }
		
		Integer end = 0
		Integer start = params.offset?params.int('offset'):0
		if ( start + params.max > problems.size())
			end = problems.size() > 0 ? problems.size - 1 : 0
		else
			end = start + params.max
		
		
		def problemList = new ArrayList<Person>()
		if (end > 0)
			problemList = problems[start..end]
		 else
		 	problemList = problems
		
		[personProblemList: problemList, personInstanceTotal: problems.size()]
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
