package gcrud

class Study {

    static constraints = {
		shortCode(blank:false, maxSize:100)
		title(maxSize:1500)
		description(maxSize:1500)
    }
	
	String shortCode
	String title
	String description

	static hasMany = [studyContext:StudyContext, people:Person]
}
